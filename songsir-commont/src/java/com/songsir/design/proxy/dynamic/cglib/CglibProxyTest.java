package com.songsir.design.proxy.dynamic.cglib;

import com.songsir.design.proxy.staticproxy.BuyCar;
import com.songsir.design.proxy.staticproxy.BuyCarImpl;

/**
 * @PackageName com.songsir.design.proxy.dynamic.cglib
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 15:09 2019/8/23
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class CglibProxyTest {
    public static void main(String[] args) {
        BuyCar buyCar = new BuyCarImpl();
        CglibProxy cglibProxy = new CglibProxy();
        BuyCarImpl instance = (BuyCarImpl) cglibProxy.getInstance(buyCar);
        instance.buyCar();
    }
}
