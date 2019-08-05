package com.songsir.bean;

/**
 * @PackageName com.songsir.bean
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 16:52 2019/1/23
 * @Description:
 * @Copyright Copyright (c) 2018, songyapeng@shopin.cn All Rights Reserved.
 */
public class Face {

    private Integer id;

    private String sex;

    private Integer age;

    private String score;

    private String imgUrl;

    @Override
    public String toString() {
        return "Face{" +
                "id=" + id +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", score='" + score + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
