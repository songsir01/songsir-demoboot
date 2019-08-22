package com.songsir.设计模式.工厂模式.简单工厂;

/**
 * @PackageName com.songsir.设计模式.工厂模式.简单工厂
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 15:37 2019/8/22
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class SimpleFactorytest {
    public static void main(String[] args) {
        SimpleFactory simpleFactory = new SimpleFactory();
        Car benz = simpleFactory.getCar("Benz");
        System.out.println(benz.getName());
    }
}
