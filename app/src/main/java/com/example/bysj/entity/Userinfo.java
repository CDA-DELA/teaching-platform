package com.example.bysj.entity;

import java.io.Serializable;
/*
用户信息实体类
 */
public class Userinfo implements Serializable {
    private int id; //用户id
    private String uname; //用户名
    private String upass; //用户密码
    private String creatDt; //创建日期
    private String name; //用户真实姓名
    private String email; //用户邮箱
    private String phone; //用户电话
    private int status;//学生或者老师


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Userinfo(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }

    public String getCreatDt() {
        return creatDt;
    }

    public void setCreatDt(String creatDt) {
        this.creatDt = creatDt;
    }

}
