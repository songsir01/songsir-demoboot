package com.songsir.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.songsir.service.DubboTestProviderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @PackageName com.songsir.controller
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 9:44 2019/12/13
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
@Controller
public class SongsirDubooConsumerController {

//    @Reference(check = false)
    private DubboTestProviderService dubboTestProviderService;

    @ResponseBody
    @RequestMapping("/testDubboConsumer")
    public String testDubboConsumer() {

        return dubboTestProviderService.getHelloWorld();
    }

}
