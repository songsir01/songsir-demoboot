package com.songsir.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @PackageName com.songsir.schedule
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 15:29 2019/7/9
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
@Configuration
@EnableScheduling
@PropertySource(value = "classpath:/schedule.properties")
public class SongsriTestJob {

    private static Logger logger = LoggerFactory.getLogger(SongsriTestJob.class);


    @Scheduled(cron = "${songsirTestJob}")
    public void work() {
//        logger.info("the task is running....");
    }

}
