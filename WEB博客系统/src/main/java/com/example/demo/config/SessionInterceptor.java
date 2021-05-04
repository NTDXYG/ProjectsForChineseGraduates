package com.example.demo.config;

import com.example.demo.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class SessionInterceptor implements HandlerInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(SessionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        // 从session中获取用户信息
        User admin = (User) session.getAttribute("user");
        // session过期
        if (admin == null) {
            LOG.info(">>>session过期, 跳至登录页");
            response.sendRedirect("/login"); // 通过接口跳转登录页面, 注:重定向后下边的代码还会执行 ; /outToLogin是跳至登录页的后台接口
            return false;
        } else {
            return true;
        }
    }
}
