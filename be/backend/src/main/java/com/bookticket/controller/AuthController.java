/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller;

import com.bookticket.dto.LoginRequest;
import com.bookticket.dto.Message;
import com.bookticket.dto.RegisterRequest;
import com.bookticket.dto.TokenRequest;
import com.bookticket.dto.TokenResponse;
import com.bookticket.service.AuthService;
import com.bookticket.service.JwtService;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
@RestController
@RequestMapping("api/")
@CrossOrigin("http://localhost:3000/")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/authenticate/", method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) {
        TokenResponse response = this.authService.login(loginRequest);
        if (response == null) {
            Message mess = new Message();
            mess.setMessage("Login Fail");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(mess);
        } else {

            return ResponseEntity.ok(response);

        }
    }

    @RequestMapping(value = "/accessToken/", method = RequestMethod.GET)
    public ResponseEntity<?> getUserByToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);

        if (this.jwtService.validateTokenLogin(token)) {
//            String email = this.jwtService.getUsernameFromToken(token);
//            UserDetails user = this.userDetailsService.loadUserByUsername(email);
            Map<String, Object> claimsMap = this.jwtService.getClaimsFromTokenPublic(token);

            return ResponseEntity.ok(claimsMap);
        } else {
            Message mess = new Message();
            mess.setMessage("AccessToken Error");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(mess);
        }
    }

    @RequestMapping(value = "/refeshToken/", method = RequestMethod.POST)
    public ResponseEntity<?> getNewTokenByRefeshToken(@RequestBody TokenRequest tokenRequest) {
        TokenResponse response = this.authService.refeshToken(tokenRequest);
        if (response == null || response.getRefeshToken() == null) {
            Message mess = new Message();
            mess.setMessage("RefeshToken Error");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(mess);
        } else {
            return ResponseEntity.ok(response);
        }
    }
    
    @RequestMapping(value = "/register/", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        TokenResponse response = this.authService.register(registerRequest);
        if (response == null) {
            Message mess = new Message();
            mess.setMessage("Register Fail");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(mess);
        } else {
            return ResponseEntity.ok(response);
        }
    }
}
