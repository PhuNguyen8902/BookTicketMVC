/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.service;

import com.bookticket.dto.LoginRequest;
import com.bookticket.dto.RegisterRequest;
import com.bookticket.dto.TokenRequest;
import com.bookticket.dto.TokenResponse;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author ADMIN
 */
public interface AuthService {

    TokenResponse login(@RequestBody LoginRequest loginRequest);

    TokenResponse refeshToken(@RequestBody TokenRequest tokenRequest);

    TokenResponse register(@RequestBody RegisterRequest registerRequest);

}
