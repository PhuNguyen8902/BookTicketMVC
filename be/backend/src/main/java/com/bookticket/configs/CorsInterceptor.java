/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.configs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ADMIN
 */
public class CorsInterceptor implements HandlerInterceptor{
     @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000/"); // Domain của frontend
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); // Phương thức cho phép
        response.setHeader("Access-Control-Allow-Headers", "*"); // Các header cho phép
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Không cần thực hiện gì ở đây
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Không cần thực hiện gì ở đây
    }
}
