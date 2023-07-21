/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.repository;

import com.bookticket.pojo.RefeshToken;

/**
 *
 * @author ADMIN
 */
public interface RefeshTokenRepository {

     boolean addRefeshToken(RefeshToken token);

     boolean isRefeshTokenExpired(String token);

     RefeshToken getRefeshTokenByUserId(String userId);

     RefeshToken getRefeshTokenByToken(String token);
}
