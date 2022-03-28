package com.example.bysj.dao;

import com.example.bysj.entity.StudentAndClass;
import com.example.bysj.utils.DbOpenHelper;


public class StudentDao extends DbOpenHelper {
    public StudentAndClass getStudentByStudentId(int studentid){
        StudentAndClass item = null;
        try {
            getConnection(); //取得连接
            String sql = "select * from studentandclass where studentid=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1,studentid);
            rs = pStmt.executeQuery();
            if(rs.next()){
                item = new StudentAndClass();
                item.setId(rs.getInt("id"));
                item.setStudentid(studentid);
                item.setClassid(rs.getInt("classid"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return  item;
    }

}
