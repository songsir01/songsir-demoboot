package com.songsir.service.impl;

import com.songsir.service.ITestFcService;
import org.springframework.stereotype.Service;

/**
 * @PackageName com.songsir.service.impl
 * @ProjectName songsir-demoboot
 * @Auther: SongYapeng
 * @Date: Create in 8:33 2020/6/25
 * @Description:
 * @Copyright Copyright (c) 2019, songyapeng@shopin.cn All Rights Reserved.
 */
@Service
public class TestFcServiceBImpl implements ITestFcService {
    @Override
    public String printHello() {
        return "Hello serviceB";
    }
}
