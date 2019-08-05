[toc]
# 一、概念

> CSRF（Cross-site request forgery）跨站请求伪造，也被称为“One Click Attack”或者Session Riding，通常缩写为CSRF或者XSRF，是一种对网站的恶意利用。尽管听起来像跨站脚本（XSS），但它与XSS非常不同，XSS利用站点内的信任用户，而CSRF则通过伪装成受信任用户的请求来利用受信任的网站。与XSS攻击相比，CSRF攻击往往不大流行（因此对其进行防范的资源也相当稀少）和难以防范，所以被认为比XSS更具危险性。



# 二、攻击原理
假如用户a银行转账100元，url如下：

```
http://example.icbc.com/zhuanzhang?toUser=a&payCount=100
```
而当a登录信息未过期时收到了恶意攻击者b的如下代码：

```
http://example.icbc.com/zhuanzhang?toUser=b&payCount=100
```
这种链接以隐蔽的方式存在与浏览器的各个页面中迷惑用户，当用户点击后很可能造成损失。

# 三、防范手段
## 1、校验Referer
referer字段存在于http头部信息中，用来标识请求来源，所以可以通过使用过滤器，校验referer来拦截非法请求。
### 代码实现1（web.xml配置版）：
（1）web.xml 添加过滤器

```
<filter>
	<filter-name>refererFilter</filter-name>
	<filter-class>com.songsir.config.RefererFilter</filter-class>
	<!-- 过滤排除url-->
	<init-param>
		<param-name>excudeUrl</param-name>
		<param-value>/login.action</param-value>
	</init-param>
</filter>
<filter-mapping>
	<filter-name>refererFilter</filter-name>
	<url-pattern>*.action</url-pattern>
</filter-mapping>
```
（2）filter类

```
package com.songsir.config;

import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @PackageName com.songsir.config
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 13:40 2019/8/2
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class RefererFilter implements Filter {


    String[] excudeUrlArray;

    /**
     * @param filterConfig
     * @MethodName init
     * @Description 获取过滤器需要放过的uri
     * @Author SongYapeng
     * @Date 2019/8/2 13:42
     * @Since JDK 1.8
     */
    @Override
    public void init(FilterConfig filterConfig) {
        String excudeUrl = filterConfig.getInitParameter("excudeUrl");
        String regex = ",";
        excudeUrlArray = excudeUrl.split(regex);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String servletPath = getServletPath(request);
        boolean isExcludeUrl = false;
        for (String excudeUrl : excudeUrlArray) {
            if (excudeUrl.equals(servletPath)) {
                isExcludeUrl = true;
                break;
            }
        }
        if (isExcludeUrl) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String referer = request.getHeader("Referer");
            // 如果来源是本网域名（假设本网域名是"com.songsir"）
            if (referer != null && referer.trim().contains("com.songsir")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                response.sendRedirect("/login.action");
            }
        }
    }

    private String getServletPath(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        if (StringUtils.isNotEmpty(servletPath)) {
            return servletPath;
        } else {
            int startIndex = request.getContextPath().equals("") ? 0 : request.getContextPath().length();
            int endIndex = request.getRequestURI().length();
            return request.getRequestURI().substring(startIndex, endIndex);
        }
    }

    @Override
    public void destroy() {
        return;
    }
}

```
### 代码实现2（SpringBoot版）
（1）启动类增加注解@ServletComponentScan
（2）Filter配置类

```
package com.songsir.config;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @PackageName com.songsir.config
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 13:40 2019/8/2
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */

@WebFilter(urlPatterns = {"/*"}, filterName = "refererFilter")
public class RefererFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(RefererFilter.class);

    private String ecludeUrl = "/login.action";

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("refererFilter is init ...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String servletPath = getServletPath(request);
        if (servletPath.equals(ecludeUrl)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String referer = request.getHeader("Referer");
            if (referer.trim().contains("com.songsir")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                response.sendRedirect("/login.action");
            }
        }
    }

    private String getServletPath(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        if (StringUtils.isNotEmpty(servletPath)) {
            return servletPath;
        } else {
            int startIndex = request.getContextPath().equals("") ? 0 : request.getContextPath().length();
            int endIndex = request.getRequestURI().length();
            return request.getRequestURI().substring(startIndex, endIndex);
        }
    }

    @Override
    public void destroy() {
        return;
    }
}

```
## 2、添加校验 Token

> 在访问敏感数据请求时，要求用户浏览器提供不保存在 Cookie 中，并且攻击者无法伪造的数据作为校验。例如服务器生成随机数并附加在表单中，并要求客户端传回这个随机数。

## 3、输入验证码

> 因为 CSRF 攻击是在用户无意识的情况下发生的，所以要求用户输入验证码可以让用户知道自己正在做的操作。
