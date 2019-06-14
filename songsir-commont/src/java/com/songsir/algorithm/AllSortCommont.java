package com.songsir.algorithm;

import static com.songsir.algorithm.AllSortCommont.*;

/**
 * @PackageName com.songsir.algorithm
 * @ProjectName songsir-demoboot
 * @Auther: SongYapeng
 * @Date: Create in 14:09 2019/6/10
 * @Description:
 * @Copyright Copyright (c) 2019, songyapeng@shopin.cn All Rights Reserved.
 */
public class AllSortCommont {

    public static int[] createData(int n) {
        long l = System.currentTimeMillis();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            int ss = (int) Math.floor(Math.random() * n);
            a[i] = ss;
        }
        System.out.println(n + "数据生成：");
        // 打印生成的数据
        printData(a);
        System.out.println("\n" + n + "个随机数字生成耗时：" + (System.currentTimeMillis() - l));
        return a;
    }

    /**
     * @MethodName printData
     * @Description 数据输出，中间使用...代替
     * @Auther SongYapeng
     * @Date 2019/6/10 14:12
     * @param data
     * @Since JDK 1.8
     */
    private static void printData(int[] data) {
        int i = 0;
        for (int datum : data) {
            if (i < 10 || i > data.length - 9) {
                System.out.print(datum + ",");
            } else {
                if (i > 10 && i < 14) {
                    System.out.print("...");
                }
            }
            i++;
        }
    }

    /**
     * @MethodName getSorttimesAndSize
     * @Description 定义排序的分组数
     * @Auther SongYapeng
     * @Date 2019/6/10 14:14
     * @param times
     * @Since JDK 1.8
     */
    public static int[] getSorttimesAndSize(int times) {
        int[] ns = new int[times];
        for (int m = 0; m < times; m++) {
            ns[m] = (int) Math.pow(10, m + 1);
        }
        return ns;
    }

    /**
     * @MethodName printSortData
     * @Description 排序后输出
     * @Auther SongYapeng
     * @Date 2019/6/10 14:19
     * @param data
     * @Since JDK 1.8
     */
    public static void printSortData(int[] data) {
        System.out.print(data.length + "个数据排序后：");
        printData(data);
        System.out.println("\n\t");
    }
}
class BubbleSort {
    public static void main(String[] args) {
        int times = 5;
        int[] ns = getSorttimesAndSize(times);
        for (int n : ns) {
            // 数据生成
            int[] data = createData(n);
            // 冒泡排序
            bubbleSort(data);
            // 输出数据
            printSortData(data);
        }
    }

    /**
     * @MethodName bubbleSort
     * @Description 冒泡排序
     * @Auther SongYapeng
     * @Date 2019/6/10 14:20
     * @param data
     * @Since JDK 1.8
     */
    private static void bubbleSort(int[] data) {
        int temp;
        long l = System.currentTimeMillis();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    temp = data[j + 1];
                    data[j + 1] = data[j];
                    data[j] = temp;
                }
            }
        }
        System.out.println(data.length + "个随机数字冒泡排序耗时：" + (System.currentTimeMillis() - l));
    }
}

class QuickSort {

    public static void main(String[] args) {

    }
}

class SelectSort {

    public static void main(String[] args) {

    }
}
