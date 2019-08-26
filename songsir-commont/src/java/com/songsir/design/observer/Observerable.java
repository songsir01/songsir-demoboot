package com.songsir.design.observer;

/**
 * @PackageName com.songsir.design.observer
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 15:31 2019/8/26
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public interface Observerable {

    public void registerObserver(Observer observer);

    public void removeObserver(Observer observer);

    public void notifyObserver();

}
