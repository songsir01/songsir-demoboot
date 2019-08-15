package com.songsir.util;

import java.util.concurrent.*;

/**
 * @PackageName com.songsir.util
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 17:12 2019/8/6
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class ThreadPoolUtils {

    /**
     * @param poolSize
     * @param method
     * @MethodName creatExeutorService
     * @Description 创建线程池方法
     * @Auther SongYapeng
     * @Date 2019/8/6 17:19
     * @Since JDK 1.8
     */
    public static ExecutorService creatExeutorService(int poolSize, String method, BlockingQueue<Runnable> queueToUse) {
        int coreSize = Runtime.getRuntime().availableProcessors();
        if (poolSize < coreSize) {
            coreSize = poolSize;
        }
        ThreadFactory threadFactory = r -> {
            Thread t = new Thread(r, "thread created mewthod{" + method + "}");
            t.setDaemon(true);
            return t;
        };
        final ThreadPoolExecutor executor = new ThreadPoolExecutor(coreSize, poolSize, 60, TimeUnit.SECONDS, queueToUse, threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
}
