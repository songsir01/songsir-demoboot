package com.songsir.controller;

import com.songsir.service.ICalculator;
import com.songsir.service.impl.CalculatorImpl;

/**
 * @PackageName com.songsir.controller
 * @ProjectName songsir-demoboot
 * @Auther: SongYapeng
 * @Date: Create in 16:28 2019/4/16
 * @Description:
 * @Copyright Copyright (c) 2019, songyapeng@shopin.cn All Rights Reserved.
 */
public class TestProxy {
    public static void main(String[] args) {
        TestHandler proxy = new TestHandler();
        ICalculator calculator = (ICalculator) proxy.bind(new CalculatorImpl());
        int result = calculator.add(1, 2);
        System.out.println(result);
        result = calculator.subtract(3,2);
        System.out.println("result is:"+result);
        result = calculator.multiply(4,6);
        System.out.println("result is:"+result);
        result = calculator.devide(6,2);
        System.out.println("result is:"+result);
    }
}
