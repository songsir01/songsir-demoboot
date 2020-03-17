package com.songsir.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @PackageName com.songsir.concurrency
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 20:36 2020/2/25
 * @Description: 不安全的计数
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
@Slf4j
public class LockExample2 {


    private final Map<String, Data> map = new TreeMap();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    public Data get(String key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys() {
        readLock.lock();
        try {
            return map.keySet();
        } finally {
            readLock.unlock();
        }
    }

    public Data pub(String key, Data value) {
        writeLock.lock();
        try {
            return map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    class Data {

    }

}
