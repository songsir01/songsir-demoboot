package com.songsir.lock;

import com.songsir.concurrency.ConcurrencyTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @PackageName com.songsir.concurrency
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 20:36 2020/2/25
 * @Description: 不安全的计数
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class LockExample1 {

    private static final Logger log = LoggerFactory.getLogger(LockExample1.class);

    public static int clientTotal = 5000;

    public static int threadTatal = 200;

    public static int count = 0;

    private final static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTatal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("excuption", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count: {}", count);
    }

    private static void add() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

}
