/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller.jsp;

import com.bookticket.service.AddressService;
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
public class AddressControllerJsp {
    
    @Autowired
    private AddressService addressService;
    
    @ModelAttribute
    public void getAdminVehicle(Model model){
        model.addAttribute("addressInfo", this.addressService.getAdminAdressInfo());
    }
}
