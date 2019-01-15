package com.songsir;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @PackageName com.songsir
 * @ProjectName songsir-demoboot
 * @Auther: SongYapeng
 * @Date: Create in 16:05 2018/11/28
 * @Description:
 * @Copyright Copyright (c) 2018, songyapeng@shopin.cn All Rights Reserved.
 */
@SpringBootApplication
@MapperScan("com.songsir.dao")
public class SongsirApplication extends SpringBootServletInitializer implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SongsirApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SongsirApplication.class);
    }

    @Override
    public void run(String... args) {
        System.out.println("启动完成！");
    }
}
