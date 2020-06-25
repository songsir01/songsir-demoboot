package com.songsir.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @PackageName com.songsir.concurrency
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 20:36 2020/2/25
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class ConcurrencyTest5 {

    private static final Logger log = LoggerFactory.getLogger(ConcurrencyTest5.class);

    private static AtomicIntegerFieldUpdater<ConcurrencyTest5> updater = AtomicIntegerFieldUpdater.newUpdater(ConcurrencyTest5.class, "count");

    public volatile int count = 100;

    public int getCount() {
        return count;
    }

    private static ConcurrencyTest5 example5 = new ConcurrencyTest5();

    public static void main(String[] args) {
        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success, {}", example5.getCount());
        } else {
            log.info("update failed, {}", example5.getCount());
        }
    }

}