package com.songsir.service;

/**
 * @PackageName com.songsir.service
 * @ProjectName songsir-demoboot
 * @Auther: SongYapeng
 * @Date: Create in 8:55 2020/6/25
 * @Description:
 * @Copyright Copyright (c) 2019, songyapeng@shopin.cn All Rights Reserved.
 */
public interface IServiceFactory {

    ITestFcService getService(String code);

}
