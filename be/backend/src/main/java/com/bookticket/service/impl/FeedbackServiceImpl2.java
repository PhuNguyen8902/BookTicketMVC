/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.service.impl;

import com.bookticket.dto.Request.FeedbackRequest;
import com.bookticket.pojo.Feedback;
import com.bookticket.pojo.Trip;
import com.bookticket.pojo.User;
import com.bookticket.repository.TripRepository;
import com.bookticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bookticket.repository.FeedbackRepository2;
import com.bookticket.service.FeedbackService2;

/**
 *
 * @author ADMIN
 */
@Service
@Transactional
public class FeedbackServiceImpl2 implements FeedbackService2{
    
    @Autowired
    private TripRepository tripRepo;

    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private FeedbackRepository2 feedbackRepo;
    
    @Override
    public boolean addFeedback(FeedbackRequest f) {
        Feedback feedback = new Feedback();
        Trip trip = this.tripRepo.getTripById(f.getTripId());
        User u = this.userRepo.getUserById(f.getUserId());
        feedback.setContent(f.getContent());
        feedback.setTripId(trip);
        feedback.setUserId(u);
        return this.feedbackRepo.addFeedback(feedback);
        
    }
    
}
