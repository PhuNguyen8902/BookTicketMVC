/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller;

import com.bookticket.pojo.IncreasedPrice;
import com.bookticket.service.IncreasedPriceService;
import com.bookticket.service.TicketService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
@RestController
@RequestMapping("api/ticket/")
public class TicketController {

    @Autowired
    private TicketService ticketSer;

    @GetMapping("/checkSeat/{tripId}")
    public ResponseEntity<List<Short>> getListSeatOfTrip(@PathVariable int tripId) {
        List<Short> list = this.ticketSer.getAllSeatTicketByTripId(tripId);
        return ResponseEntity.ok(list);
    }
}
