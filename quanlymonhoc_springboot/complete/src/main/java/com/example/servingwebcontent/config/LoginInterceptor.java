package com.example.servingwebcontent.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        // Cho phép trang login + lỗi + tài nguyên tĩnh
        if (uri.startsWith("/login") || uri.startsWith("/error")
                || uri.startsWith("/css") || uri.startsWith("/js")
                || uri.startsWith("/images") || uri.startsWith("/webjars")) {
            return true;
        }

        // Chưa đăng nhập -> chuyển hướng login
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}

