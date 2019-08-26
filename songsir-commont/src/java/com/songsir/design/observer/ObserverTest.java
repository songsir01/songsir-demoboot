package com.songsir.design.observer;

/**
 * @PackageName com.songsir.design.observer
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 15:45 2019/8/26
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class ObserverTest {
    public static void main(String[] args) {
        WechatServer wechatServer = new WechatServer();

        User zhangsan = new User("zhangsan");
        User lisi = new User("lisi");
        User wangwu = new User("wangwu");
        // 三个用户订阅消息
        wechatServer.registerObserver(zhangsan);
        wechatServer.registerObserver(lisi);
        wechatServer.registerObserver(wangwu);
        // 微信发布消息，三个用户都收到该消息
        wechatServer.setInfomation("PHP是世界上最好的语言！！！");
        System.out.println("this is fengexian ---------------------------------");
        // 张三对该消息颇为震惊，果断取消订阅
        wechatServer.removeObserver(zhangsan);
        // 微信感觉发布言论不当，重新发布消息，但是张三已取消订阅，无法接受到该消息
        wechatServer.setInfomation("Java 才是世界上最好的语言！！！");
    }
}
