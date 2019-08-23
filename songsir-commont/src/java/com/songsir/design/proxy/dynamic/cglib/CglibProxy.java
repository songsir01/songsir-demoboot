package com.songsir.design.proxy.dynamic.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @PackageName com.songsir.design.proxy.dynamic.cglib
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 15:02 2019/8/23
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class CglibProxy implements MethodInterceptor {

    private Object target;

    public Object getInstance(final Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("买车前凑钱...");
        Object invoke = methodProxy.invokeSuper(o, objects);
        System.out.println("买车后去浪...");
        return invoke;
    }
}
