package com.songsir.test;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * @PackageName com.songsir.test
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 15:42 2019/8/28
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class SongsirTest {
    public static void main(String[] args) {
        String s = "&#35746;&#21333;&#21015;&#34920;";
        System.out.println(StringEscapeUtils.escapeHtml(s));
        System.out.println(StringEscapeUtils.unescapeHtml("&#35746;&#21333;&#21015;&#34920;"));
    }
}
