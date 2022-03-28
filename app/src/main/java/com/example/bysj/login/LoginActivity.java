package com.example.bysj.login;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bysj.R;
import com.example.bysj.dao.UserDao;
import com.example.bysj.entity.Userinfo;
import com.example.bysj.reg.RegisterActivity;
import com.example.bysj.ui.HomepageActivity;
import com.example.bysj.utils.CommonUtils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_login,btn_register;//登录按钮 注册按钮




    private EditText et_uname,et_upass;//用户名，密码
    private UserDao dao;//数据库连接辅助类

    private Handler mainHandler; //接收主线程

    private CheckBox remember;//记住密码

    private SharedPreferences sharedPreferences;


    /**
     * 透明状态栏
     */
    private void transparentStatusBar() {
        //改变状态栏颜色为透明
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        transparentStatusBar();
        initView();

        sharedPreferences=getSharedPreferences("remenberpass", Context.MODE_PRIVATE);
        boolean isRemenber=sharedPreferences.getBoolean("remenberpass",false);
        if(isRemenber){
            String name=sharedPreferences.getString("name","");
            String pass=sharedPreferences.getString("pass","");
            et_uname.setText(name);
            et_upass.setText(pass);
            remember.setChecked(true);
        }


    }
    private void initView(){

        et_uname = findViewById(R.id.et_uname);
        et_upass = findViewById(R.id.et_upass);
        btn_login =findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        remember = (CheckBox) findViewById(R.id.remember);

        dao = new UserDao();

        mainHandler = new Handler(getMainLooper()); //获取主线
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);

    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_login: //登录
                doLogin();
                break;
            case R.id.btn_register:
                doRegister();
                break;
        }
    }
    /**
    *注册
    */
    private void doRegister() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }




    /**
    *登录
     */
    private void doLogin(){
        final String uname = et_uname.getText().toString().trim();
        final String upass = et_upass.getText().toString().trim();
        if(TextUtils.isEmpty(uname)){ //检查字符串是否为空  //后续还可以做密码非法字符串验证
            CommonUtils.showShortMsg(this,"请输入账号！");
            et_uname.requestFocus();
        }else if(TextUtils.isEmpty(upass)){//检查字符串是否为空
            CommonUtils.showShortMsg(this,"请输入密码！");
            et_upass.requestFocus();
        }else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final Userinfo item = dao.getUserByUnameAndUpass(uname,upass);
                    mainHandler.post(new Runnable() {

                        @Override
                        public void run() {
                            if(item == null){
                                CommonUtils.showDlgMsg(LoginActivity.this,"账号或密码错误！");
                            }else {
                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                if(remember.isChecked()){
                                    editor.putBoolean("remenberpass",true);
                                    editor.putString("name",uname);
                                    editor.putString("pass",upass);
                                }else {
                                    editor.clear();
                                }
                                editor.commit();
                                Toast.makeText(getApplicationContext(),"登陆成功！",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LoginActivity.this, HomepageActivity.class);
                                intent.putExtra("ss",uname);
                                startActivity(intent);
                            }


                        }
                    });

                }
            }).start(); //start一定要是启动线程
        }
    }




}