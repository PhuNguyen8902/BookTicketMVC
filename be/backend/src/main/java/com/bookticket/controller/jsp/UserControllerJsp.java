/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller.jsp;

import com.bookticket.dto.Request.LoginRequest;
import com.bookticket.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
/**
 *
 * @author admin
 */
@Controller
@ControllerAdvice
public class UserControllerJsp {

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginRequest", new User()); // Thêm đối tượng mô hình vào model
        return "login";
    }
    
     @GetMapping("/info")
    public String info(Model model) {
        return "info";
    }


}
