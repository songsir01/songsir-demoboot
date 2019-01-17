package com.songsir;

import com.alibaba.fastjson.JSON;
import com.songsir.bean.TBean;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @PackageName com.songsir
 * @ProjectName songsir-demoboot
 * @Auther: SongYapeng
 * @Date: Create in 14:29 2018/12/11
 * @Description:
 * @Copyright Copyright (c) 2018, songyapeng@shopin.cn All Rights Reserved.
 */
public class MyTest {

    public static void main(String[] args) {

        String a[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
        "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","0","1","2","3",
        "4","5","6","7","8","9"};

        long start = System.currentTimeMillis();

        StringBuffer sbStr = new StringBuffer();
        for (int j = 0; j < 10000; j ++) {
            for (int i = 0; i < a.length; i ++) {
                double random = Math.random() * a.length;
                int floor = (int) Math.floor(random);
                sbStr.append(a[floor]);
            }
        }
        System.out.println("第一个执行时间：" + (System.currentTimeMillis() - start));


        long start2 = System.currentTimeMillis();

        String str = "";
        for (int j = 0; j < 10000; j ++) {
            for (int i = 0; i < a.length; i ++) {
                double random = Math.random() * a.length;
                int floor = (int) Math.floor(random);
                str += a[floor];
            }
        }
        System.out.println("第二个执行时间：" + (System.currentTimeMillis() - start2));


    }

    @Test
    public void test() {
        TBean tBean = new TBean();
        tBean.setSid("123456789");
        tBean.set$name("Tom");
        System.out.println(tBean.toString());
        System.out.println(JSON.toJSONString(tBean));
    }


    @Test
    public void test2() {
        int a = 6;
        for (int i = 0; i < 3; i ++) {
            System.out.print(a);
        }
    }
}
class Cc {
    public static void main(String[] args) {
        BigDecimal detailFtq = new BigDecimal("10");
        /*BigDecimal divide = detailFtq.multiply(new BigDecimal(1)).divide(new BigDecimal(2), 10, BigDecimal.ROUND_CEILING);
        System.out.println(divide);

        BigDecimal subtract = detailFtq.divide(new BigDecimal(2), 10, BigDecimal.ROUND_CEILING);
        System.out.println(subtract);*/

        BigDecimal salePrice = new BigDecimal("100");
        BigDecimal subtract = salePrice.subtract(detailFtq.divide(new BigDecimal(2), 10, BigDecimal.ROUND_CEILING));
        System.out.println(subtract);

        BigDecimal subtract2 = subtract.subtract(detailFtq.divide(new BigDecimal(2), 10, BigDecimal.ROUND_CEILING));
        System.out.println(subtract2);


        BigDecimal bigDecimal = new BigDecimal("1000");
        BigDecimal divide = bigDecimal.divide(new BigDecimal(1000), 10, BigDecimal.ROUND_CEILING);
        System.out.println(divide);


    }
}

class Dc {
    public static void main(String[] args) {
        String ss = "T1010100";
        String startWord = ss.substring(0, 1);
        String lastWord = ss.substring(1, ss.length());
        System.out.println((byte)startWord.toCharArray()[0] + "" + lastWord);
    }
}

class Ee {
    public static void main(String[] args) {
        for(int i = 0 ;i < 2000; i ++) {
            System.out.println(i + " " + (char)i);
        }
    }
}

