package com.example.bysj.dao;


import com.example.bysj.entity.TeacherAndClass;
import com.example.bysj.entity.TeacherAndCourse;
import com.example.bysj.utils.DbOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class TeacherDao extends DbOpenHelper {
    public TeacherAndClass getTeacherByTeacherId(int teacherid){
        TeacherAndClass item = null;
        try {
            getConnection(); //取得连接
            String sql = "select * from teacherandclass where teacherid=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1,teacherid);
            rs = pStmt.executeQuery();
            if(rs.next()){
                item = new TeacherAndClass();
                item.setId(rs.getInt("id"));
                item.setTeacherid(teacherid);
                item.setClassid(rs.getInt("classid"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return  item;
    }

    public List<TeacherAndCourse> getTeacherById(int userid){
        List<TeacherAndCourse> list = new ArrayList<>();
        try {
            getConnection(); //取得连接
            String sql = "select * from teacherandcourse where userid=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1,userid);
            rs = pStmt.executeQuery();
            while(rs.next()){
                TeacherAndCourse item = new TeacherAndCourse();
                item.setId(rs.getInt("id"));
                item.setUserid(userid);
                item.setCoursename(rs.getString("coursename"));
                list.add(item);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return  list;
    }
}
