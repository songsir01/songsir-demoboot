package com.songsir.singleton;

/**
 * @PackageName com.songsir.singleton
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 21:28 2020/2/27
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class ThreadSafeSingleTonUseEnum {


    private ThreadSafeSingleTonUseEnum() {

    }

    public static ThreadSafeSingleTonUseEnum getInstance() {
        return SingleTon.INSTANCE.getInstance();
    }

    private enum SingleTon {
        INSTANCE;

        private ThreadSafeSingleTonUseEnum threadSafeSingleTonUseEnum;

        // JVM保证这个方法绝对只调用一次
        SingleTon() {
            threadSafeSingleTonUseEnum = new ThreadSafeSingleTonUseEnum();
        }

        public ThreadSafeSingleTonUseEnum getInstance() {
            return  threadSafeSingleTonUseEnum;
        }
    }
}
