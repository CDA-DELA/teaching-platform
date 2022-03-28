package com.example.bysj.entity;

import java.io.Serializable;

public class ClassR implements Serializable {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    private int id;
    private String classname;
}
