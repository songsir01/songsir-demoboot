package com.songsir.service.impl;

import com.songsir.service.ITestFcService;
import com.songsir.service.IServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @PackageName com.songsir.service.impl
 * @ProjectName songsir-demoboot
 * @Auther: SongYapeng
 * @Date: Create in 8:29 2020/6/25
 * @Description:
 * @Copyright Copyright (c) 2019, songyapeng@shopin.cn All Rights Reserved.
 */
@Service
public class ServiceFactoryImpl implements IServiceFactory {

    @Qualifier("testFcServiceAImpl")
    @Autowired
    private ITestFcService factoryServiceA;
    @Qualifier("testFcServiceBImpl")
    @Autowired
    private ITestFcService factoryServiceB;

    private Map<String, ITestFcService> serviceMap = new HashMap<>();

    public ITestFcService getService(String code) {
        initServiceMap();
        return serviceMap.get(code);
    }

    private void initServiceMap() {
        serviceMap.put("1", factoryServiceA);
        serviceMap.put("2", factoryServiceB);
    }


}
