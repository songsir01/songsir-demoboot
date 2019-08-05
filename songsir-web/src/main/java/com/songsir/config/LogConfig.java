package com.songsir.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @PackageName com.songsir.config
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 15:56 2019/7/5
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
@Aspect
@Component
public class LogConfig {

    private static Logger logger = LoggerFactory.getLogger(LogConfig.class.getName());

    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    @Pointcut("execution(* com.songsir.*..*Controller.*(..)) || execution(* com.songsir.*..*Schedule*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        logger.info("\r\n----------方法开始----------");
        startTime.set(System.currentTimeMillis());

        /**
         * 接收到请求，记录请求内容
         */
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String userAgent = request.getHeader("User-Agent");

        Cookie cookies[] = request.getCookies();
        String cookieInfo = getCookieInfo(cookies);
        String ip = getClientIpAddress(request);
        /**
         * 记录下请求内容
         */
        logger.info("User-Agent : " + userAgent + "; IP ：" + ip);
        /**
         * 打印cookie
         */
        logger.info("COOKIE：" + cookieInfo);
        logger.info("URL : " + request.getRequestURL().toString() + " Http_Method : " + request.getMethod());
        /**
         * 获取所有参数方法一：
         */
        Enumeration<String> enu = request.getParameterNames();
        if (enu.hasMoreElements()) {
            StringBuffer params = new StringBuffer();
            while (enu.hasMoreElements()) {
                String paraName = enu.nextElement();
                params.append(paraName + ": " + request.getParameter(paraName) + " ");
            }
            logger.info("Params : " + params.toString());
        }
    }

    @AfterReturning("webLog()")
    public void doAfterReturning(JoinPoint joinPoint) {
        /**
         * 处理完请求，返回内容
         */
        logger.info("耗时（毫秒） : " + (System.currentTimeMillis() - startTime.get()));
        logger.info("\r\n----------方法结束----------\n\n\n");
    }

    /**
     * 打印cookie信息
     * @param cookies
     * @return
     */
    private String getCookieInfo(Cookie[] cookies) {
        if (cookies != null) {
            StringBuffer sbCookie = new StringBuffer();
            for (Cookie cookie : cookies) {
                sbCookie.append(cookie.getName() + ":" + cookie.getValue());
            }
            return sbCookie.toString();
        }
        return "";
    }

    private static final String[] HEADERS_TO_TRY = {"X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR", "X-Real-IP"};

    /**
     * getClientIpAddress:(获取用户ip，可穿透代理). <br/>
     * @param request
     * @return
     * @author SongYapeng
     * @Date 2018年3月2日下午4:41:47
     * @since JDK 1.7
     */
    public static String getClientIpAddress(HttpServletRequest request) {
        for (String header : HEADERS_TO_TRY) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }
}
