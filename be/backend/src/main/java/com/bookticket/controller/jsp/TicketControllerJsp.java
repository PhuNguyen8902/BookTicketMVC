package com.bookticket.controller.jsp;


import com.bookticket.dto.Request.TicketRequest;
import com.bookticket.pojo.Ticket;
import com.bookticket.service.TicketService;
import java.util.List;
import java.util.Map;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
@ControllerAdvice
public class TicketControllerJsp {
    
    @Autowired
    private TicketService ticketService;
    
    @GetMapping("/admin/onlTicket")
    public String getOnlTickets(@RequestParam Map<String, String> params, Model model){
         if (!params.containsKey("page")) {
            params.put("page", "1");
        }

        List<TicketRequest> tickets = ticketService.getOnlTickets(params);
       
        model.addAttribute("tickets", tickets);
        if (tickets.size() != 0) {
            model.addAttribute("totalPage", tickets.get(0).getTotalPage());
        }

        return "onlTicket";
    }
    @GetMapping("/admin/add")
    public String addTicket(){
        
        return "addOnlTicket";
    }
    
    @GetMapping("/admin/editTicket")
    public String editTicket(){
        
        return "editOnlTicket";
    }
    
    @GetMapping("/admin/offTicket")
    public String getOffTickets(@RequestParam Map<String, String> params, Model model){
         if (!params.containsKey("page")) {
            params.put("page", "1");
        }

        List<TicketRequest> tickets = ticketService.getOffTickets(params);
       
        model.addAttribute("tickets", tickets);
        if (tickets.size() != 0) {
            model.addAttribute("totalPage", tickets.get(0).getTotalPage());
        }

        return "offTicket";
    }
    @GetMapping("/admin/offTicket/{id}")
    public String offTicketDetail(Model model, @PathVariable(value = "id") Integer id){
        
        Ticket ticket = this.ticketService.getTicketById(id);
        
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setId(id);
        ticketRequest.setUserName(ticket.getName());
        ticketRequest.setSeat(Integer.valueOf(ticket.getSeat()));
        
        ticketRequest.setDate(ticket.getDate().toString());
        
        model.addAttribute("ticket", ticketRequest);
        return "editOffTicket";
    }
    
    
    
}
