package com.songsir.util;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @PackageName com.songsir.util
 * @ProjectName songsir-demoboot
 * @Auther: SongYapeng
 * @Date: Create in 16:03 2019/1/16
 * @Description:
 * @Copyright Copyright (c) 2018, songyapeng@shopin.cn All Rights Reserved.
 */
public class BaiduFaceUtil {

    // 官网获取的 API Key
    private static final String APIKEY = "jMr8yvzUbpB8YKCHLSiz7qAO";
    // 官网获取的 Secret
    private static final String APISCRET = "SROB4zQvciWKNcEpwsQTtEXogSTZPl7Y";
    // 获取token地址
    private static final String AUTHHOST = "https://aip.baidubce.com/oauth/2.0/token?";

    /**
     * @param
     * @MethodName getAuth
     * @Description 获取token
     * @Auther SongYapeng
     * @Date 2019/1/16 16:04
     * @Since JDK 1.8
     */
    public static String getAuth() {
        String clientId = APIKEY;
        String clientSecret = APISCRET;
        return getAuth(clientId, clientSecret);
    }

    private static String getAuth(String ak, String sk) {
        String getAccessTokenUrl = AUTHHOST
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            System.err.println("result:" + result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }

}
