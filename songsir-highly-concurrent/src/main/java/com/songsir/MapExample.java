package com.songsir;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @PackageName com.songsir
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 18:46 2020/2/24
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
@Slf4j
public class MapExample {

    private static Map<Integer, Integer> map = new HashMap<>();

    private static int threadNum = 200;
    private static int clientNum = 5000;

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadNum);
        for (int i = 0; i < clientNum; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    semaphore.acquire();
                    func(threadNum);
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
            });
        }
        exec.shutdown();
        log.info("size: {}", map.size());
    }

    private static void func(int threadNum) {
        map.put(threadNum, threadNum);
    }
}
