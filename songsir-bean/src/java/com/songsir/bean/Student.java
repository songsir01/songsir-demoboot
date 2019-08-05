package com.songsir.bean;

/**
 * @PackageName com.songsir.bean
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 16:41 2018/11/28
 * @Description:
 * @Copyright Copyright (c) 2018, songyapeng@shopin.cn All Rights Reserved.
 */
public class Student {

    private String SID;

    private String Sname;

    private String Sage;

    private String Ssex;

    private String Sphone;

    @Override
    public String toString() {
        return "Student{" +
                "SID='" + SID + '\'' +
                ", Sname='" + Sname + '\'' +
                ", Sage='" + Sage + '\'' +
                ", Ssex='" + Ssex + '\'' +
                ", Sphone='" + Sphone + '\'' +
                '}';
    }

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getSage() {
        return Sage;
    }

    public void setSage(String sage) {
        Sage = sage;
    }

    public String getSsex() {
        return Ssex;
    }

    public void setSsex(String ssex) {
        Ssex = ssex;
    }

    public String getSphone() {
        return Sphone;
    }

    public void setSphone(String sphone) {
        Sphone = sphone;
    }
}
