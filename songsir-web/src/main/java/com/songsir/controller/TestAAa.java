package com.songsir.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.songsir.bean.Student;
import com.songsir.bean.Student2;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @PackageName com.songsir.controller
 * @ProjectName songsir-demoboot
 * @Auther: SongYapeng
 * @Date: Create in 12:26 2019/2/21
 * @Description:
 * @Copyright Copyright (c) 2018, songyapeng@shopin.cn All Rights Reserved.
 */
public class TestAAa {

    private int ccc;

    private String ddd;

    public int getCcc() {
        return ccc;
    }

    public void setCcc(int ccc) {
        this.ccc = ccc;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public TestAAa clone() throws CloneNotSupportedException {
        return (TestAAa) super.clone();
    }

    public TestAAa() {
    }

    public static void main(String[] args) {
        Student student = new Student();
        bb(student);
        System.out.println(student.toString());

        String b = "abcd";
        String replace = b.replace("a", "A");
        System.out.println(b);
        System.out.println(replace);

        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("success", "false");
        resultMap.put("errorCode", "111");
        resultMap.put("memo", "啊啊啊");
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        String json = gson.toJson(resultMap);
        System.out.println(json);
    }

    private static void bb(Student student) {
        student.setSID("222");
    }



    @Test
    public void songsongs() {
        Integer a = 111;
        String b = "111";
        System.out.println(a.equals(b));
        System.out.println(a.toString().equals(b));
    }
}
