package com.songsir.bean;

/**
 * @PackageName com.songsir.bean
 * @ProjectName songsir-demoboot
 * @Auther: SongYapeng
 * @Date: Create in 16:15 2019/2/27
 * @Description:
 * @Copyright Copyright (c) 2019, songyapeng@shopin.cn All Rights Reserved.
 */
public class Student2 {
    private String SID;

    private String Sname;

    private String Sage;

    private String Ssex;

    @Override
    public String toString() {
        return "Student{" +
                "SID='" + SID + '\'' +
                ", Sname='" + Sname + '\'' +
                ", Sage='" + Sage + '\'' +
                ", Ssex='" + Ssex + '\'' +
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
}
