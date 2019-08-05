package com.songsir.service;

import com.songsir.bean.Face;
import com.songsir.bean.Student;
import com.songsir.bean.Teacher;

/**
 * @PackageName com.songsir.service
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 16:38 2018/11/28
 * @Description:
 * @Copyright Copyright (c) 2018, songyapeng@shopin.cn All Rights Reserved.
 */
public interface ISongsirService {

    Student getDemoStudent(int sid);

    Teacher getDemoTeacher(int sid);

    void savaFaceInfo(Face face);
}
