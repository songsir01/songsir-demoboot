package com.songsir.design.factory.factorymethod;

import com.songsir.design.factory.simplefactory.Bmw;
import com.songsir.design.factory.simplefactory.Car;

/**
 * @PackageName com.songsir.design.factory.factorymethod
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 15:41 2019/8/22
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class BmwFactory implements Factory {
    @Override
    public Car getCar() {
        return new Bmw();
    }
}
