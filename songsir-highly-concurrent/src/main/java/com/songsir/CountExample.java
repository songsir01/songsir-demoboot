package com.songsir;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @PackageName com.songsir
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 18:22 2020/2/24
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class CountExample {

    private static Logger logger = LoggerFactory.getLogger(CountExample.class);

    private static int threadTotal = 200;
    private static int clientTotal = 5000;
    private static long count = 0;

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(threadTotal);
        for (int index = 0; index < clientTotal; index++) {
            exec.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    logger.error("Exception", e);
                }
            });
        }
        exec.shutdown();
        logger.info("count: {}", count);
    }

    private static void add() {
        count++;
    }

}
