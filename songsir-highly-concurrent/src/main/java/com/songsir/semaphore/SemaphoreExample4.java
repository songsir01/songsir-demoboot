package com.songsir.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @PackageName com.songsir.aqs
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 9:29 2020/3/16
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class SemaphoreExample4 {

    private final static int threadCount = 20;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    // 尝试获取一个许可
                    if (semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)) {
                        test(threadNum);
                        // 释放一个许可
                        semaphore.release();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception {
        System.out.println("threadNum :" + threadNum);
        Thread.sleep(1000);
    }
}
