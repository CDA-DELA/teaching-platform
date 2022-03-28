package com.example.bysj.reg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bysj.login.LoginActivity;
import com.example.bysj.R;
import com.example.bysj.dao.UserDao;
import com.example.bysj.entity.Userinfo;
import com.example.bysj.utils.CommonUtils;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_reg;//注册按钮

    private UserDao dao;//数据库连接辅助类

    private EditText et_uname,et_upass,et_name,et_email,et_phone;

    private Handler mainHandler; //主线程

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_uname = findViewById(R.id.et_uname);
        et_upass = findViewById(R.id.et_upass);
        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_phone = findViewById(R.id.et_phone);

        dao = new UserDao();

        btn_reg = findViewById(R.id.btn_reg);

        btn_reg.setOnClickListener(this);

        mainHandler = new Handler(getMainLooper());
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_reg: //注册
                doRegister();
                break;
        }

    }

    private void doRegister() {
        final String uname = et_uname.getText().toString().trim();
        final String upass = et_upass.getText().toString().trim();
        final String name = et_name.getText().toString().trim();
        final String email = et_email.getText().toString().trim();
        final String phone = et_phone.getText().toString().trim();


        if(TextUtils.isEmpty(uname)){ //检查字符串是否为空  //后续还可以做密码非法字符串验证
            CommonUtils.showShortMsg(this,"请输入账号！");
            et_uname.requestFocus();
        }else if(TextUtils.isEmpty(upass)){//检查字符串是否为空
            CommonUtils.showShortMsg(this,"请输入密码！");
            et_upass.requestFocus();
        }else if(TextUtils.isEmpty(name)){//检查字符串是否为空
            CommonUtils.showShortMsg(this,"请输入姓名！");
            et_name.requestFocus();
        }else if(TextUtils.isEmpty(email)){//检查字符串是否为空
            CommonUtils.showShortMsg(this,"请输入邮箱！");
            et_email.requestFocus();
        }else if(TextUtils.isEmpty(phone)){//检查字符串是否为空
            CommonUtils.showShortMsg(this,"请输入手机号");
            et_phone.requestFocus();
        }else {
            final Userinfo item = new Userinfo();

            item.setUname(uname);
            item.setUpass(upass);
            item.setName(name);
            item.setEmail(email);
            item.setCreatDt(CommonUtils.getDateStrFromNow());
            item.setPhone(phone);


            new Thread(new Runnable() {
                @Override
                public void run() {
                    final int iRow = dao.addUser(item);
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"注册成功！",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });

                }
            }).start();
       }
    }


}