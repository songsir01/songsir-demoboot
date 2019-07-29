[toc]
# 一、账号申请配置
## 1、账号申请和配置
1、如果没有百度账号，去百度申请一个。有的话，打开百度AI开放平台
[百度AI开放平台](http://ai.baidu.com/)
2、鼠标移动到控制台，选择人脸识别
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190117143737389.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
3、登录后，概况里面创建应用
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190117143910211.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
4、创建完，查看应用详情，保存好API Key
和Secret Key后续使用

# 二、后台API对接
## 1、项目准备
 - 本案例使用SpringBoot项目，并在上一篇实现图片上传到阿里云
 - 参考博客[SpringBoot上传图片到阿里云](https://blog.csdn.net/SongSir001/article/details/86311137)
 - 在上篇博客的基础上开发

## 2、工具类下载
 - [FileUtil](https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72)
 - [Base64Util](https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2)
 - [HttpUtil](https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3)
 - [GsonUtil](https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3)

## 3、新建工具类获取token
此处用到上面获取到的API Key和Secret Key，代码如下：

```
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
```
## 4、人脸检测方法

```
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
```
# 三、人脸检测测试
## 1、页面
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190117173145872.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
## 2、上传文件获取结果
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190117173241798.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
大部分明星的分数七八十以上。

# 四、代码参考
[songsir`s github](https://github.com/songsir01/songsir-demoboot.git)
请多指教