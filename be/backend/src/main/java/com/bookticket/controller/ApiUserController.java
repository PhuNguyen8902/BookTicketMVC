/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller;

import com.bookticket.dto.Api.ApiTicketRequest;
import com.bookticket.dto.Message;
import com.bookticket.dto.Request.RegisterRequest;
import com.bookticket.pojo.User;
import com.bookticket.service.UserService;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vegar
 */
@RestController
@RequestMapping("api/user/")
public class ApiUserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/update-basic-user")
    public ResponseEntity<?> updateBasicUser(@RequestBody RegisterRequest userRequest){
        User user = this.userService.getUserById(userRequest.getUuid());
        
        user.setEmail(userRequest.getEmail());
        user.setName(userRequest.getName());
        user.setPhone(userRequest.getPhone());
        
        if(this.userService.editUser(user)){
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("suscess", "Successful booking.");

            return ResponseEntity.status(HttpStatus.CREATED).body(jsonResponse.toString());
            
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.builder().message("Create Fail").build());
    }
}
