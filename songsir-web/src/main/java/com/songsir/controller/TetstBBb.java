package com.songsir.controller;

import com.songsir.bean.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;

/**
 * @PackageName com.songsir.controller
 * @ProjectName songsir-demoboot
 * @Auther: SongYapeng
 * @Date: Create in 14:52 2019/1/25
 * @Description:
 * @Copyright Copyright (c) 2018, songyapeng@shopin.cn All Rights Reserved.
 */
public class TetstBBb extends TestAAa implements Cloneable {

    private static Logger logger = LoggerFactory.getLogger(TetstBBb.class);

    private String a;

    private int b;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "TetstBBb{" +
                "a='" + a + '\'' +
                ", b=" + b +
                '}';
    }

    @Override
    public TetstBBb clone() throws CloneNotSupportedException {
        return (TetstBBb) super.clone();
    }

    public static void main(String[] args) {

        Student student = new Student();
        student.setSID("111");
        chagneA(student);
        System.out.println(student.toString());

        int a = 2;
        String b = a != 2 ? "yes" : "no";
        System.out.println(b);

        BigDecimal bb = BigDecimal.ZERO;
        BigDecimal add = bb.add(bb);
        System.out.println(add);

    }

    private static void chagneA(Student student) {
        student.setSID("222");
    }

    public String TestAAa() {

        return "";
    }


    @Test
    public void aa() {
        BigDecimal add = new BigDecimal("0.01").add(new BigDecimal("100").divide(new BigDecimal(101), 2, BigDecimal.ROUND_FLOOR));
        System.out.println(add);
        BigDecimal divide = new BigDecimal("100").divide(new BigDecimal(101), 2, BigDecimal.ROUND_FLOOR);
        System.out.println(divide);

        HashMap<Object, Object> map = new HashMap<>();
        map.put("a", "a");
        System.out.println(map.toString());

        BigDecimal a1 = new BigDecimal("1");
        BigDecimal a2 = new BigDecimal("2");
        if (a1.compareTo(a2) > 0) {
            System.out.println(2222);
        }
        if (a2.compareTo(a1) > 0) {
            System.out.println(3333);
        }


    }


}

class Bbbbb {

    public static void main(String[] args) {

        ExecutorService ex = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 100; i++) {

            int finalI = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println(finalI);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            ex.submit(runnable);

        }

    }
}

class Ccc {
    public static void main(String[] args) {
        double a = 0.1;
        double b = 0.2;

        System.out.println(a + b);
        if (a + b > 0.3) {
            System.out.println("0.2 + 0.1 > 0.3");
        }

        float c = 0.1f;
        float d = 0.1f;
        System.out.println(c + d);

        String aa = "java";
        String cc = new String("java");
        System.out.println(aa == cc);

    }

    @Test
    public void aa() {
        int a = Integer.MAX_VALUE;
        System.out.println(a);
        System.out.println((long) Math.pow(2,31));
        System.out.println(Long.MAX_VALUE);
        System.out.println(new BigDecimal(Math.pow(2,600)));

        BigDecimal bigDecimal = new BigDecimal(100.1);
        BigDecimal bigDecimal2 = new BigDecimal("100.1");
        System.out.println(bigDecimal);
        System.out.println(bigDecimal2);

        System.out.println(Math.floor(2.1));
        System.out.println(Math.round(4.5));
        System.out.println(Math.round(4.4));
        System.out.println(Math.ceil(2.1));

        System.out.println(Math.floor(-2.1));
        System.out.println(Math.round(-4.5));
        System.out.println(Math.round(-4.6));
        System.out.println(Math.round(-4.4));
        System.out.println(Math.ceil(-2.1));

        System.out.println(Integer.MAX_VALUE);
        int i = Integer.parseInt("100000000000");
        System.out.println(i);

    }
}


