package com.songsir.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @PackageName com.songsir.juc
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 16:29 2019/6/21
 * @Description:使用CAS自增
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class SafeDemo {

    private static int COREPOOLSIZE = 5;
    private static int MAXIMUMPOOLSIZE = 200;
    private static long KEEPALIVETIME = 0L;
    private static int QUESIZE = 100;
    // 拒绝策略
    // ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常, 默认策略
    // ThreadPoolExecutor.DiscardPolicy：丢弃任务不抛出异常。 
    // ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程） 
    // ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
    private static ThreadPoolExecutor.CallerRunsPolicy rejectHandler = new ThreadPoolExecutor.CallerRunsPolicy();
    // 尽量不使用Executors创建线程池，Executors有可能导致OOM发生
    public static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(COREPOOLSIZE, MAXIMUMPOOLSIZE, KEEPALIVETIME, TimeUnit.SECONDS, new LinkedBlockingDeque<>(QUESIZE), Thread::new, rejectHandler);

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger cnt = new AtomicInteger();
        int forSize = 100000;
        CountDownLatch countDownLatch = new CountDownLatch(forSize);
        for (int i = 0; i < forSize; i++) {
            Runnable runnable = () -> {
                cnt.incrementAndGet();
                countDownLatch.countDown();
            };
            poolExecutor.execute(runnable);
        }
        countDownLatch.await();
        System.out.println(cnt.get());
    }
}
