package com.bookticket.controller.jsp;


import com.bookticket.dto.Request.TicketRequest;
import com.bookticket.service.TicketService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    
    @Autowired
    private TicketService ticketService;
    
    @GetMapping("/admin/ticket")
    public String getTickets(@RequestParam Map<String, String> params, Model model){
         if (!params.containsKey("page")) {
            params.put("page", "1");
        }

        List<TicketRequest> tickets = ticketService.getTickets(params);
       
        model.addAttribute("tickets", tickets);
        if (tickets.size() != 0) {
            model.addAttribute("totalPage", tickets.get(0).getTotalPage());
        }

        return "ticket";
    }
    @GetMapping("/admin/add")
    public String addTicket(){
        
        return "addTicket";
    }
    @GetMapping("/admin/editTicket")
    public String editTicket(){
        
        return "editTicket";
    }
}
