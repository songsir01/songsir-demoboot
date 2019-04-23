package com.songsir.controller;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import java.util.concurrent.*;

/**
 * @PackageName com.songsir.controller
 * @ProjectName songsir-demoboot
 * @Auther: SongYapeng
 * @Date: Create in 17:10 2019/4/16
 * @Description:
 * @Copyright Copyright (c) 2019, songyapeng@shopin.cn All Rights Reserved.
 */
public class Programmer {

    public static void main(String[] args) {
        /**
         * 有一串数字的字符串，每个数字以空格隔开，最后一个没有空格，现需要进行排序
         * 按数字后三位组成的大小排序，如果数字长度小于等于三位，则将原数字进行排序
         * 排序由小到大，如：12345 1111 22 33 233311 排序为 22 33 1111 233311 12345
         * 现在 数字串如下:238483 2839 2349 23 590 230 9230 82582 23 90 201332 2012
         * 请按如上规则进行排序，(排序后为：2012 23 23 90 230 9230 201332 2349 238483 82582 590 2839)
         */
        String str = "238483 2839 2349 23 590 230 9230 82582 23 90 201332 2012";
        // 将字符串使用空格分割组成数组
        String[] splitStr = str.split(" ");
        // 定义一个长度和字符串数组长度相同的数值型数组
        int[] a = new int[splitStr.length];
        // 将字符串类型的数组转为数值类型的数组 得到 a[] = {238483,2839,2349,23,590,230,9230,82582,23,90,201332,2012};
        for (int i = 0; i < splitStr.length; i++) {
            a[i] = Integer.parseInt(splitStr[i]);
        }
        // 冒泡排序
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                // 将获取到的数值转为字符串 ，s 第i位的字符，b 第i + 1位的字符
                String s = String.valueOf(a[j]);
                String b = String.valueOf(a[j + 1]);
                // ss 第i位的字符的后三位
                String ss = "";
                // bb 第i + 1位的字符后三位
                String bb = "";
                // 如果s的长度大于3，取后三位，else取s
                if (s.length() > 3) {
                    ss = s.substring(s.length() - 3, s.length());
                } else {
                    ss = s;
                }
                // 如果b的长度大于3，取后三位，else取b
                if (b.length() > 3) {
                    bb = b.substring(b.length() - 3, b.length());
                } else {
                    bb = b;
                }
                // 比较地i位的字符的后三位和低i + 1位的字符的后三位，然后相应的交换第i位和第i + 1位
                if (Integer.parseInt(ss) > Integer.parseInt(bb)) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        for (int i : a) {
            System.out.print(i);
            System.out.print(" ");
        }
    }
}

class Bbbb {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        long l = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> System.out.println(111111 + Thread.currentThread().getName()));
            executorService.submit(thread);
        }
        System.out.println(System.currentTimeMillis() - l);
        executorService.submit(new Ccccc());

//        executorService.submit(thread);

//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 25, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024));

        String c[] = {"a", "b", "c"};
        int length = c.length;
        int length1 = "aaa".length();

    }
}

class Ccccc implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + 999999);

    }
}
