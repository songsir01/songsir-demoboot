package com.songsir.设计模式.工厂模式.工厂方法;

import com.songsir.设计模式.工厂模式.简单工厂.Benz;
import com.songsir.设计模式.工厂模式.简单工厂.Car;

/**
 * @PackageName com.songsir.设计模式.工厂模式.工厂方法
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 15:41 2019/8/22
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class BenzFactory implements Factory {
    @Override
    public Car getCar() {
        return new Benz();
    }
}
