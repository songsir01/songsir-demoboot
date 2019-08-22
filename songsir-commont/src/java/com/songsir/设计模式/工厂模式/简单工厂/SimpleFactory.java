package com.songsir.设计模式.工厂模式.简单工厂;

/**
 * @PackageName com.songsir.设计模式.工厂模式.简单工厂
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 15:34 2019/8/22
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class SimpleFactory {

    public Car getCar(String name) {
        if (name.equals("Benz")) {
            return new Benz();
        } else if (name.equals("Bmw")) {
            return new Bmw();
        } else {
            System.out.println("暂时没有该汽车！");
            return null;
        }
    }
}
