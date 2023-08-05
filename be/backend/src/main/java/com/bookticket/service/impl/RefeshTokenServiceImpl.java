/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.service.impl;

import com.bookticket.pojo.RefeshToken;
import com.bookticket.pojo.User;
import com.bookticket.repository.RefeshTokenRepository;
import com.bookticket.repository.UserRepository;
import com.bookticket.service.RefeshTokenService;
import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADMIN
 */
@Service
@Transactional
public class RefeshTokenServiceImpl implements RefeshTokenService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RefeshTokenRepository refeshRepo;

    @Override
    public String createRefeshToken(String email) {
        String refeshToken = UUID.randomUUID().toString();
        User user = this.userRepo.getUsers(email).get(0);
        RefeshToken token = new RefeshToken();
        token.setToken(refeshToken);
        token.setUserId(user);
        token.setExpiryDate(new Date(System.currentTimeMillis() + 60000*60*24)); // 1 ngay
        boolean rs = this.refeshRepo.addRefeshToken(token);
        if (rs) {
            return refeshToken;
        } else {
            return null;
        }
    }

    @Override
    public boolean isRefeshTokenExpired(String token) {
        return this.refeshRepo.isRefeshTokenExpired(token);
    }

    @Override
    public RefeshToken getRefeshTokenByUserId(String userId) {
        return this.refeshRepo.getRefeshTokenByUserId(userId);
    }

    @Override
    public RefeshToken getRefeshTokenByToken(String token) {
        return this.refeshRepo.getRefeshTokenByToken(token);
    }

}
