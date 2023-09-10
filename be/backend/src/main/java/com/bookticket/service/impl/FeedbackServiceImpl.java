/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.service.impl;

import com.bookticket.dto.Request.FeedbackRequest;
import com.bookticket.pojo.Feedback;
import com.bookticket.pojo.Trip;
import com.bookticket.pojo.User;
import com.bookticket.repository.FeedbackRepository;
import com.bookticket.repository.TripRepository;
import com.bookticket.repository.UserRepository;
import com.bookticket.service.FeedbackService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vegar
 */
@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private TripRepository tripRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public List<FeedbackRequest> getFeedBacks(Map<String, String> params) {
        return this.feedbackRepository.getFeedBacks(params);
    }

    @Override
    public boolean addFeedback(FeedbackRequest f) {
        Feedback feedback = new Feedback();
        Trip trip = this.tripRepo.getTripById(f.getTripId());
        User u = this.userRepo.getUserById(f.getUserId());
        feedback.setContent(f.getContent());
        feedback.setTripId(trip);
        feedback.setUserId(u);
        return this.feedbackRepository.addFeedback(feedback);

    }
}
