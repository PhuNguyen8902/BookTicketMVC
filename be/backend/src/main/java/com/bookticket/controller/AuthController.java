/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller;

import com.bookticket.dto.LoginRequest;
import com.bookticket.dto.Message;
import com.bookticket.dto.PictureResponse;
import com.bookticket.dto.RegisterRequest;
import com.bookticket.dto.TokenRequest;
import com.bookticket.dto.TokenResponse;
import com.bookticket.pojo.User;
import com.bookticket.service.AuthService;
import com.bookticket.service.PictureService;
import com.bookticket.service.UserService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    private UserService userService;
    
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
//        Authentication auth = ;
//        User user =(User) auth.getPrincipal();
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
      @RequestMapping(value = "/demo/", method = RequestMethod.GET)
    public ResponseEntity<?> getDemo(HttpServletRequest request) {
        
//        Authentication auth = ;
//        User user =(User) auth.getPrincipal();
User u = this.userService.getUsers("admin@gmail.com").get(0);
        return ResponseEntity.ok(u);
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
    @PostMapping("/picture/demo/")
    public ResponseEntity<?> pictureDemo(@RequestParam("file") MultipartFile file) throws IOException {
//            public ResponseEntity<?> pictureDemo(@RequestBody MultipartFile file) throws IOException {
        System.out.println("-------------------controller");
                System.out.println(file);

        PictureResponse pic = this.pictureService.sendPicToCloud(file);
        if (pic == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Message.builder().message("khong co anh").build());
        } else {
            return ResponseEntity.ok(pic);
        }
    }
}
