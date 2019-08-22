package com.songsir.设计模式.单例模式;

/**
 * @PackageName com.songsir.设计模式
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 9:23 2019/6/30
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class SingleTonAll {

}

/**
 * @PackageName com.songsir.设计模式
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 9:23 2019/6/30
 * @Description: 非线程安全懒汉式
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
class SingleTon1 {

    private SingleTon1() {

    }

    private static SingleTon1 singleTon;

    public static SingleTon1 getSingleTon() {
        if (singleTon == null) {
            singleTon = new SingleTon1();
        }
        return singleTon;
    }

}

/**
 * @PackageName com.songsir.设计模式
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 9:23 2019/6/30
 * @Description: 懒汉式线程安全
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
class SingleTon2 {

    private static SingleTon2 singleTon;

    private SingleTon2() {

    }

    public static synchronized SingleTon2 getSingleTon() {
        if (singleTon == null) {
            singleTon = new SingleTon2();
        }
        return singleTon;
    }

}

/**
 * @PackageName com.songsir.设计模式
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 9:23 2019/6/30
 * @Description: 双重校验锁
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
class SingleTon3 {

    private volatile static SingleTon3 singleTon;

    private SingleTon3() {

    }

    public static SingleTon3 getSingleTon() {
        if (singleTon == null) {
            synchronized (SingleTon3.class) {
                if (singleTon == null) {
                    singleTon = new SingleTon3();
                }
            }
        }
        return singleTon;
    }

}
