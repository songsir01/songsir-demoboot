package com.songsir.bean;

/**
 * @PackageName com.songsir.bean
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 11:21 2018/12/7
 * @Description:
 * @Copyright Copyright (c) 2018, songyapeng@shopin.cn All Rights Reserved.
 */
public class Teacher {
    private int sid;

    private String name;

    private int age;

    @Override
    public String toString() {
        return "Teacher{" +
                "sid=" + sid +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
