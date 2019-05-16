package com.songsir.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @packageName com.songsir.controller
 * @projectName songsir-demoboot
 * @author: SongYapeng
 * @date: Create in 11:32 2019/5/8
 * @description: 随机排序
 * @copyright Copyright (c) 2019, songyapeng@shopin.cn All Rights Reserved.
 */
public class RandomSort {
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        Map<Integer, Integer> map = new HashMap<>();
        int size = 10;
        int randomBeishu = 10;
        long l1 = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            int random = (int) Math.round(Math.random() * randomBeishu);
            while (map.containsKey(random)) {
                random = (int) Math.round(Math.random() * randomBeishu);
            }
            map.put(random, i);
        }
        System.out.println("获取随机数用时：" + (System.currentTimeMillis() - l1));
        long l2 = System.currentTimeMillis();
        int[] randomList = new int[size];
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        int b = 0;
        for (Map.Entry<Integer, Integer> entry : entries) {
            randomList[b] = entry.getKey();
            b++;
        }
        int[] clone = randomList.clone();
        System.out.println("随机数放数组耗时：" + (System.currentTimeMillis() - l2));

        long l5 = System.currentTimeMillis();
        int start = 0;
        int end = randomList.length - 1;
        quickSort(clone, start, end);
        System.out.println("快速排序耗时：" + (System.currentTimeMillis() - l5));

        int[] endList = new int[size];
        int c = 0;
        long l4 = System.currentTimeMillis();
        for (int i : randomList) {
            endList[c] = map.get(i);
            c++;
        }
        System.out.println("排序后放数组耗时：" + (System.currentTimeMillis() - l4));
        for (int i : endList) {
            System.out.print(i + ",");
        }
        System.out.println("\n总耗时：" + (System.currentTimeMillis() - l));
    }


    /**
     * @MethodName quickSort
     * @Description 快速排序
     * @Author SongYapeng
     * @Date 2019/5/16 14:44
     * @param a
     * @param left
     * @param right
     * @Since JDK 1.8
     */
    public static void quickSort(int[] a, int left, int right) {
        int start = left;
        int end = right;
        int key = a[left];
        while (end > start) {
            // 从后往前比较，如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置
            while (end > start && a[end] >= key) {
                end--;
            }
            if (a[end] <= key) {
                // 如果比基准数小交换位置
                int temp = a[end];
                a[end] = a[start];
                a[start] = temp;
            }
            // 从前往后比较，如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
            while (end > start && a[start] <= key) {
                start++;
            }
            if (a[start] >= key) {
                // 如果比基准数大交换位置
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
            // 左右探针重合后，以基准值分为左右两部分，左边都比基准值小，右边都比基准值大
        }
        // 然后递归，直到排完所有数字
        if (start > left) {
            quickSort(a, left, start - 1);
        }
        if (end < right) {
            quickSort(a, end + 1, right);
        }
    }
}
