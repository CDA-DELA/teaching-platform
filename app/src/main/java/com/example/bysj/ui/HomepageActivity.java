package com.example.bysj.ui;

import com.example.bysj.R;
import com.example.bysj.fragment.DiscussFragment;
import com.example.bysj.fragment.HomeFragment;
import com.example.bysj.fragment.MeFragment;
import com.example.bysj.login.LoginActivity;
import com.example.bysj.utils.CommonUtils;
import com.google.android.material.navigation.NavigationView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;


public class HomepageActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private DrawerLayout drawerLayout;//滑动菜单
    private Toolbar toolbar;
    private NavigationView navView;//导航视图
    private String uname;
    int arg = 0;
    private RadioGroup rb_group;
    private List<Fragment> fragments = new ArrayList<>();
    private Fragment fragment;
    private FragmentManager fm;
    private FragmentTransaction transaction;

    private long mExitTime;//退出时间

    private RadioButton rb_home,rb_me,rb_discuss;

    /**
     * 透明状态栏
     */
    private void transparentStatusBar() {
        //改变状态栏颜色为透明
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }


    /**
     * 按两次退出app
     * @param keyCode
     * @param event
     * @return
     */


        //点击两次返回退出app   System.currentTimeMillis()系统当前时间
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                    Object mHelperUtils;
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    mExitTime = System.currentTimeMillis();

                } else {
                    finish();
                }
                return true;
            }
            return super.onKeyDown(keyCode, event);
        }



    /**
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        transparentStatusBar();
        initView();
        rb_group.setOnCheckedChangeListener(this);
        fragments = getFragments();
        normalFragment();

    }
    private void normalFragment() { //初始化fragment
        fm=getSupportFragmentManager();
        transaction=fm.beginTransaction();
        fragment=fragments.get(0);
        transaction.replace(R.id.fl_container,fragment);
        transaction.commit();
    }
    private List<Fragment> getFragments() {
        fragments.add(new HomeFragment());
        fragments.add(new DiscussFragment());
        fragments.add(new MeFragment());
        return fragments;
    }

    private void addFragment(Fragment fragment , String TAG){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_container,fragment,TAG);
        transaction.commit();
    }



    /**
     * Toast提示
     * @param msg 内容
     */
    private void showMsg(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }


    private void initView() {
        Intent intent=getIntent();

        uname=intent.getStringExtra("ss");

        rb_group = (RadioGroup) findViewById(R.id.rb_group);
        rb_home = (RadioButton) findViewById(R.id.rb_home);
        rb_me = (RadioButton) findViewById(R.id.rb_me);
        rb_discuss = (RadioButton) findViewById(R.id.rb_discuss);

        drawerLayout = findViewById(R.id.drawer_layout);

        navView = findViewById(R.id.nav_view);
        //获取头部视图
        View headerView = navView.getHeaderView(0);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        //工具栏按钮点击11111

        toolbar.setNavigationOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        //头像点击
        headerView.findViewById(R.id.iv_avatar).setOnClickListener(v -> Toast.makeText(this, "看你妈！", Toast.LENGTH_SHORT).show());
        //导航菜单点击

        navView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.item_friend:
                    showMsg("朋友");
                    break;
                case R.id.item_phone:
                    showMsg("电话");
                    break;
                case R.id.item_email:
                    showMsg("邮箱");
                    break;
                case R.id.item_setting:
                    showMsg("分享");
                    break;
                case R.id.item_logout:
                    new android.app.AlertDialog.Builder(HomepageActivity.this)
                            .setTitle("退出")
                            .setMessage("确定要退出吗？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    HomepageActivity.this.finish();
                                }
                            })
                            .setNegativeButton("取消",null)
                            .create()
                            .show();
                    if(arg == 1){
                        HomepageActivity.this.finish();
                    }
                    break;
                default:
                    break;
            }
            //关闭滑动菜单
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        fm=getSupportFragmentManager();
        transaction=fm.beginTransaction();
        switch (checkedId){
            case R.id.rb_home:
                fragment=fragments.get(0);
                transaction.replace(R.id.fl_container,fragment);//取代当前页面
                break;
            case R.id.rb_discuss:
                fragment=fragments.get(1);
                transaction.replace(R.id.fl_container,fragment);
                break;
            case R.id.rb_me:
                fragment=fragments.get(2);
                transaction.replace(R.id.fl_container,fragment);
                break;
        }
        transaction.commit();
    }

    public String getUname(){
        return uname;
    }


}
