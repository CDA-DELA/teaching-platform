package com.example.bysj.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
同用工具类自定义
 */
public class CommonUtils {
    /*
    获取当前时间
     */
    public static String getDateStrFromNow(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    public static void showShortMsg(Context context,String msg){
        //显示短消息，context 上下文，msg要显示的信息
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    public static void showLongMsg(Context context,String msg){
        //显示长消息，context 上下文，msg要显示的信息
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }

    public static void showDlgMsg(Context context, String msg){
        //显示消息对话框
        new AlertDialog.Builder(context)
                .setTitle("提示信息")
                .setMessage(msg)
                .setPositiveButton("确定",null)
                .setNegativeButton("取消",null)
                .create().show();
    }

}


