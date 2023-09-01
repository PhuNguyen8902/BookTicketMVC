/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller;

import com.bookticket.dto.Request.TicketRequest;
import com.bookticket.pojo.Ticket;
import com.bookticket.service.TicketService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vegar
 */
@RestController
public class ApiTicketController {
    
    @Autowired
    private TicketService ticketService;
    
    @GetMapping("/api/ticket")
    public ResponseEntity<List<TicketRequest>> getTickets(Map<String, String> params){
        return ResponseEntity.ok(this.ticketService.getOnlTickets(params));
    }
}
