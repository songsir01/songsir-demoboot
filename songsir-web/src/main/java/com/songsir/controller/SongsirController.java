package com.songsir.controller;

import com.songsir.bean.Student;
import com.songsir.service.ISongsirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PackageName com.songsir.controller
 * @ProjectName songsir-demoboot
 * @Auther: SongYapeng
 * @Date: Create in 16:07 2018/11/28
 * @Description:
 * @Copyright Copyright (c) 2018, songyapeng@shopin.cn All Rights Reserved.
 */
@RestController
public class SongsirController {

    @Autowired
    ISongsirService songsirService;

    @RequestMapping("/firstDemo")
    public String firstDemo(String SID) {
        Student student = songsirService.getDemoDb(SID);
        System.out.println(student.toString());
        return "Hello World!";
    }
}
