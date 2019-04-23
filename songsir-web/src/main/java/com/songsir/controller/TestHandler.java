package com.songsir.controller;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @PackageName com.songsir.controller
 * @ProjectName songsir-demoboot
 * @Auther: SongYapeng
 * @Date: Create in 16:22 2019/4/16
 * @Description:
 * @Copyright Copyright (c) 2019, songyapeng@shopin.cn All Rights Reserved.
 */
public class TestHandler implements InvocationHandler {

    private Object targetObject;

    private int useTimes;

    public Object bind(Object targetObject) {
        this.targetObject = targetObject;
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(targetObject, args);
        after();
        return result;
    }
    private void before() {
        System.out.println("we can do something before calculate");
    }
    private void after() {
        useTimes ++;
        System.out.println("已使用：" + useTimes + "次");
    }
}
