package com.songsir.bean;

/**
 * @PackageName com.songsir.bean
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 10:39 2018/12/17
 * @Description:
 * @Copyright Copyright (c) 2018, songyapeng@shopin.cn All Rights Reserved.
 */
public class TestBean {

    private String $name;

    private String $age;

    public String get$name() {
        return $name;
    }

    public void set$name(String $name) {
        this.$name = $name;
    }

    public String get$age() {
        return $age;
    }

    public void set$age(String $age) {
        this.$age = $age;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "$name='" + $name + '\'' +
                ", $age='" + $age + '\'' +
                '}';
    }
}
