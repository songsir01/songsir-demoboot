package com.songsir.conding;


import java.util.List;
import java.util.Vector;

/**
 * @PackageName com.songsir.conding
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 16:41 2021/5/28
 * @Description:
 * @Copyright Copyright (c) 2021, songsir01@163.com All Rights Reserved.
 */
public class Recursion {

    public static void main(String[] args) {
        int n = 45;
        long l = System.currentTimeMillis();
        long ret = fib1(n);
        System.out.println("基本递归返回：" + ret + "，耗时 = " + (System.currentTimeMillis() - l));

        long l2 = System.currentTimeMillis();
        long ret2 = fib2(n);
        System.out.println("备忘录递归返回：" + ret2 + "，耗时 = " + (System.currentTimeMillis() - l2));

        long l3 = System.currentTimeMillis();
        long ret3 = fib3(n);
        System.out.println("动态规划递归返回：" + ret3 + "，耗时 = " + (System.currentTimeMillis() - l3));
    }

    public static long fib1(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fib1(n - 1) + fib1(n - 2);
    }

    public static long fib2(int n) {
        if (n < 1) {
            return 0;
        }
        long[] memo = new long[n + 1];
        for (int i = 0; i <= n; i++) {
            memo[i] = 0;
        }
        return helper(memo, n);
    }

    private static long helper(long[] memo, int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = helper(memo, n -1) + helper(memo, n - 2);
        return memo[n];
    }

    public static long fib3(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        long prev = 1L;
        long curr = 1L;
        for (int i = 3; i <= n; i++) {
            long sum = prev + curr;
            prev = curr;
            curr = sum;
        }
        return curr;
    }

}
