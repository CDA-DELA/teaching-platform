package com.example.bysj.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/*
连接辅助
 */
public class DbOpenHelper {
    private static final String CLS = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://114.213.75.60:3306/testdb";
    private static final String USER = "sf";
    private static final String PASSWORD = "123456";

    public static Connection conn; //连接对象
    public static Statement stmt; //命令集
    public static PreparedStatement pStmt; //预编译命令集
    public static ResultSet rs; //结果集

    //取得连接的办法
    public static void getConnection(){
        try{
            Class.forName(CLS);
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    //关闭数据库操作对象
    public static void closeAll(){
        try {
            if(rs!=null){
                rs.close();
                rs=null;
            }
            if(stmt!=null){
                stmt.close();
                stmt=null;
            }
            if(pStmt!=null){
                pStmt.close();
                pStmt=null;
            }
            if(conn!=null){
                conn.close();
                conn=null;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
