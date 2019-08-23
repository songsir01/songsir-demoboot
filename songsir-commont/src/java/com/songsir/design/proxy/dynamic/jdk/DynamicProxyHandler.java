package com.songsir.design.proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @PackageName com.songsir.design.proxy.dynamic
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 14:53 2019/8/23
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class DynamicProxyHandler implements InvocationHandler {

    private Object object;

    public DynamicProxyHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("买车前凑钱...");
        Object invoke = method.invoke(object, args);
        System.out.println("买车后去浪....");
        return invoke;
    }
}
