package com.example.bysj.FourC;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.bysj.R;
import com.example.bysj.adapter.LvTeacherCourseAdapter;
import com.example.bysj.clicklistener.OnDelBtnClickListener;
import com.example.bysj.dao.TeacherDao;
import com.example.bysj.entity.TeacherAndCourse;


import java.util.List;


public class CourseActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_back, iv_add;

    private ListView lv_course;
    private Handler mainHandler;
    private TeacherDao teacherDao;


    private List<TeacherAndCourse> teacherAndCourseList;

    private LvTeacherCourseAdapter lvTeacherCourseAdapter; //数据适配器

    private int id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        initView();
        loadDb();

    }


    private void initView() {
        Intent intent = getIntent();
        id = intent.getIntExtra("ss", id);

        iv_back = findViewById(R.id.iv_back);
        iv_add = findViewById(R.id.iv_add);

        lv_course = findViewById(R.id.lv_course);

        teacherDao = new TeacherDao();


        mainHandler = new Handler(getMainLooper());

        iv_back.setOnClickListener(this);
        iv_add.setOnClickListener(this);


    }

    private void loadDb() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                    teacherAndCourseList = teacherDao.getTeacherById(id);
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            showLvTeacherData();
                        }
                    });
                }


        }).start();

    }

//    private void loadDb2() {
//
//    }





    private void showLvTeacherData() {
        if (lvTeacherCourseAdapter == null) {
            lvTeacherCourseAdapter = new LvTeacherCourseAdapter(this, teacherAndCourseList);
            lv_course.setAdapter(lvTeacherCourseAdapter);
        } else {
            lvTeacherCourseAdapter.setTeacherAndCourseList(teacherAndCourseList);
            lvTeacherCourseAdapter.notifyDataSetChanged();
        }

        lvTeacherCourseAdapter.setOnDelBtnClickListener(new OnDelBtnClickListener() {
            @Override
            public void onDelBtnClick(View view, int position) {

            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_add:
                break;
        }
    }


}