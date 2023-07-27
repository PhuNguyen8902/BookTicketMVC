/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
@PropertySource("classpath:configs.properties")
public class WebAppContextConfig implements WebMvcConfigurer {

    @Autowired
    private Environment env;

//    Cấu hình bộ xử lý Sẻvlet mặc định của Spring MVC. Phương thức được ghi đè từ WebMvcConfigurer
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable(); //kích hoạt xử lý Servlet mặc định
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver
                = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }

//    @Bean
//    public Cloudinary cloudinary() {
//        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
//                "cloud_name", "dqdspcxhq",
//                "api_key", "446165921947982",
//                "api_secret", "45qDgXCEN-oYiZMJ3bQeIzCHk6A",
//                "secure", true));
//        return cloudinary;
//    }
//    @Bean
//    public Cloudinary cloudinary() {
//        return new Cloudinary("cloudinary://" + env.getProperty("cloudinary.api_id") + ":" + env.getProperty("cloudinary.api_secret") + "@" + env.getProperty("cloudinary.cloud_name"));
//    }
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
