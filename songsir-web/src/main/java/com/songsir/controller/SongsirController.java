package com.songsir.controller;

import com.songsir.bean.Student;
import com.songsir.bean.Teacher;
import com.songsir.service.ISongsirService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @PackageName com.songsir.controller
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 16:07 2018/11/28
 * @Description:
 * @Copyright Copyright (c) 2018, songyapeng@shopin.cn All Rights Reserved.
 */
@Controller
public class SongsirController {

    private static Logger logger = LoggerFactory.getLogger(SongsirController.class);

    @Autowired
    ISongsirService songsirService;

    @RequestMapping("/studentDemo")
    public String firstDemo(int sid, HttpServletRequest request) {
        Student student = songsirService.getDemoStudent(sid);
        logger.info(student.toString());
        request.setAttribute("sphone", student.getSphone());
        return "tiaoxingma";
    }

    @RequestMapping("/teacherDemo")
    public String teacherDemo(int sid) {
        Teacher teacher = songsirService.getDemoTeacher(sid);
        logger.info(teacher.toString());
        return teacher.toString();
    }


    public static void main(String[] args) {
        Map<String, Student> stuMap = new HashMap<>();
        Student s1 = new Student();
        s1.setSname("song1");
        Student s2 = new Student();
        s2.setSname("song2");
        stuMap.put("song1", s1);
        stuMap.put("song2", s2);
        String sname = stuMap.values().stream().findFirst().orElse(new Student()).getSname();
        System.out.println(sname);
    }


}
