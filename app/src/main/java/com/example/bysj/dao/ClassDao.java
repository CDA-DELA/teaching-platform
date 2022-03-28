package com.example.bysj.dao;



import com.example.bysj.entity.ClassR;
import com.example.bysj.utils.DbOpenHelper;

public class ClassDao extends DbOpenHelper {
    public ClassR getClassById(int id){
        ClassR item = null;
        try {
            getConnection(); //取得连接
            String sql = "select * from classr where id=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1,id);
            rs = pStmt.executeQuery();
            if(rs.next()){
                item = new ClassR();
                item.setId(id);
                item.setClassname(rs.getString("classname"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return  item;
    }
}
