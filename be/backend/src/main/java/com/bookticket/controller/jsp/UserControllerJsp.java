/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller.jsp;

import com.bookticket.dto.Request.LoginRequest;
import com.bookticket.dto.Request.RegisterRequest;
import com.bookticket.dto.Request.RegisterRequestJsp;
import com.bookticket.dto.Response.TokenResponse;
import com.bookticket.pojo.User;
import com.bookticket.service.AuthService;
import com.bookticket.service.impl.PictureServiceImpl;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author admin
 */
@Controller
public class UserControllerJsp {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserDetailsService userDetailService;

    @GetMapping("/login-user")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login-user")
    public String login(@ModelAttribute(value = "loginRequest") @Valid LoginRequest p,
            BindingResult rs) {
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

    @GetMapping("/admin/create-account")
    public String createAccount(Model model) {
        model.addAttribute("create", new RegisterRequestJsp());
        return "createAccount";
    }

    @PostMapping("/admin/create-account")
    public String login(@ModelAttribute(value = "create") @Valid RegisterRequestJsp p,
            BindingResult rs, Model model) {
        if (p.getImage().getSize() == 0) {
            rs.rejectValue("image", "image.sameAsStart", "Please choose the avatar");
            return "createAccount";
        }
        if (rs.hasErrors()) {
            return "createAccount";
        }
        try {
            Map res = this.cloudinary.uploader().upload(p.getImage().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            String url = res.get("secure_url").toString();
            RegisterRequest register = new RegisterRequest();
            register.setAvatar(url);
            register.setEmail(p.getEmail());
            register.setPassword(p.getPassword());
            register.setName(p.getName());
            register.setPhone(p.getPhone());
            register.setRole(p.getRole());
            TokenResponse response = this.authService.register(register);
        } catch (IOException ex) {
            Logger.getLogger(PictureServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/";
    }
}
