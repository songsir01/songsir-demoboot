package com.songsir.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * @PackageName com.songsir.bean
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 17:07 2019/7/9
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
@ToString
public class LombokBean {

    /**
     * idea 下载lombok插件，重启；setting --> Build... ---> Annotation Processors ---> 选中 enable annotation processing
     * 然后就可以使用lombok注解
     */


    @Setter
    @Getter
    private int id;

    private String name;

    private Map hashMap;


}
