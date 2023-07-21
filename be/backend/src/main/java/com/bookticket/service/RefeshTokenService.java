/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.service;

import com.bookticket.pojo.RefeshToken;

/**
 *
 * @author ADMIN
 */
public interface RefeshTokenService {

     String createRefeshToken(String email);

     boolean isRefeshTokenExpired(String token);

     RefeshToken getRefeshTokenByUserId(String userId);

     RefeshToken getRefeshTokenByToken(String token);
}
