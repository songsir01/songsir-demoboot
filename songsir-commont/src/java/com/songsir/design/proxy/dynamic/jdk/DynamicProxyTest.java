package com.songsir.design.proxy.dynamic.jdk;

import com.songsir.design.proxy.staticproxy.BuyCar;
import com.songsir.design.proxy.staticproxy.BuyCarImpl;

import java.lang.reflect.Proxy;

/**
 * @PackageName com.songsir.design.proxy.dynamic
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 14:57 2019/8/23
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
        BuyCarImpl buyCar = new BuyCarImpl();
        BuyCar buyCarProxy = (BuyCar) Proxy.newProxyInstance(BuyCar.class.getClassLoader(), new Class[]{BuyCar.class}, new DynamicProxyHandler(buyCar));
        buyCarProxy.buyCar();
    }
}
