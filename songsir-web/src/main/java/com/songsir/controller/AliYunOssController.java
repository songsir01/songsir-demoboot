package com.songsir.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.songsir.util.BaiduFaceUtil;
import com.songsir.util.Base64Util;
import com.songsir.util.GsonUtils;
import com.songsir.util.HttpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @PackageName com.songsir.controller
 * @ProjectName songsir-demoboot
 * @Auther: SongYapeng
 * @Date: Create in 9:15 2019/1/15
 * @Description:
 * @Copyright Copyright (c) 2018, songyapeng@shopin.cn All Rights Reserved.
 */
@Controller
public class AliYunOssController {


    public static String ENDPOINT = "http://oss-cn-beijing.aliyuncs.com";
    public static String ACCESSKEYID = "LTAI9rV1x0TmtGjq";
    public static String ACCESSKEYSECRET = "3QCJw4MhlyZC8zQUATKaLxWZpk4bFY";
    public static String BUCKETNAME = "songsirimg";
    public static String KEY = "home/img/";

    private static final String DERECTURL = "https://aip.baidubce.com/rest/2.0/face/v3/detect";

    /**
     * @param
     * @MethodName upload
     * @Description 页面跳转
     * @Auther SongYapeng
     * @Date 2019/1/15 9:16
     * @Since JDK 1.8
     */
    @RequestMapping("/myUpload")
    public String upload() {
        return "myUpload";
    }

    /**
     * @param request
     * @MethodName myphotoupload
     * @Description 图片上传
     * @Auther SongYapeng
     * @Date 2019/1/15 9:16
     * @Since JDK 1.8
     */
    @ResponseBody
    @RequestMapping(value = "/photoupload")
    public String myphotoupload(HttpServletRequest request) {
        JSONObject ret = new JSONObject();
        String key = "";
        String fileNames = "";
        ret.put("success", false);
        ret.put("successFace", false);
        ret.put("msg", "请求失败[PU01]");
        try {
            StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
            Iterator<String> iterator = req.getFileNames();
            while (iterator.hasNext()) {
                MultipartFile file = req.getFile(iterator.next());

                fileNames = file.getOriginalFilename();
                InputStream input = file.getInputStream();

                // 一下相当于复制文件流，一个供上传，另一个供人脸识别。但第一次读取InputStream对象后，第二次再读取时可能已经到Stream的结尾了（EOFException）或者Stream已经close掉了。
                // 而InputStream对象本身不能复制，因为它没有实现Cloneable接口
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = input.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                // 转化成byte[] 网络上都是 byte [] data = new byte[inputStream.available()];这种方法不可取
                byte[] data = outStream.toByteArray();
                InputStream streamForUpload = new ByteArrayInputStream(data);
                InputStream streamForFaceReact = new ByteArrayInputStream(data);

                // 创建OSSClient实例
                OSSClient ossClient = new OSSClient(ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET);
                // 上传文件流
                ossClient.putObject(BUCKETNAME, KEY + fileNames, streamForUpload);

                ossClient.shutdown();
                // 人脸识别
                faceReact(streamForFaceReact, ret);
            }
            ret.put("success", true);
            ret.put("msg", key + fileNames);
            System.out.println(("图片上传阿里云 name=" + key + fileNames));

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(ret.toString());
        return ret.toString();
    }


    /**
     * @param input
     * @param ret
     * @MethodName faceReact
     * @Description 百度人脸识别
     * @Auther SongYapeng
     * @Date 2019/1/17 10:12
     * @Since JDK 1.8
     */
    public JSONObject faceReact(InputStream input, JSONObject ret) {
        try {
            // 以下代码将文件流转为base64
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = input.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            byte[] data = outStream.toByteArray();
            String imageEncode = Base64Util.encode(data);
            Map<String, Object> map = new HashMap<>();
            // 图片的base64值
            map.put("image", imageEncode);
            // 测试类型，此处只检测人脸 年龄、颜值评分和性别
            map.put("face_field", "age,beauty,gender");
            map.put("image_type", "BASE64");
            String param = GsonUtils.toJson(map);
            // 获取token
            String accessToken = BaiduFaceUtil.getAuth();

            String result = HttpUtil.post(DERECTURL, accessToken, "application/json", param);
            JSONObject faceResult = JSONObject.parseObject(result);
            if ("SUCCESS".equals(faceResult.getString("error_msg"))) {
                JSONObject faceSonResult = JSONObject.parseObject(faceResult.getString("result"));
                JSONArray jsonArray = JSONObject.parseArray(faceSonResult.getString("face_list"));
                String age = jsonArray.getJSONObject(0).getString("age");
                String beauty = jsonArray.getJSONObject(0).getString("beauty");
                JSONObject genderObject = JSONObject.parseObject(jsonArray.getJSONObject(0).getString("gender"));
                String gender = genderObject.getString("type");
                ret.put("age", age);
                ret.put("beauty", beauty);
                ret.put("gender", gender);
                ret.put("successFace", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}
