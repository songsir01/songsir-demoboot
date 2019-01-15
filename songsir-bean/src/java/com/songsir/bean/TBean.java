package com.songsir.bean;

/**
 * @PackageName com.songsir.bean
 * @ProjectName songsir-demoboot
 * @Auther: SongYapeng
 * @Date: Create in 9:40 2018/12/18
 * @Description:
 * @Copyright Copyright (c) 2018, songyapeng@shopin.cn All Rights Reserved.
 */
public class TBean {
    private String sid;
    public String $name;
    @Override
    public String toString() {
        return "TBean{" +
                "sid='" + sid + '\'' +
                ", $name='" + $name + '\'' +
                '}';
    }
    public String getSid() {
        return sid;
    }
    public void setSid(String sid) {
        this.sid = sid;
    }
    public String get$name() {
        return $name;
    }
    public void set$name(String $name) {
        this.$name = $name;
    }
}
