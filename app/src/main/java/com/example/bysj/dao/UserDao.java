package com.example.bysj.dao;

import com.example.bysj.entity.Userinfo;
import com.example.bysj.utils.DbOpenHelper;

import java.util.ArrayList;
import java.util.List;

/*
用户数据库操作类
实现用户CRUD操作
 */
public class UserDao extends DbOpenHelper {
    /**
     * 根据账号查询
     * @param uname
     * @return
     */

    public Userinfo getUserByUname(String uname){
        Userinfo item = null;
        try {
            getConnection(); //取得连接
            String sql = "select * from userinfo where uname=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,uname);
            rs = pStmt.executeQuery();
            if(rs.next()){
                item = new Userinfo();
                item.setId(rs.getInt("id"));
                item.setUname(uname);
                item.setUpass(rs.getString("upass"));
                item.setCreatDt(rs.getString("createDt"));
                item.setName(rs.getString("name"));
                item.setEmail(rs.getString("Email"));
                item.setPhone(rs.getString("phone"));
                item.setStatus(rs.getInt("status"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
       return item;
    }

    public Userinfo getUserById(int id){
        Userinfo item = null;
        try {
            getConnection(); //取得连接
            String sql = "select * from userinfo where id=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1,id);
            rs = pStmt.executeQuery();
            if(rs.next()){
                item = new Userinfo();
                item.setId(rs.getInt(id));
                item.setUname(rs.getString("uname"));
                item.setUpass(rs.getString("upass"));
                item.setCreatDt(rs.getString("createDt"));
                item.setName(rs.getString("name"));
                item.setEmail(rs.getString("Email"));
                item.setPhone(rs.getString("phone"));
                item.setStatus(rs.getInt("status"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return item;
    }



    /**
     按用户名密码查询 C
     */
    public Userinfo getUserByUnameAndUpass(String uname,String upass){
        Userinfo item = null;
        try {
            getConnection(); //取得连接
            String sql = "select * from userinfo where uname=? and upass=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,uname);
            pStmt.setString(2,upass);
            rs = pStmt.executeQuery();
            if(rs.next()){
                item = new Userinfo();
                item.setId(rs.getInt("id"));
                item.setUname(uname);
                item.setUpass(upass);
                item.setCreatDt(rs.getString("createDt"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return  item;
    }
    /**
     * 增加
     */
    public int addUser(Userinfo item){
        int iRow = 0;
        try {
            getConnection(); //取得连接
            String sql = "insert into userinfo(uname,upass,createDt,name,email,phone) values(?,?,?,?,?,?)";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,item.getUname());
            pStmt.setString(2,item.getUpass());
            pStmt.setString(3,item.getCreatDt());
            pStmt.setString(4,item.getName());
            pStmt.setString(5,item.getEmail());
            pStmt.setString(6,item.getPhone());
            iRow = pStmt.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return iRow;
    }
/**
 *
 */
    public int editUser(Userinfo item){
        int iRow = 0;
        try {
            getConnection(); //取得连接
            String sql = "update userinfo set uname=?,upass=? where id =?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,item.getUname());
            pStmt.setString(2,item.getUpass());
            pStmt.setInt(3,item.getId());
            iRow = pStmt.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return iRow;
    }
    /**
     *

     */
    public int delUser(int id){
        int iRow = 0;
        try {
            getConnection(); //取得连接
            String sql = "delete from userinfo where id =?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1,id);
            iRow = pStmt.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return iRow;
    }


}
