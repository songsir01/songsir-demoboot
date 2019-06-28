package com.songsir.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @PackageName com.songsir.juc
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 16:17 2019/6/21
 * @Description:i++的线程不安全性测试
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class UnsafeDemo {

    private int cnt = 0;

    public void UnSafeAdd() {
        cnt++;
    }

    public int getCnt() {
        return cnt;
    }

    public static void main(String[] args) {
        int exePoolSize = 100;
        int forSize = 1000;
        UnsafeDemo unsafeDemo = new UnsafeDemo();
        ExecutorService exe = Executors.newFixedThreadPool(exePoolSize);
        CountDownLatch countDownLatch = new CountDownLatch(forSize);
        for (int i = 0; i < forSize; i++) {
            Runnable runnable = () -> {
                unsafeDemo.UnSafeAdd();
                countDownLatch.countDown();
            };
            exe.execute(runnable);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exe.shutdown();
        System.out.println(unsafeDemo.getCnt());
    }

}


