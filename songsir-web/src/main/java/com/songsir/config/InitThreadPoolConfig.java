package com.songsir.config;

import com.songsir.util.ThreadPoolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;

import static com.songsir.util.ThreadPoolUtils.queueToUse;

/**
 * @PackageName com.songsir.config
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 17:07 2019/8/15
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
@Component
public class InitThreadPoolConfig implements Runnable{


    @Autowired
    private ThreadPoolUtils threadPoolUtils;

    private ExecutorService initThreadPool;

    @PostConstruct
    public void init() {
        this.run();
    }

    @Override
    public void run() {
        if (initThreadPool == null) {
            initThreadPool = threadPoolUtils.creatExeutorService(100, "initThreadPool", queueToUse);
        }
        Runnable takeQue = null;
        try {
            if (!queueToUse.isEmpty()) {
                takeQue = queueToUse.take();
            } else {
                takeQue = () -> {

                };
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        initThreadPool.execute(takeQue);
    }
}
