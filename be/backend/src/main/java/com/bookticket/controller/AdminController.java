/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller;

import com.bookticket.dto.Message;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
@RestController
@RequestMapping("api/admin/")
public class AdminController {
    
    @RequestMapping(value = "/test/", method = RequestMethod.GET)
    public ResponseEntity<?> adminTest(){
        Message mess = new Message();
        mess.setMessage("vo duoc roi");
        return ResponseEntity.ok(mess);
    }
     @RequestMapping(value = "/accessToken/", method = RequestMethod.GET)
    public ResponseEntity<?> getUserByToken(HttpServletRequest request) {
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
