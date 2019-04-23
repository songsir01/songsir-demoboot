package com.songsir.controller;

/**
 * @PackageName com.songsir.controller
 * @ProjectName songsir-demoboot
 * @Auther: SongYapeng
 * @Date: Create in 10:27 2019/2/22
 * @Description:
 * @Copyright Copyright (c) 2019, songyapeng@shopin.cn All Rights Reserved.
 */
interface Playable {
    void play();
}

interface Bounceable {
    void play();
}

interface Rollable extends Playable, Bounceable {
    Ball ball = new Ball("PingPang");
}

class Ball implements Rollable {
    private String name;

    public String getName() {
        return name;
    }

    public Ball(String name) {
        this.name = name;
    }

    public void play() {

        System.out.println(ball.getName());
    }
}
