package com.songsir.controller;

import com.songsir.bean.Student;
import com.songsir.bean.Teacher;
import com.songsir.service.ISongsirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @PackageName com.songsir.controller
 * @ProjectName songsir-demoboot
 * @Auther: SongYapeng
 * @Date: Create in 16:07 2018/11/28
 * @Description:
 * @Copyright Copyright (c) 2018, songyapeng@shopin.cn All Rights Reserved.
 */
@Controller
public class SongsirController {

    @Autowired
    ISongsirService songsirService;

    @RequestMapping("/studentDemo")
    public String firstDemo(int sid) {
        Student student = songsirService.getDemoStudent(sid);
        System.out.println(student.toString());
        return student.toString();
    }

    @RequestMapping("/teacherDemo")
    public String teacherDemo(int sid) {
        Teacher teacher = songsirService.getDemoTeacher(sid);
        System.out.println(teacher.toString());
        return teacher.toString();
    }






}
