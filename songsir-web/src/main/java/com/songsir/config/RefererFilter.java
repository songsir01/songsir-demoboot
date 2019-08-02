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
        filterChain.doFilter(servletRequest, servletResponse);

        /*if (servletPath.equals(ecludeUrl)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String referer = request.getHeader("Referer");
            if (referer.trim().contains("com.songsir")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                response.sendRedirect("/login.action");
            }
        }*/
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
