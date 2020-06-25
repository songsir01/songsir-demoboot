package com.songsir.deadlock;

import com.songsir.concurrency.ConcurrencyTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @PackageName com.songsir.deadlock
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 10:25 2020/3/22
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class deadLock implements Runnable {


    private static final Logger log = LoggerFactory.getLogger(deadLock.class);

    public int flag = 1;

    private static Object o1 = new Object();

    private static Object o2 = new Object();

    @Override
    public void run() {
        log.info("flag:{}", flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    log.info("1");
                }
            }
        }
        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    log.info("0");
                }
            }
        }
    }

    public static void main(String[] args) {
        deadLock deadLock1 = new deadLock();
        deadLock deadLock2 = new deadLock();
        deadLock1.flag = 1;
        deadLock2.flag = 0;
        new Thread(deadLock1).start();
        new Thread(deadLock2).start();
    }
}
