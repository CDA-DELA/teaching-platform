package com.example.bysj.utils;

/*
直接连接数据库的辅助工具类
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySqlHelp {
    public static int getUserSize(){
        final String CLS = "com.mysql.jdbc.Driver";
        final String URL = "jdbc:mysql://114.213.69.220:3306/testdb";
        final String USER = "sf";
        final String PASSWORD = "123456";

        int count = 0;

        try {
            Class.forName(CLS);
            Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
            String sql = "select count(1) as s1 from userinfo";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                count = rs.getInt("s1");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return count;

    }
}
