package com.songsir.concurrency.sync;

import com.songsir.concurrency.ConcurrencyTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @PackageName com.songsir.concurrency.sync
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 20:32 2020/2/26
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class SynchronizedExample1 {

    private static final Logger log = LoggerFactory.getLogger(SynchronizedExample1.class);

    // 修饰代码块
    public void test1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 - {}", i);
            }
        }
    }

    // 修饰方法
    public synchronized void test2() {
        for (int i = 0; i < 10; i++) {
            log.info("test2 - {}", i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 synchronizedExample1 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            synchronizedExample1.test1();
        });
        executorService.execute(() -> {
            synchronizedExample1.test1();
        });
    }
}
