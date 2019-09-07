package com.songsir.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @PackageName com.songsir.util
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 9:10 2019/9/7
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class FileUtils {

    private final static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private static int BUFFER_SIZE = 1024;

    /**
     * @param path
     * @MethodName fileIsExists
     * @Description 文件是否存在
     * @Author SongYapeng
     * @Date 2019/9/7 9:13
     * @Since JDK 1.8
     */
    public static boolean fileIsExists(String path) {
        File file = new File(path);
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param sourceFile
     * @param targetFile
     * @MethodName copyFile
     * @Description 复制文件
     * @Author SongYapeng
     * @Date 2019/9/7 9:36
     * @Since JDK 1.8
     */
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inputStream = null;
        BufferedOutputStream outputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(sourceFile));
            outputStream = new BufferedOutputStream(new FileOutputStream(targetFile));
            byte[] b = new byte[BUFFER_SIZE];
            int len;
            while ((len = inputStream.read(b)) != -1) {
                outputStream.write(b, 0, len);
            }
            outputStream.flush();
        } catch (Exception e) {
            logger.error("copy file error", e);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

}
