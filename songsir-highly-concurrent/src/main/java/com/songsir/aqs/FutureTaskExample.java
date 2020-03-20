package com.songsir.aqs;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @PackageName com.songsir.aqs
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 10:36 2020/3/20
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class FutureTaskExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("do something in callable");
                Thread.sleep(5000);
                return "Done";
            }
        });

        new Thread(futureTask).start();
        System.out.println("do something in main");
        Thread.sleep(1000);
        String result = futureTask.get();
        System.out.println("result is :" + result);

    }
}
