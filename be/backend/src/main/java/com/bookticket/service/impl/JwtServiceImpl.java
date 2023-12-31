///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package com.bookticket.service.impl;
//

import com.bookticket.pojo.User;
import com.bookticket.service.JwtService;
import com.bookticket.service.UserService;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author ADMIN
// */

@Service
@Transactional
public class JwtServiceImpl implements JwtService {

    @Autowired
    private UserService userSerice;

    private static final String SECRET_KEY = "hp2JdTQsAmOTxHvxh9CosgVhvMbqiHIDum7e87mUA1N7fGXUX11LZ/4bBJwpMuB1";

    private byte[] generateShareSecret() {
        byte[] shareSecret = new byte[32];
        shareSecret = SECRET_KEY.getBytes();
        return shareSecret;
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + 60000*60);             // 60p 60000*60
    }

    @Override
    public String generateTokenLogin(UserDetails user) {
        String token = null;
        User u = this.userSerice.getUsers(user.getUsername()).get(0);
        try {
            Date date = new Date();
            long dateLong = date.getTime();
            JWSSigner signer = new MACSigner(generateShareSecret());
            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
            builder.claim("userId", u.getId());
            builder.claim("name", u.getName());
            builder.claim("email", user.getUsername());
            builder.claim("authorise", user.getAuthorities());
            builder.claim("exp", generateExpirationDate());
            builder.claim("iat", dateLong);

            builder.expirationTime(generateExpirationDate());

            JWTClaimsSet claimsSet = builder.build();

            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

            signedJWT.sign(signer);

            token = signedJWT.serialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    private JWTClaimsSet getClaimsFromToken(String token) {
        JWTClaimsSet claims = null;
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(generateShareSecret());
            if (signedJWT.verify(verifier)) {
                claims = signedJWT.getJWTClaimsSet();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return claims;
    }

    @Override
    public Map<String, Object> getClaimsFromTokenPublic(String token) {
        Map<String, Object> claimsMap = null;
        try {
            JWTClaimsSet claims = getClaimsFromToken(token);
            if (claims != null) {
                claimsMap = claims.getClaims();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return claimsMap;
    }

    @Override
    public String getUsernameFromToken(String token) {
        String email = null;
        try {
            JWTClaimsSet claims = getClaimsFromToken(token);
            email = claims.getStringClaim("email");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return email;
    }

    private Date getExpirationDateFromToken(String token) {
        Date expiration = null;
        JWTClaimsSet claims = getClaimsFromToken(token);
        expiration = claims.getExpirationTime();
        return expiration;
    }

    private Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    @Override
    public Boolean validateTokenLogin(String token) {
        if (token == null || token.trim().length() == 0) {
            return false;
        }
        String username = getUsernameFromToken(token);
        if (username == null || username.isEmpty()) {
            return false;
        }
        if (isTokenExpired(token)) {

            return false;
        }
        return true;
    }

}
