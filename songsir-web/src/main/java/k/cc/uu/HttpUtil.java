package k.cc.uu;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class HttpUtil {

    private static Logger log = LoggerFactory.getLogger(HttpUtil.class);

    public static String HttpPost(String url, String method, Map paramMap) {
        String encoding = "UTF-8";
        String webUrl = url + "/" + method;
        log.info(webUrl);
        if (encoding == null || "".equals(encoding)) {
            encoding = "UTF-8";
        }
        StringBuffer sBuffer = new StringBuffer();
        //构造HttpClient的实例
        HttpClient httpClient = new HttpClient();
        //httpClient.set
        //创建POS方法的实例
        NameValuePair[] pairs = null;
        PostMethod postMethod = new PostMethod(webUrl);
        if (paramMap != null) {
            pairs = new NameValuePair[paramMap.size()];
            Set set = paramMap.keySet();
            Iterator it = set.iterator();
            int i = 0;
            while (it.hasNext()) {
                Object key = it.next();
                Object value = paramMap.get(key);
                pairs[i] = new NameValuePair(key.toString(), value.toString());
                i++;
            }
            postMethod.setRequestBody(pairs);
        }
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);

        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(20000); //连接20秒超时
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(40000);//读取40秒超时
        try {
            //执行getMethod
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + postMethod.getStatusLine());
                sBuffer = new StringBuffer();
            } else {
                InputStream resStream = postMethod.getResponseBodyAsStream();
                sBuffer = new StringBuffer(postMethod.getResponseBodyAsString() + "");
            }
        } catch (HttpException e) {
            //发生致命的异常，可能是协议不对或者返回的内容有问题
            System.out.println("Please check your provided http address!");
            e.printStackTrace();
        } catch (IOException e) {
            //发生网络异常
            e.printStackTrace();
        } finally {
            //释放连接
            postMethod.releaseConnection();
        }
        return sBuffer.toString();
    }


    public static String HttpPostToMap(String webUrl, Map paramMap) {
        String encoding = "UTF-8";
        log.info(webUrl);
        if (encoding == null || "".equals(encoding)) encoding = "UTF-8";
        StringBuffer sBuffer = new StringBuffer();
        //构造HttpClient的实例
        HttpClient httpClient = new HttpClient();
        //httpClient.set
        //创建POS方法的实例
        NameValuePair[] pairs = null;
        PostMethod postMethod = new PostMethod(webUrl);
        if (paramMap != null) {
            pairs = new NameValuePair[paramMap.size()];
            Set set = paramMap.keySet();
            Iterator it = set.iterator();
            int i = 0;
            while (it.hasNext()) {
                Object key = it.next();
                Object value = paramMap.get(key);
                pairs[i] = new NameValuePair(key.toString(), value.toString());
                i++;
            }
            postMethod.setRequestBody(pairs);
        }
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(50000); //连接50秒超时
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(60000);//读取60秒超时
        try {
            //执行getMethod
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + postMethod.getStatusLine());
                sBuffer = new StringBuffer();
            } else {
                InputStream resStream = postMethod.getResponseBodyAsStream();
                sBuffer = new StringBuffer(postMethod.getResponseBodyAsString() + "");
            }
        } catch (HttpException e) {
            //发生致命的异常，可能是协议不对或者返回的内容有问题
            System.out.println("Please check your provided http address!");
            e.printStackTrace();
        } catch (IOException e) {
            //发生网络异常
            e.printStackTrace();
        } finally {
            //释放连接
            postMethod.releaseConnection();
        }
        return sBuffer.toString();
    }

    public static String HttpPostForlogistics(String url, String method, String str) {
        String encoding = "UTF-8";
        String webUrl = url + "/" + method;
        if (encoding == null || "".equals(encoding)) encoding = "UTF-8";
        StringBuffer sBuffer = new StringBuffer();
        //构造HttpClient的实例
        HttpClient httpClient = new HttpClient();
        //创建POS方法的实例
        PostMethod postMethod = new PostMethod(webUrl);
        try {
//					postMethod.setRequestEntity(new ByteArrayRequestEntity(str.getBytes(),"application/json; charset=utf-8"));
            postMethod.setRequestBody(str);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10000); //连接5秒超时
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(30000);//读取30秒超时
//			  	postMethod.setRequestHeader("Content-type", "application/json; charset=utf-8");
//			  	postMethod.setDoAuthentication(false);
//			  	postMethod.addRequestHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");

        postMethod.setDoAuthentication(true);
        postMethod.addRequestHeader("Authorization", "Basic U0FQX0FNOkFubmllTWFvMTIzNA==");
        postMethod.setRequestHeader("Content-type", "application/json");
        try {
            //执行getMethod
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + postMethod.getStatusLine());
                sBuffer = new StringBuffer();
            } else {
                InputStream resStream = postMethod.getResponseBodyAsStream();
                sBuffer = new StringBuffer(postMethod.getResponseBodyAsString() + "");
            }
        } catch (HttpException e) {
            //发生致命的异常，可能是协议不对或者返回的内容有问题
            System.out.println("Please check your provided http address!");
            e.printStackTrace();
        } catch (IOException e) {
            //发生网络异常
            e.printStackTrace();
        } finally {
            //释放连接
            postMethod.releaseConnection();
        }
        return sBuffer.toString();
    }

    public static String HttpPostToJson(String webUrl, String str) {
        String encoding = "UTF-8";
        if (encoding == null || "".equals(encoding)) encoding = "UTF-8";
        StringBuffer sBuffer = new StringBuffer();
        //构造HttpClient的实例
        HttpClient httpClient = new HttpClient();
        //创建POS方法的实例
        PostMethod postMethod = new PostMethod(webUrl);
        try {
            //postMethod.setRequestEntity(new ByteArrayRequestEntity(str.getBytes(),"application/json; charset=utf-8"));
            postMethod.setRequestBody(str);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10000); //连接5秒超时
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(30000);//读取30秒超时
//			  	postMethod.setRequestHeader("Content-type", "application/json; charset=utf-8");
//			  	postMethod.setDoAuthentication(false);
//			  	postMethod.addRequestHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");

        postMethod.setDoAuthentication(true);
        postMethod.addRequestHeader("Authorization", "Basic U0FQX0FNOkFubmllTWFvMTIzNA==");
        postMethod.setRequestHeader("Content-type", "application/json");
        try {
            //执行getMethod
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + postMethod.getStatusLine());
                sBuffer = new StringBuffer();
            } else {
                InputStream resStream = postMethod.getResponseBodyAsStream();
                sBuffer = new StringBuffer(postMethod.getResponseBodyAsString() + "");
            }
        } catch (HttpException e) {
            //发生致命的异常，可能是协议不对或者返回的内容有问题
            System.out.println("Please check your provided http address!");
            e.printStackTrace();
        } catch (IOException e) {
            //发生网络异常
            e.printStackTrace();
        } finally {
            //释放连接
            postMethod.releaseConnection();
        }
        return sBuffer.toString();
    }

    public static String HttpPostForJson(String url, String method, Map paramMap) {
        String encoding = "UTF-8";
        String webUrl = url + "/" + method + ".json";
        if (encoding == null || "".equals(encoding)) encoding = "UTF-8";
        StringBuffer sBuffer = new StringBuffer();
        //构造HttpClient的实例
        HttpClient httpClient = new HttpClient();
        //创建POS方法的实例
        NameValuePair[] pairs = null;
        PostMethod postMethod = new PostMethod(webUrl);
        if (paramMap != null) {
            pairs = new NameValuePair[paramMap.size()];
            Set set = paramMap.keySet();
            Iterator it = set.iterator();
            int i = 0;
            while (it.hasNext()) {
                Object key = it.next();
                Object value = paramMap.get(key);
                pairs[i] = new NameValuePair(key.toString(), value.toString());
                i++;
            }
            postMethod.setRequestBody(pairs);
        }
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
        try {
            //执行getMethod
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + postMethod.getStatusLine());
            }
            InputStream resStream = postMethod.getResponseBodyAsStream();
            sBuffer = new StringBuffer(postMethod.getResponseBodyAsString() + "");
            //modify by guanshiqiang at 2012-06-01 for 处理接收字符乱码bug end
            //处理内容
            //sBuffer.append(new String(responseBody,encoding));
        } catch (HttpException e) {
            //发生致命的异常，可能是协议不对或者返回的内容有问题
            System.out.println("Please check your provided http address!");
            e.printStackTrace();
        } catch (IOException e) {
            //发生网络异常
            e.printStackTrace();
        } finally {
            //释放连接
            postMethod.releaseConnection();
        }
        return sBuffer.toString();
    }

    public static String HttpPostForRest(String url, String method, Map paramMap) {
        String encoding = "UTF-8";
        String webUrl = url + "/" + method + ".rest";
        if (encoding == null || "".equals(encoding)) {
            encoding = "UTF-8";
        }
        StringBuffer sBuffer = new StringBuffer();
        //构造HttpClient的实例
        HttpClient httpClient = new HttpClient();
        //创建POS方法的实例
        NameValuePair[] pairs = null;
        PostMethod postMethod = new PostMethod(webUrl);
        if (paramMap != null) {
            pairs = new NameValuePair[paramMap.size()];
            Set set = paramMap.keySet();
            Iterator it = set.iterator();
            int i = 0;
            while (it.hasNext()) {
                Object key = it.next();
                Object value = paramMap.get(key);
                pairs[i] = new NameValuePair(key.toString(), value.toString());
                i++;
            }
            postMethod.setRequestBody(pairs);
        }
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
        try {
            //执行getMethod
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + postMethod.getStatusLine());
            }
            InputStream resStream = postMethod.getResponseBodyAsStream();
            sBuffer = new StringBuffer(postMethod.getResponseBodyAsString() + "");
            //modify by guanshiqiang at 2012-06-01 for 处理接收字符乱码bug end
            //处理内容
            //sBuffer.append(new String(responseBody,encoding));
        } catch (HttpException e) {
            //发生致命的异常，可能是协议不对或者返回的内容有问题
            System.out.println("Please check your provided http address!");
            e.printStackTrace();
        } catch (IOException e) {
            //发生网络异常
            e.printStackTrace();
        } finally {
            //释放连接
            postMethod.releaseConnection();
        }
        return sBuffer.toString();
    }


    public static void print(HttpServletResponse response, String json) {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) +
                s.substring(19, 23) + s.substring(24);
    }


    //add by wangchao
    public static String HttpPostToSAP(String url, String content) {
        String encoding = "UTF-8";
        String webUrl = url;
        if (encoding == null || "".equals(encoding)) encoding = "UTF-8";
        StringBuffer sBuffer = new StringBuffer();
        //构造HttpClient的实例
        HttpClient httpClient = new HttpClient();
        //httpClient.set
        //创建POS方法的实例
        PostMethod postMethod = new PostMethod(webUrl);
        postMethod.setRequestBody(content);
        postMethod.setDoAuthentication(true);
        postMethod.addRequestHeader("Authorization", "Basic U0FQX0FNOkFubmllTWFvMTIzNA==");
        postMethod.setRequestHeader("Content-type", "application/json");
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(50000); //连接5秒超时
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(30000);//读取30秒超时
        try {
            //执行getMethod
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode != HttpStatus.SC_OK && statusCode != 201) {
                System.err.println("Method failed: " + postMethod.getStatusLine());
                sBuffer = new StringBuffer();

                InputStream resStream = postMethod.getResponseBodyAsStream();
                sBuffer = new StringBuffer(postMethod.getResponseBodyAsString() + "");
                System.out.println(sBuffer);
            } else {
                InputStream resStream = postMethod.getResponseBodyAsStream();
                sBuffer = new StringBuffer(postMethod.getResponseBodyAsString() + "");
//			  	System.out.println(sBuffer);
            }
        } catch (HttpException e) {
            //发生致命的异常，可能是协议不对或者返回的内容有问题
            System.out.println("Please check your provided http address!");
            e.printStackTrace();
        } catch (IOException e) {
            //发生网络异常
            e.printStackTrace();
        } finally {
            //释放连接
            postMethod.releaseConnection();
        }
        return sBuffer.toString();
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


    public static String HttpPostForQiakr(String webUrl, String json) {
        String encoding = "UTF-8";
        if (encoding == null || "".equals(encoding)) encoding = "UTF-8";
        StringBuffer sBuffer = new StringBuffer();
        //构造HttpClient的实例
        HttpClient httpClient = new HttpClient();
        //创建POS方法的实例
        PostMethod postMethod = new PostMethod(webUrl);
        if (json != null) {
            postMethod.setRequestBody(json);
            postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
        }
        try {
            postMethod.setDoAuthentication(true);
            postMethod.addRequestHeader("Authorization", "Basic U0FQX0FNOkFubmllTWFvMTIzNA==");
            postMethod.setRequestHeader("Content-type", "application/json");
            //执行getMethod
            int statusCode = httpClient.executeMethod(postMethod);
            System.out.println("=====" + statusCode);
            if (statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_CREATED) {
                sBuffer = new StringBuffer(postMethod.getResponseBodyAsString() + "");
            } else {
                System.out.println("====");
                System.err.println("Method failed: " + postMethod.getStatusLine());
            }
            //	  	InputStream resStream = postMethod.getResponseBodyAsStream();
            //modify by guanshiqiang at 2012-06-01 for 处理接收字符乱码bug end
            //处理内容
            //sBuffer.append(new String(responseBody,encoding));
        } catch (HttpException e) {
            //发生致命的异常，可能是协议不对或者返回的内容有问题
            System.out.println("Please check your provided http address!");
            e.printStackTrace();
        } catch (IOException e) {
            //发生网络异常
            e.printStackTrace();
        } finally {
            //释放连接
            postMethod.releaseConnection();
        }
        return sBuffer.toString();
    }

}
