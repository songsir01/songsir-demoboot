package com.songsir.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.songsir.service.DubboTestProviderService;

/**
 * @PackageName com.songsir.service.impl
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 16:10 2019/12/12
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
@Service
public class DubboTestProviderServiceImpl implements DubboTestProviderService {

    @Override
    public String getHelloWorld() {
        return "Hello World!";
    }
}
