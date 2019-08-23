package com.songsir.design.factory.factorymethod;

/**
 * @PackageName com.songsir.design.factory.factorymethod
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 15:42 2019/8/22
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class FactoryTest {
    public static void main(String[] args) {
        BenzFactory benzFactory = new BenzFactory();
        System.out.println(benzFactory.getCar().getName());
        BmwFactory bmwFactory = new BmwFactory();
        System.out.println(bmwFactory.getCar().getName());
    }
}
