package com.songsir;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
public class SongsirApplication {

    public static void main(String[] args) {
        SpringApplication.run(SongsirApplication.class,args);
    }

}
