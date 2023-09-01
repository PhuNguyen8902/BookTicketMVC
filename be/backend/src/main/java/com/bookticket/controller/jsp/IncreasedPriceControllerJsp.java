/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller.jsp;

import com.bookticket.service.IncreasedPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author vegar
 */
@Controller
@ControllerAdvice
public class IncreasedPriceControllerJsp {
    
    @Autowired
    private IncreasedPriceService increasedPriceService;
    
    @ModelAttribute
    public void getIncreasedPriceInfo(Model model){
        model.addAttribute("IncreasedPriceInfo", this.increasedPriceService.getIncreasedPriceInfo());
    }
}
