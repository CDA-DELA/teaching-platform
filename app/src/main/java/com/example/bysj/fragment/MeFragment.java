package com.example.bysj.fragment;

import static android.os.Looper.getMainLooper;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.bysj.R;
import com.example.bysj.dao.UserDao;
import com.example.bysj.entity.Userinfo;
import com.example.bysj.login.LoginActivity;
import com.example.bysj.ui.HomepageActivity;


public class MeFragment extends Fragment implements View.OnClickListener {
    private View rootview;
    private TextView tv_email,tv_name;
    private UserDao dao;
    private String uname;
    private Userinfo userinfo;
    private Handler mainHandler;

    public static MeFragment newInstance() {
        MeFragment meFragment = new MeFragment();

        return meFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(rootview == null) {
            rootview = inflater.inflate(R.layout.fragment_me, container, false);
        }
        dao = new UserDao();
        tv_name = rootview.findViewById(R.id.tv_name);
        tv_email = rootview.findViewById(R.id.tv_email);
        mainHandler = new Handler(getMainLooper());
        loadDb();

        return rootview;

    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        uname = ((HomepageActivity) activity).getUname();//通过强转成宿主activity，就可以获取到传递过来的数据
    }

    private void loadDb() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                userinfo = dao.getUserByUname(uname);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        showData();
                    }
                });
            }
        }).start();
    }



    private void showData(){
        tv_email.setText(userinfo.getEmail());
        tv_name.setText(userinfo.getName());
    }





    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}