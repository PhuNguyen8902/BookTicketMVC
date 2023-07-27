/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller;

import com.bookticket.dto.FileDemo;
import com.bookticket.dto.LoginRequest;
import com.bookticket.dto.Message;
import com.bookticket.dto.PictureDemo;
import com.bookticket.dto.RegisterRequest;
import com.bookticket.dto.TokenRequest;
import com.bookticket.dto.TokenResponse;
import com.bookticket.service.AuthService;
import com.bookticket.service.PictureService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
@RestController
@RequestMapping("api/auth/")
public class AuthController {

    @Autowired
    private AuthService authService;
    
    @Autowired
    private PictureService pictureService;

    @RequestMapping(value = "/authenticate/", method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) {
        TokenResponse response = this.authService.login(loginRequest);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Message.builder().message("Login Fail").build());
        } else {

            return ResponseEntity.ok(response);

        }
    }

    @RequestMapping(value = "/accessToken/", method = RequestMethod.GET)
    public ResponseEntity<?> getUserByToken(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(auth.getPrincipal());
    }

    @RequestMapping(value = "/refeshToken/", method = RequestMethod.POST)
    public ResponseEntity<?> getNewTokenByRefeshToken(@RequestBody TokenRequest tokenRequest) {
        TokenResponse response = this.authService.refeshToken(tokenRequest);
        if (response == null || response.getRefeshToken() == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Message.builder().message("RefeshToken Error").build());
        } else {
            return ResponseEntity.ok(response);
        }
    }

    @RequestMapping(value = "/register/", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        TokenResponse response = this.authService.register(registerRequest);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Message.builder().message("Register Fail").build());
        } else {
            return ResponseEntity.ok(response);
        }
    }
    
    @RequestMapping(value = "/picture/demo/", method = RequestMethod.POST)
    public ResponseEntity<?> pictureDemo(@RequestBody FileDemo file) {
        PictureDemo pic = this.pictureService.sendPicToCloud(file.getFile());
        if (pic == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Message.builder().message("khong co anh").build());
        } else {
            return ResponseEntity.ok(pic);
        }
    }
}
