/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller.jsp;

import com.bookticket.dto.Request.LoginRequest;
import com.bookticket.pojo.User;
import com.bookticket.service.UserService;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author admin
 */
@Controller
@ControllerAdvice
public class UserControllerJsp {

    @Autowired
    private UserDetailsService userDetailService;

    @GetMapping("/login-user")
    public String login(Model model) {
//        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping("/login-user")
    public String login(@ModelAttribute(value = "loginRequest") @Valid LoginRequest p,
            BindingResult rs ) {
        User userDetails = (User) userDetailService.loadUserByUsername(p.getEmail());

        if (userDetails != null) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        return "redirect:/";
    }

    @PostMapping("/logout-user")
    public String logout() {
        SecurityContextHolder.clearContext();

        return "redirect:/";
    }

//    @ModelAttribute
//    public void info(Model model, Principal principal) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("------------------demo");
//                System.out.println(authentication);
//
//        if(authentication.getPrincipal() != "anonymousUser"){
//        model.addAttribute("infoUser", authentication.getPrincipal());
//        }else{
//                    model.addAttribute("infoUser", null);
//
//        }
//    }

}
