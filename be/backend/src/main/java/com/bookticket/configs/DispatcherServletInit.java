/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.configs;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author ADMIN
 */
public class DispatcherServletInit extends AbstractAnnotationConfigDispatcherServletInitializer {

// Cấu hình config cho toàn bộ hệ thống
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
            HibernateConfig.class,
            TilesConfig.class,
            SpringSecurityConfig.class

        };
    }

// Phương thức này trả về một mảng các lớp cấu hình (Servlet Config Classes) cho Dispatcher Servlet. 
// Dispatcher Servlet là một Servlet đặc biệt trong Spring MVC được sử dụng để xử lý các yêu cầu từ người dùng
    @Override
protected Class<?>[] getServletConfigClasses() {
    return new Class[]{
        WebAppContextConfig.class
    };
}


//    Đường dẫn mà Dispatcher Servlet sẽ xử lý là đường dẫn được khớp với URL yêu cầu từ phía người dùng. 
//    Trong ví dụ này, phương thức trả về một mảng với một đường dẫn "/" (root). 
//    Điều này có nghĩa là Dispatcher Servlet sẽ xử lý tất cả các yêu cầu đến từ đường dẫn gốc ("/") của ứng dụng.
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
