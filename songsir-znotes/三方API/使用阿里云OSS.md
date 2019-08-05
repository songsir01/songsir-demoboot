[toc]
# һ����ͨ�˺�

## 1��ȥע��
������ӣ�[https://help.aliyun.com/product/31815.html](https://help.aliyun.com/product/31815.html) ע�ᡣ

## 2���ɹ�����ȡ����ײ�
![���������ͼƬ����](https://img-blog.csdnimg.cn/20190111180338830.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
 - ʵ����֤��������֧����ֱ����֤
 - ��֤����ȡ����ײ�
 ![���������ͼƬ����](https://img-blog.csdnimg.cn/20190111180431455.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
 ## 3����ͨ����
  - �鿴��Ϣ�������ͨ
  ![���������ͼƬ����](https://img-blog.csdnimg.cn/20190111180604229.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
  ![���������ͼƬ����](https://img-blog.csdnimg.cn/20190111180612201.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
 ## 4���½�bucket
 ![���������ͼƬ����](https://img-blog.csdnimg.cn/20190111180711439.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
 ## 5����ȡAccessKey������
 ��1����[�����ƿ���̨](https://home.console.aliyun.com/new?spm=a2c4g.11186623.2.10.77d32974Dzitvi#/)
 ��2�������������Ϸ����û��������ڵ����Ŀ�ݲ˵���ѡ��accesskeys��
 ![���������ͼƬ����](https://img-blog.csdnimg.cn/20190111181236425.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
 ��3����һ����Ҫ�����µ�accesskeys��
 ���ɺ����£�
 ![���������ͼƬ����](https://img-blog.csdnimg.cn/20190111181352666.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
# ����ͼƬ�ϴ�
## 1��SpringBoot��Ŀ׼��

 - ʹ����ǰ���SpringBoot��Ŀ
 - ���밢����OSS����
 - ����JSP֧��
 
### ��1��SpringBoot��Ŀ

��ο�
 -  [SpringBoot��Ŀ�](https://blog.csdn.net/SongSir001/article/details/80436415)
 -  [SpringBoot������Դ����](https://blog.csdn.net/SongSir001/article/details/84875619)
 
### ��2�����밢����OSS����


```
<dependency>
    <groupId>com.aliyun.oss</groupId>
    <artifactId>aliyun-sdk-oss</artifactId>
    <version>2.8.2</version>
</dependency>
```
### ��3��SpringBoot��JSP֧��
1������tomcat��jstl��jsp����ص�֧������


```
<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-jasper</artifactId>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>javax.servlet.jsp.jstl</groupId>
    <artifactId>jstl-api</artifactId>
    <version>1.2</version>
</dependency>
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>jstl</artifactId>
</dependency>
```
2������JSP·��


```
package com.songsir.config;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @PackageName com.songsir.config
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 9:34 2019/1/15
 * @Description:
 * @Copyright Copyright (c) 2018, songyapeng@shopin.cn All Rights Reserved.
 */
public class PageMVCConfig {

    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

}

```

## 2��ҳ��׼��
### ��1������JS
����ajaxfileupload.js��jquery.js����js����ͼƬ�ϴ���
### ��2����ҳ�����

```
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/11
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="../../js/jquery-1.7.2.min.js"></script>
    <script src="../../js/ajaxfileupload.js"></script>
</head>
<body>
<input type="file" capture="camera" accept="images/*"  id="upload" name="file">

<script>
    $("#upload").live("change",upload);
    function upload(event){
        if(event.target.files.length == 1 ) {
            if (event.target.files[0].size >= 4096000) {
                alert('������ͼƬ����ӦС��4M');
            }else{
                $.ajaxFileUpload({url:'photoupload',
                    secureuri:false,
                    fileElementId:'upload',
                    dataType: 'text/html',
                    success: function(data,success){
                        alert(data);
                    },
                    error: function (data, status, e){
                        aler("�ϴ�ͼƬʧ�ܣ����Ժ����ԡ�");
                        closeLoad();
                    }
                });
            }
        }else{
            alert('���ϴ��Ĳ���ͼƬ����ѡ��ͼƬ�ϴ�');
        }
    }
</script>
</body>
</html>

```
����ṹ���£�
![���������ͼƬ����](https://img-blog.csdnimg.cn/20190115101254250.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
## 3����̨����ʵ��
### ��1��ҳ����ת
д���������ϴ�ͼƬҳ

```
/**
     * @param
     * @MethodName upload
     * @Description ҳ����ת
     * @Author SongYapeng
     * @Date 2019/1/15 9:16
     * @Since JDK 1.8
     */
    @RequestMapping("/myUpload")
    public String upload() {
        return "myUpload";
    }
```
### ��2���ϴ�ͼƬ����
1����ȡ������OSS���������Ϣ

 - ENDPOINT
 ![���������ͼƬ����](https://img-blog.csdnimg.cn/20190115101817950.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
  - ACCESSKEYID��ACCESSKEYSECRET���������ᵽ��λ�ȡ
  - BUCKETNAME
  - KEY
  �൱����Ҫ�ϴ���ͼƬ��Ŀ¼
  
  �����ҵ��������£�
  

```
public static String ENDPOINT = "http://oss-cn-beijing.aliyuncs.com";
public static String ACCESSKEYID = "LTAI9rV1x0TmtGjq";
public static String ACCESSKEYSECRET = "3QCJw4MhlyZC8zQUATKaLxWZpk4bFY";
public static String BUCKETNAME = "songsiraliyun";
public static String KEY = "images/";
```
2����ش���

```
	/**
     * @param request
     * @MethodName myphotoupload
     * @Description ͼƬ�ϴ�
     * @Author SongYapeng
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
        ret.put("msg", "����ʧ��[PU01]");
        try {
            StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
            Iterator<String> iterator = req.getFileNames();
            while (iterator.hasNext()) {
                MultipartFile file = req.getFile(iterator.next());
                fileNames = file.getOriginalFilename();
                InputStream input = file.getInputStream();

                // ����OSSClientʵ��
                OSSClient ossClient = new OSSClient(ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET);
                // �ϴ��ļ���
                ossClient.putObject(BUCKETNAME, KEY + fileNames, input);
                ossClient.shutdown();
            }
            ret.put("success", true);
            ret.put("msg", key + fileNames);
            System.out.println(("ͼƬ�ϴ������� name=" + key + fileNames));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret.getString("msg");
    }
```
# ������Ŀ����ο�
[https://github.com/songsir01/songsir-demoboot.git](https://github.com/songsir01/songsir-demoboot.git)
