package com.example.bysj.fragment;

import static android.os.Looper.getMainLooper;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;

import com.example.bysj.FourC.CourseActivity;
import com.example.bysj.FourC.KaoqingActivity;
import com.example.bysj.R;
import com.example.bysj.dao.ClassDao;
import com.example.bysj.dao.StudentDao;
import com.example.bysj.dao.TeacherDao;
import com.example.bysj.dao.UserDao;
import com.example.bysj.entity.ClassR;
import com.example.bysj.entity.StudentAndClass;
import com.example.bysj.entity.TeacherAndClass;
import com.example.bysj.entity.Userinfo;
import com.example.bysj.ui.HomepageActivity;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private String uname;
    private int statusId,id,classId;

    private ClassDao classDao;
    private TeacherDao teacherDao;
    private UserDao dao;
    private StudentDao studentDao;

    private Userinfo userinfo;
    private ClassR classr;  //班级实体数据表


    private View rootview;
    private ImageView btn_add; //添加图片
    private Button btn_class;


    private Handler mainHandler;



    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(rootview == null) {
            rootview = inflater.inflate(R.layout.fragment_home, container, false);
        }

        teacherDao = new TeacherDao();
        dao = new UserDao();
        classDao = new ClassDao();
        studentDao = new StudentDao();

        btn_add = rootview.findViewById(R.id.btn_add);
        btn_class= rootview.findViewById(R.id.btn_class);

        mainHandler = new Handler(getMainLooper());

        btn_add.setOnClickListener(this);
        btn_class.setOnClickListener(this);
        loadClassDb();

        return rootview;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        uname = ((HomepageActivity) activity).getUname();//通过强转成宿主activity，就可以获取到传递过来的数据

    }

    private void loadClassDb() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                userinfo = dao.getUserByUname(uname);
                statusId = userinfo.getStatus();
                id = userinfo.getId();
                if (statusId == 0)
                {
                    TeacherAndClass teacherAndClass = teacherDao.getTeacherByTeacherId(id);
                    classId = teacherAndClass.getClassid();
                    classr = classDao.getClassById(classId);

                } else {
                    StudentAndClass studentAndClass = studentDao.getStudentByStudentId(id);
                    classId = studentAndClass.getClassid();
                    classr = classDao.getClassById(classId);
                }

                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        showData();
                    }
                });
            }
        }).start();
    }

    /**
     * 显示数据
     */
    private void showData(){
        btn_class.setText(classr.getClassname());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:
                break;
            case R.id.btn_class:
                Intent intent = new Intent(getActivity(), CourseActivity.class);
                intent.putExtra("ss",id);
                startActivity(intent);
                break;
        }

    }


}