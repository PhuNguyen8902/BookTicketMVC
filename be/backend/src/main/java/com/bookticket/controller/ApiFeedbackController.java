/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller;

import com.bookticket.dto.Message;
import com.bookticket.dto.Request.FeedbackRequest;
import com.bookticket.dto.Request.RouteRequest;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bookticket.service.FeedbackService2;

/**
 *
 * @author ADMIN
 */
@RestController
@RequestMapping("api/feedback/")
public class ApiFeedbackController {
    
    @Autowired
    private FeedbackService2 feedbackSer;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addFeedback(@RequestBody FeedbackRequest feed) {
       



        boolean rs = this.feedbackSer.addFeedback(feed);
        if (rs) {
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("suscess", "Feedback đã được thêm thành công.");

            return ResponseEntity.status(HttpStatus.CREATED).body(jsonResponse.toString());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.builder().message("Create Fail").build());

        }
    }
}
