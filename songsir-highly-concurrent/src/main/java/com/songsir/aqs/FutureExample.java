package com.songsir.aqs;

import java.util.concurrent.*;

/**
 * @PackageName com.songsir.aqs
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 10:29 2020/3/20
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class FutureExample {


    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {

            System.out.println("do something in callable");
            Thread.sleep(5000);
            return "Done";
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());
        System.out.println("do something in main");
        Thread.sleep(1000);
        String result = future.get();
        System.out.println("result is :" + result);

    }
}
