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
/**
 * @PackageName com.songsir.algorithm
 * @ProjectName songsir-demoboot
 * @Auther: SongYapeng
 * @Date: Create in 14:09 2019/6/28
 * @Description: 冒泡排序
 * @Copyright Copyright (c) 2019, songyapeng@shopin.cn All Rights Reserved.
 */
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

/**
 * @PackageName com.songsir.algorithm
 * @ProjectName songsir-demoboot
 * @Auther: SongYapeng
 * @Date: Create in 14:09 2019/6/28
 * @Description: 快速排序
 * @Copyright Copyright (c) 2019, songyapeng@shopin.cn All Rights Reserved.
 */
class QuickSort {

    public static void main(String[] args) {
        int times = 5;
        int[] ns = getSorttimesAndSize(times);
        for (int n : ns) {
            int[] data = createData(n);
            long l = System.currentTimeMillis();
            quickSort(data, 0, data.length - 1);
            System.out.println(data.length + "个随机数字快速排序耗时：" + (System.currentTimeMillis() - l));
            printSortData(data);
        }
    }

    private static void quickSort(int[] data, int left, int right) {
        int start = left;
        int end = right;
        int key = data[left];
        while (end > start) {
            while (end > start && data[end] >= key) {
                end--;
            }
            if (data[end] < key) {
                int temp = data[end];
                data[end] = data[start];
                data[start] = temp;
            }
            while (end > start && data[start] <= key) {
                start++;
            }
            if (data[start] >= key) {
                int temp = data[start];
                data[start] = data[end];
                data[end] = temp;
            }
        }
        if (start > left) {
            quickSort(data, left, start - 1);
        }
        if (end < right) {
            quickSort(data, end + 1, right);
        }
    }
}

/**
 * @PackageName com.songsir.algorithm
 * @ProjectName songsir-demoboot
 * @Auther: SongYapeng
 * @Date: Create in 14:09 2019/6/28
 * @Description: 选择排序
 * @Copyright Copyright (c) 2019, songyapeng@shopin.cn All Rights Reserved.
 */
class SelectSort {

    public static void main(String[] args) {

    }

}
