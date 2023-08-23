package com.bookticket.controller.jsp;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vegar
 */
@Controller
public class TicketControllerJsp {
    
    @GetMapping("/admin/ticket")
    public String getTickets(){
        
        return "ticket";
    }
    @GetMapping("/admin/addTicket")
    public String addTicket(){
        
        return "addTicket";
    }
    @GetMapping("/admin/editTicket")
    public String editTicket(){
        
        return "editTicket";
    }
}
