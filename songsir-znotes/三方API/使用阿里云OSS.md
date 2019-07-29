[toc]
# 一、开通账号

## 1、去注册
点击链接：[https://help.aliyun.com/product/31815.html](https://help.aliyun.com/product/31815.html) 注册。

## 2、成功后领取免费套餐
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190111180338830.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
 - 实名认证，可以用支付宝直接认证
 - 认证完领取免费套餐
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190111180431455.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
 ## 3、开通激活
  - 查看消息，点击开通
  ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190111180604229.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
  ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190111180612201.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
 ## 4、新建bucket
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190111180711439.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
 ## 5、获取AccessKey，备用
 （1）打开[阿里云控制台](https://home.console.aliyun.com/new?spm=a2c4g.11186623.2.10.77d32974Dzitvi#/)
 （2）将鼠标放在右上方的用户名区域，在弹出的快捷菜单中选择accesskeys。
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190111181236425.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
 （3）第一次需要生成新的accesskeys。
 生成后如下：
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190111181352666.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
# 二、图片上传
## 1、SpringBoot项目准备

 - 使用以前搭建的SpringBoot项目
 - 引入阿里云OSS依赖
 - 配置JSP支持
 
### （1）SpringBoot项目

请参考
 -  [SpringBoot项目搭建](https://blog.csdn.net/SongSir001/article/details/80436415)
 -  [SpringBoot多数据源配置](https://blog.csdn.net/SongSir001/article/details/84875619)
 
### （2）引入阿里云OSS依赖


```
<dependency>
    <groupId>com.aliyun.oss</groupId>
    <artifactId>aliyun-sdk-oss</artifactId>
    <version>2.8.2</version>
</dependency>
```
### （3）SpringBoot对JSP支持
1）引入tomcat、jstl、jsp等相关的支持依赖


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
2）配置JSP路径


```
package com.songsir.config;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @PackageName com.songsir.config
 * @ProjectName songsir-demoboot
 * @Auther: SongYapeng
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

## 2、页面准备
### （1）引入JS
引入ajaxfileupload.js和jquery.js两个js辅助图片上传。
### （2）简单页面代码

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
                alert('您这张图片过大，应小于4M');
            }else{
                $.ajaxFileUpload({url:'photoupload',
                    secureuri:false,
                    fileElementId:'upload',
                    dataType: 'text/html',
                    success: function(data,success){
                        alert(data);
                    },
                    error: function (data, status, e){
                        aler("上传图片失败，请稍后重试。");
                        closeLoad();
                    }
                });
            }
        }else{
            alert('您上传的不是图片，请选择图片上传');
        }
    }
</script>
</body>
</html>

```
具体结构如下：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190115101254250.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
## 3、后台方法实现
### （1）页面跳转
写方法跳到上传图片页

```
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
```
### （2）上传图片代码
1）获取阿里云OSS相关配置信息

 - ENDPOINT
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190115101817950.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)
  - ACCESSKEYID和ACCESSKEYSECRET上文中已提到如何获取
  - BUCKETNAME
  - KEY
  相当于需要上传的图片的目录
  
  其中我的配置如下：
  

```
public static String ENDPOINT = "http://oss-cn-beijing.aliyuncs.com";
public static String ACCESSKEYID = "LTAI9rV1x0TmtGjq";
public static String ACCESSKEYSECRET = "3QCJw4MhlyZC8zQUATKaLxWZpk4bFY";
public static String BUCKETNAME = "songsiraliyun";
public static String KEY = "images/";
```
2）相关代码

```
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
        ret.put("msg", "请求失败[PU01]");
        try {
            StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
            Iterator<String> iterator = req.getFileNames();
            while (iterator.hasNext()) {
                MultipartFile file = req.getFile(iterator.next());
                fileNames = file.getOriginalFilename();
                InputStream input = file.getInputStream();

                // 创建OSSClient实例
                OSSClient ossClient = new OSSClient(ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET);
                // 上传文件流
                ossClient.putObject(BUCKETNAME, KEY + fileNames, input);
                ossClient.shutdown();
            }
            ret.put("success", true);
            ret.put("msg", key + fileNames);
            System.out.println(("图片上传阿里云 name=" + key + fileNames));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret.getString("msg");
    }
```
# 三、项目代码参考
[https://github.com/songsir01/songsir-demoboot.git](https://github.com/songsir01/songsir-demoboot.git)
