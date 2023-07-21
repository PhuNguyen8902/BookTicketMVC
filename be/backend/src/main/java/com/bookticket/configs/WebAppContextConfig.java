/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.configs;

import com.sun.org.apache.bcel.internal.generic.RET;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * @author ADMIN
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
    "com.bookticket.controller",
    "com.bookticket.pojo",
    "com.bookticket.configs",
    "com.bookticket.repository",
    "com.bookticket.service"
})

public class WebAppContextConfig implements WebMvcConfigurer {

//    Cấu hình bộ xử lý Sẻvlet mặc định của Spring MVC. Phương thức được ghi đè từ WebMvcConfigurer
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable(); //kích hoạt xử lý Servlet mặc định
    }

////    Cấu hình các tài nguyên phía view hiển thị cho người dùng
//    @Bean
//    public InternalResourceViewResolver internalResourceViewResolver() {
//        InternalResourceViewResolver r = new InternalResourceViewResolver();
//        r.setViewClass(JstlView.class); // Sử dụng thư viện JSTL
//        r.setPrefix("/WEB-INF/pages/"); // Thiết lập đường dẫn tới tài nguyên view
//        r.setSuffix(".jsp"); // Hậu tố cho đường dẫn phía trên để lấy file view
//        return r;
//    }

    @Override
    public Validator getValidator() {
        return validator();
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean v = new LocalValidatorFactoryBean();
        v.setValidationMessageSource(messageSource());
        return v;
    }
    

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("messages");
        return source;
    }

}
