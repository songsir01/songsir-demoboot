package com.songsir.algorithm;

import static com.songsir.algorithm.AllSortCommont.*;

/**
 * @PackageName com.songsir.algorithm
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
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
        System.out.print(n + "个数据生成：");
        // 打印生成的数据
        printData(a);
        System.out.println("\n" + n + "个随机数字生成耗时：" + (System.currentTimeMillis() - l));
        return a;
    }

    /**
     * @param data
     * @MethodName printData
     * @Description 数据输出，中间使用...代替
     * @Author SongYapeng
     * @Date 2019/6/10 14:12
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
     * @param times
     * @MethodName getSorttimesAndSize
     * @Description 定义排序的分组数
     * @Author SongYapeng
     * @Date 2019/6/10 14:14
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
     * @param data
     * @MethodName printSortData
     * @Description 排序后输出
     * @Author SongYapeng
     * @Date 2019/6/10 14:19
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
 * @Author: SongYapeng
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
     * @param data
     * @MethodName bubbleSort
     * @Description 冒泡排序
     * @Author SongYapeng
     * @Date 2019/6/10 14:20
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
 * @Author: SongYapeng
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
 * @Author: SongYapeng
 * @Date: Create in 14:09 2019/6/28
 * @Description: 选择排序
 * @Copyright Copyright (c) 2019, songyapeng@shopin.cn All Rights Reserved.
 */
class SelectSort {

    public static void main(String[] args) {
        // 排序次数现骨干
        int times = 6;
        int[] ns = getSorttimesAndSize(times);
        for (int n : ns) {
            // 数据生成
            int[] data = createData(n);
            // 选择排序
            selectSort(data);
            // 输出结果
            printSortData(data);
        }
    }

    private static void selectSort(int[] data) {
        long l = System.currentTimeMillis();
        int k;
        int temp;
        for (int i = 0; i < data.length; i++) {
            k = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[k] > data[j]) {
                    k = j;
                }
            }
            if (k != i) {
                temp = data[i];
                data[i] = data[k];
                data[k] = temp;
            }
        }
        System.out.println(data.length + "个随机数字选择排序耗时：" + (System.currentTimeMillis() - l));
    }

}

/**
 * @PackageName com.songsir.algorithm
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 14:09 2019/7/3
 * @Description: 插入排序
 * @Copyright Copyright (c) 2019, songyapeng@shopin.cn All Rights Reserved.
 */
class InsertSort {

    public static void main(String[] args) {
        int times = 6;
        int[] ns = getSorttimesAndSize(times);
        for (int n : ns) {
            // 数据生成
            int[] data = createData(n);
            // 排序
            insertSort(data);
            // 打印输出
            printSortData(data);
        }
    }

    /**
     * @param data
     * @MethodName insertSort
     * @Description 插入排序
     * @Author SongYapeng
     * @Date 2019/7/3 17:28
     * @Since JDK 1.8
     */
    private static void insertSort(int[] data) {
        int length = data.length;
        for (int out = 1; out < length; out++) {
            int temp = data[out];
            int in = out;
            while (in - 1 >= 0 && data[in - 1] > temp) {
                data[in] = data[in - 1];
                in--;
            }
            if (in != out) {
                data[in] = temp;
            }
        }
    }

}

/**
 * @PackageName com.songsir.algorithm
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 14:09 2019/7/5
 * @Description: 希尔排序
 * @Copyright Copyright (c) 2019, songyapeng@shopin.cn All Rights Reserved.
 */
class ShellSort {

    public static void main(String[] args) {
        int times = 6;
        int[] ns = getSorttimesAndSize(times);
        for (int n : ns) {
            int[] data = createData(n);
            long l = System.currentTimeMillis();
            shellSort(data);
            System.out.println(data.length + "个随机数字希尔排序耗时：" + (System.currentTimeMillis() - l));
            printSortData(data);
        }
    }

    private static void shellSort(int[] data) {
        int length = data.length;
        int h = 1;
        while (h < length / 3) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            for (int out = h; out < length; out++) {
                int temp = data[out];
                int in = out;
                while (in - h >= 0 && data[in - h] > temp) {
                    data[in] = data[in - h];
                    in = in - h;
                }
                if (in != out) {
                    data[in] = temp;
                }
            }
            h = (h - 1) / 3;
        }
    }

}
