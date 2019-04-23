package com.songsir.controller;

import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.util.concurrent.*;

/**
 * @PackageName com.songsir.controller
 * @ProjectName songsir-demoboot
 * @Auther: SongYapeng
 * @Date: Create in 16:11 2019/2/22
 * @Description:
 * @Copyright Copyright (c) 2019, songyapeng@shopin.cn All Rights Reserved.
 */
public class SongsirNIO {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }
}

class Aaa implements Callable <Boolean> {

    @Override
    public Boolean call() throws Exception {
        System.out.println(123);
        return true;
    }
}
class Bbb implements Callable<Boolean> {

    @Override
    public Boolean call() throws Exception {
        System.out.println(345);
        return true;
    }
}
class Cc {
    public static void main(String[] args) {
        ExecutorService ex = Executors.newCachedThreadPool();
        Aaa aaa = new Aaa();
        Bbb bbb = new Bbb();
        Future<Boolean> f1 = ex.submit(bbb);
        Future<Boolean> f2 = ex.submit(aaa);
        try {
            Boolean b1 = f1.get();
            Boolean b2 = f2.get();
            System.out.println(b1);
            System.out.println(b2);
            if (b1 && b2) {
                System.out.println(555);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}