package com.songsir.design.proxy.staticproxy;

/**
 * @PackageName com.songsir.design.proxy.staticproxy
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 14:48 2019/8/23
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class BuyCarProxy implements BuyCar {

    private BuyCar buyCar;

    public BuyCarProxy(BuyCar buyCar) {
        this.buyCar = buyCar;
    }

    @Override
    public void buyCar() {
        System.out.println("买车先凑钱...");
        buyCar.buyCar();
        System.out.println("买车后去浪...");
    }
}
