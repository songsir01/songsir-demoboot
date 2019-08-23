package com.songsir.design.proxy.staticproxy;

/**
 * @PackageName com.songsir.design.proxy.staticproxy
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 14:47 2019/8/23
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class BuyCarImpl implements BuyCar {
    @Override
    public void buyCar() {
        System.out.println("买车了。。");
    }
}
