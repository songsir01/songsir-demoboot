package com.songsir.test;

import com.songsir.util.FileUtils;
import org.apache.commons.lang.StringEscapeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

class TestFile {
    public static void main(String[] args) {
        String path = "C://Windows";
        long l = System.currentTimeMillis();
        List<String> allFiles = FileUtils.getAllFiles(path, "1");
        System.out.println(allFiles.size());

        System.out.println(System.currentTimeMillis() - l);

        long l2 = System.currentTimeMillis();
        List<String> list = new ArrayList<>();
        FileUtils.getAllFileName(path, list);
        System.out.println("size:" + list.size());
        System.out.println("耗时" + (System.currentTimeMillis() - l2));
    }
}

class TestLocal {
    public static void main(String[] args) {
        String[] isoCountries = Locale.getISOCountries();
        for (String isoCountry : isoCountries) {

            System.out.println(isoCountry);
        }

        Locale aDefault = Locale.getDefault();
        String country = aDefault.getCountry();
        String language = aDefault.getLanguage();
        String displayCountry = aDefault.getDisplayCountry();
        String displayLanguage = aDefault.getDisplayLanguage();
        String displayName = aDefault.getDisplayName();
        System.out.println(country);
        System.out.println(language);
        System.out.println(displayCountry);
        System.out.println(displayLanguage);
        System.out.println(displayName);
    }
}
