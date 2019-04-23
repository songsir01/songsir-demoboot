package com.songsir.service.impl;

import com.songsir.service.ICalculator;

/**
 * @PackageName com.songsir.service.impl
 * @ProjectName songsir-demoboot
 * @Auther: SongYapeng
 * @Date: Create in 16:20 2019/4/16
 * @Description:
 * @Copyright Copyright (c) 2019, songyapeng@shopin.cn All Rights Reserved.
 */
public class CalculatorImpl implements ICalculator {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int subtract(int a, int b) {
        return a - b;
    }

    @Override
    public int multiply(int a, int b) {
        return a * b;
    }

    @Override
    public int devide(int a, int b) {
        return a / b;
    }
}
