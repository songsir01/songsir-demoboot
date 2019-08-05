[toc]
# һ���˺���������
## 1���˺����������
1�����û�аٶ��˺ţ�ȥ�ٶ�����һ�����еĻ����򿪰ٶ�AI����ƽ̨
[�ٶ�AI����ƽ̨](http://ai.baidu.com/)
2������ƶ�������̨��ѡ������ʶ��
![���������ͼƬ����](https://img-blog.csdnimg.cn/20190117143737389.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
3����¼�󣬸ſ����洴��Ӧ��
![���������ͼƬ����](https://img-blog.csdnimg.cn/20190117143910211.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
4�������꣬�鿴Ӧ�����飬�����API Key
��Secret Key����ʹ��

# ������̨API�Խ�
## 1����Ŀ׼��
 - ������ʹ��SpringBoot��Ŀ��������һƪʵ��ͼƬ�ϴ���������
 - �ο�����[SpringBoot�ϴ�ͼƬ��������](https://blog.csdn.net/SongSir001/article/details/86311137)
 - ����ƪ���͵Ļ����Ͽ���

## 2������������
 - [FileUtil](https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72)
 - [Base64Util](https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2)
 - [HttpUtil](https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3)
 - [GsonUtil](https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3)

## 3���½��������ȡtoken
�˴��õ������ȡ����API Key��Secret Key���������£�

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
 * @Author: SongYapeng
 * @Date: Create in 16:03 2019/1/16
 * @Description:
 * @Copyright Copyright (c) 2018, songyapeng@shopin.cn All Rights Reserved.
 */
public class BaiduFaceUtil {
    // ������ȡ�� API Key
    private static final String APIKEY = "jMr8yvzUbpB8YKCHLSiz7qAO";
    // ������ȡ�� Secret
    private static final String APISCRET = "SROB4zQvciWKNcEpwsQTtEXogSTZPl7Y";
    // ��ȡtoken��ַ
    private static final String AUTHHOST = "https://aip.baidubce.com/oauth/2.0/token?";
    /**
     * @param
     * @MethodName getAuth
     * @Description ��ȡtoken
     * @Author SongYapeng
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
                // 1. grant_typeΪ�̶�����
                + "grant_type=client_credentials"
                // 2. ������ȡ�� API Key
                + "&client_id=" + ak
                // 3. ������ȡ�� Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // �򿪺�URL֮�������
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // ��ȡ������Ӧͷ�ֶ�
            Map<String, List<String>> map = connection.getHeaderFields();
            // �������е���Ӧͷ�ֶ�
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // ���� BufferedReader����������ȡURL����Ӧ
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
            System.err.printf("��ȡtokenʧ�ܣ�");
            e.printStackTrace(System.err);
        }
        return null;
    }
}
```
## 4��������ⷽ��

```
public JSONObject faceReact(InputStream input, JSONObject ret) {
        try {
            // ���´��뽫�ļ���תΪbase64
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = input.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            byte[] data = outStream.toByteArray();
            String imageEncode = Base64Util.encode(data);
            Map<String, Object> map = new HashMap<>();
            // ͼƬ��base64ֵ
            map.put("image", imageEncode);
            // �������ͣ��˴�ֻ������� ���䡢��ֵ���ֺ��Ա�
            map.put("face_field", "age,beauty,gender");
            map.put("image_type", "BASE64");
            String param = GsonUtils.toJson(map);
            // ��ȡtoken
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
# ��������������
## 1��ҳ��
![���������ͼƬ����](https://img-blog.csdnimg.cn/20190117173145872.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
## 2���ϴ��ļ���ȡ���
![���������ͼƬ����](https://img-blog.csdnimg.cn/20190117173241798.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
�󲿷����ǵķ����߰�ʮ���ϡ�

# �ġ�����ο�
[songsir`s github](https://github.com/songsir01/songsir-demoboot.git)
���ָ��