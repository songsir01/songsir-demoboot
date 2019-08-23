package com.songsir.design.proxy.staticproxy;

/**
 * @PackageName com.songsir.design.proxy.staticproxy
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 14:50 2019/8/23
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class ProxyTest {
    public static void main(String[] args) {
        BuyCarImpl buyCar = new BuyCarImpl();
        buyCar.buyCar();
        BuyCarProxy buyCarProxy = new BuyCarProxy(buyCar);
        buyCarProxy.buyCar();
    }
}
