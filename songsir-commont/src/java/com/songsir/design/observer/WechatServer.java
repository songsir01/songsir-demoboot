package com.songsir.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @PackageName com.songsir.design.observer
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 15:38 2019/8/26
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class WechatServer implements Observerable {

    private List<Observer> list;

    private String message;

    public WechatServer() {
        list = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        list.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (!list.isEmpty()) {
            list.remove(observer);
        }
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : list) {
            observer.update(message);
        }
    }

    public void setInfomation(String m) {
        this.message = m;
        System.out.println("微信更新消息：" + m);
        notifyObserver();
    }
}
