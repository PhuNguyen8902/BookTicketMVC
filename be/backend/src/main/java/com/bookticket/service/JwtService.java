/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author ADMIN
 */
public interface JwtService {

     String generateTokenLogin(UserDetails user);
     String getUsernameFromToken(String token);
     Boolean validateTokenLogin(String token);
}
