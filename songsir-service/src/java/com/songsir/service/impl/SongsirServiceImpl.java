package com.songsir.service.impl;

import com.songsir.bean.Student;
import com.songsir.bean.Teacher;
import com.songsir.dao.mapper.SongsirMapper;
import com.songsir.dao.mapper2.SongsirTeacherMapper;
import com.songsir.service.ISongsirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @PackageName com.songsir.service.impl
 * @ProjectName songsir-demoboot
 * @Auther: SongYapeng
 * @Date: Create in 16:38 2018/11/28
 * @Description:
 * @Copyright Copyright (c) 2018, songyapeng@shopin.cn All Rights Reserved.
 */
@Service
public class SongsirServiceImpl implements ISongsirService {

    @Autowired
    SongsirMapper songsirMapper;
    @Autowired
    SongsirTeacherMapper songsirTeacherMapper;

    @Override
    public Student getDemoStudent(int sid) {
        return songsirMapper.getDemoStudent(sid);
    }

    @Override
    public Teacher getDemoTeacher(int sid) {
        return songsirTeacherMapper.getTeacher(sid);
    }

}
