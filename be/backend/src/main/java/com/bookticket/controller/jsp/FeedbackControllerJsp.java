/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller.jsp;

import com.bookticket.dto.Request.FeedbackRequest;
import com.bookticket.service.FeedbackService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author vegar
 */
@Controller
public class FeedbackControllerJsp {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("admin/feedback")
    public String getFeedbacks(@RequestParam Map<String, String> params, Model model) {

        if (!params.containsKey("page")) {
            params.put("page", "1");
        }

        List<FeedbackRequest> list = this.feedbackService.getFeedBacks(params);

        model.addAttribute("feedback", list);

        if (!list.isEmpty()) {
            model.addAttribute("totalPage", list.get(0).getTotalPage());
        }

        return "feedback";
    }
    
    @GetMapping("employee/feedback")
    public String getFeedbacksForEmployee(@RequestParam Map<String, String> params, Model model) {

        if (!params.containsKey("page")) {
            params.put("page", "1");
        }

        List<FeedbackRequest> list = this.feedbackService.getFeedBacks(params);

        model.addAttribute("feedback", list);

        if (!list.isEmpty()) {
            model.addAttribute("totalPage", list.get(0).getTotalPage());
        }

        return "feedback";
    }
}
