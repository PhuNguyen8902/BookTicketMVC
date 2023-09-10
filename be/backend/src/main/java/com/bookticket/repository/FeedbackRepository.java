/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.repository;

import com.bookticket.dto.Request.FeedbackRequest;
import com.bookticket.pojo.Feedback;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vegar
 */
public interface FeedbackRepository {

    List<FeedbackRequest> getFeedBacks(Map<String, String> params);

    boolean addFeedback(Feedback feedback);

}
