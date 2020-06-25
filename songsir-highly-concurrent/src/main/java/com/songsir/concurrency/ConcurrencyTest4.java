package com.songsir.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @PackageName com.songsir.concurrency
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 20:36 2020/2/25
 * @Description: 安全计数
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class ConcurrencyTest4 {

    private static final Logger log = LoggerFactory.getLogger(ConcurrencyTest4.class);


    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0, 2);
        count.compareAndSet(0, 1);
        count.compareAndSet(1, 3);
        count.compareAndSet(2, 4);
        count.compareAndSet(3, 5);
        log.info("count: {}", count);
    }

}
