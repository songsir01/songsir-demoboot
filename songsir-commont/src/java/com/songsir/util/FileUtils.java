package com.songsir.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    /**
     * @param path
     * @param fileType 文件类型，0 = 文件夹 1 = 文件
     * @MethodName getAllFiles
     * @Description 讀取文件夹下的，不包括子文件夹内
     * @Author SongYapeng
     * @Date 2019/9/17 15:56
     * @Since JDK 1.8
     */
    public static List<String> getAllFiles(String path, String fileType) {
        List<String> fileList = new ArrayList<>();
        File fileDic = new File(path);
        File[] files = fileDic.listFiles();
        for (File file : files) {
            if ("1".equals(fileType)) {
                if (file.isFile()) {
                    fileList.add(file.toString());
                }
            }
            if ("0".equals(fileType)) {
                if (file.isDirectory()) {
                    fileList.add(file.toString());
                }
            }
        }
        return fileList;
    }

    /**
     * @param path
     * @MethodName getFolderFiles
     * @Description 递归获取所有包括子文件夹的文件
     * @Author SongYapeng
     * @Date 2019/9/17 16:11
     * @Since JDK 1.8
     */
    public static void getAllFileName(String path, List<String> listFileName) {
        try {
            File file = new File(path);
            File[] files = file.listFiles();
            String[] names = file.list();
            if (names != null) {
                String[] completNames = new String[names.length];
                for (int i = 0; i < names.length; i++) {
                    completNames[i] = path + names[i];
                }
                listFileName.addAll(Arrays.asList(completNames));
            }
            for (File a : files) {
                // 如果文件夹下有子文件夹，获取子文件夹下的所有文件全路径。
                if (a.isDirectory()) {
                    getAllFileName(a.getAbsolutePath() + "\\", listFileName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
