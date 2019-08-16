package com.songsir.controller;

import com.songsir.bean.Student;
import com.songsir.service.ISongsirService;
import com.songsir.util.ThreadPoolUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

import static com.songsir.util.ThreadPoolUtils.queueToUse;

/**
 * @PackageName com.songsir.controller
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 10:02 2019/8/16
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
@Controller
public class ThreadPoolController {

    private int MAX_TASK_QUEUE = 100;

    private BlockingQueue<Object> taskQueue = new ArrayBlockingQueue<>(MAX_TASK_QUEUE);

    private static Logger logger = LoggerFactory.getLogger(ThreadPoolController.class);

    @Autowired
    private ThreadPoolUtils threadPoolUtils;
    @Autowired
    ISongsirService songsirService;


    private ExecutorService initThreadPool;

    private boolean addTask(Student student) {
        logger.info("taskQueueSize: {}", taskQueue.size());
        boolean flag = false;
        try {
            flag = taskQueue.add(student);
        } catch (Exception e) {
            logger.error("add to taskQueue exception", e);
        }
        return flag;
    }

    private Student takeTask() {
        logger.info("taskQueueSize: {}", taskQueue.size());
        try {
            return (Student) taskQueue.take();
        } catch (Exception e) {
            logger.error("takeQueue error", e);
        }
        return null;
    }

    private void prinTaskBean(Student student) {
        if (student != null) {
            logger.info(student.toString());
        } else {
            logger.info("there has not bean ...");
        }
    }

    @RequestMapping("/createThread")
    public void createThread() {
        if (initThreadPool == null) {
            initThreadPool = threadPoolUtils.creatExeutorService(MAX_TASK_QUEUE, "createThread", queueToUse);
        }
        while (true) {
            Student student = takeTask();
            initThreadPool.execute(() -> prinTaskBean(student));
        }
    }

    @RequestMapping("/addTask")
    public String addTask(int sid) {
        Student student = songsirService.getDemoStudent(sid);
        addTask(student);
        return "success";
    }
}
