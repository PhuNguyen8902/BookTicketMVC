/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.service.impl;

import com.bookticket.dto.LoginRequest;
import com.bookticket.dto.RegisterRequest;
import com.bookticket.dto.TokenRequest;
import com.bookticket.dto.TokenResponse;
import com.bookticket.enums.Role;
import com.bookticket.pojo.RefeshToken;
import com.bookticket.pojo.User;
import com.bookticket.service.AuthService;
import com.bookticket.service.JwtService;
import com.bookticket.service.RefeshTokenService;
import com.bookticket.service.UserService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADMIN
 */
@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private RefeshTokenService refeshTokenService;

    private String checkRefeshToken(String token) {
        boolean rs = this.refeshTokenService.isRefeshTokenExpired(token);
        if (rs) {
            return token;
        }
        return null;
    }

    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        UserDetails user = this.userDetailsService.loadUserByUsername(email);
        if (user == null) {
            return null;
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        String accessToken = this.jwtService.generateTokenLogin(user);
        User u = this.userService.getUsers(email).get(0);
        RefeshToken refeshToken = this.refeshTokenService.getRefeshTokenByUserId(u.getId());
        if (refeshToken == null) {
            String newRefeshToken = this.refeshTokenService.createRefeshToken(email);
            return TokenResponse.builder().accessToken(accessToken).refeshToken(newRefeshToken).build();
        } else {
            String newRefeshToken = checkRefeshToken(refeshToken.getToken());
            if (newRefeshToken == null) {
                String newRefeshToken2 = this.refeshTokenService.createRefeshToken(email);
                return TokenResponse.builder().accessToken(accessToken).refeshToken(newRefeshToken2).build();
            } else {
                return TokenResponse.builder().accessToken(accessToken).refeshToken(newRefeshToken).build();
            }
        }

    }

    @Override
    public TokenResponse refeshToken(TokenRequest tokenRequest) {
        String token = tokenRequest.getToken();
        RefeshToken refeshToken = this.refeshTokenService.getRefeshTokenByToken(token);
        if (refeshToken == null) {
            return null;
        } else {
            User user = refeshToken.getUserId();
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(user.getEmail());
            String newRefeshToken = checkRefeshToken(refeshToken.getToken());
            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setAccessToken(this.jwtService.generateTokenLogin(userDetails));
            tokenResponse.setRefeshToken(newRefeshToken);
            return tokenResponse;
        }
    }

    @Override
    public TokenResponse register(RegisterRequest registerRequest) {
        String uuid = UUID.randomUUID().toString();
        String password = registerRequest.getPassword();
        String passwordEncoded = passwordEncoder.encode(password);
        User user = new User();
        user.setId(uuid);
        user.setName(registerRequest.getName());
        user.setPassword(passwordEncoded);
        user.setEmail(registerRequest.getEmail());
        user.setPhone(registerRequest.getPhone());

        boolean rs = this.userService.addUser(user);
        UserDetails userdetail = this.userDetailsService.loadUserByUsername(user.getEmail());
        if (rs) {
            TokenResponse tokenResponse = new TokenResponse();
            String refeshToken = this.refeshTokenService.createRefeshToken(user.getEmail());
            tokenResponse.setAccessToken(this.jwtService.generateTokenLogin(userdetail));
            tokenResponse.setRefeshToken(refeshToken);
            return tokenResponse;
        } else {
            return null;
        }
    }

}
