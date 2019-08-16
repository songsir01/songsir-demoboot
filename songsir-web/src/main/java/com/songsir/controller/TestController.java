package com.songsir.controller;

import com.songsir.util.MyRedisTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.songsir.util.ThreadPoolUtils.queueToUse;

/**
 * @PackageName com.songsir.controller
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 17:29 2019/7/22
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
@Controller
public class TestController {

    @Autowired
    private MyRedisTemplate redisTemplate;

    private static Logger logger = LoggerFactory.getLogger(TestController.class);


    @RequestMapping("/testRedis")
    public void testRedis() {
        boolean set = redisTemplate.set("A", "A", 10);
        logger.info("放入redis：" + set);
        Object a = redisTemplate.get("A");
        logger.info("获取redis：" + a);
        boolean a1 = redisTemplate.delete("A");
        logger.info("删除：" + a1);
    }


    /**
     * @MethodName testAddQue
     * @Description 测试增加队列
     * @Author SongYapeng
     * @Date 2019/8/15 16:20
     * @param
     * @Since JDK 1.8
     */
    @RequestMapping("/testAddQue")
    public String testAddQue() {

        for (int i = 0; i < 100; i++) {
            int finalI = i;
            Runnable runnable = () -> {
                logger.info("test add que" + finalI);
            };
            queueToUse.add(runnable);
        }
        return "";
    }
}
