package com.songsir.util;

import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @PackageName com.songsir.util
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 16:52 2019/7/22
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
@Component
public class SerializeUtil<E> {

    public String serialize(E object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);

            String str = baos.toString("ISO-8859-1");
            return URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                baos.close();
                oos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public E unserialize(String serializeStr) {
        String readStr = "";
        if (serializeStr == null || "".equals(serializeStr)) {
            return null;
        }
        try {
            readStr = URLDecoder.decode(serializeStr, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObjectInputStream ois = null;
        InputStream bais = null;
        try {
            bais = new ByteArrayInputStream(readStr.getBytes("ISO-8859-1"));
            ois = new ObjectInputStream(bais);
            return (E) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
                bais.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
