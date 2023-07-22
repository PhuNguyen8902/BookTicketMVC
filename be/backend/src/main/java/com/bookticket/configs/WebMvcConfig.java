/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author ADMIN
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000/") // Chỉ định domain có thể truy cập API của bạn
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Chỉ định các phương thức HTTP được chấp nhận
                .allowedHeaders("*") // Cho phép tất cả các tiêu đề (headers)
                .allowCredentials(true); // Cho phép gửi cookie khi sử dụng CORS với nguồn gốc ngoài
    }
}
