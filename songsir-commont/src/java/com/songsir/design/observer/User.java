package com.songsir.design.observer;

/**
 * @PackageName com.songsir.design.observer
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 15:42 2019/8/26
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class User implements Observer {

    private String name;

    private String message;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String msg) {
        this.message = msg;
        read();
    }

    private void read() {
        System.out.println(name + " 收到消息：" + message);
    }
}
