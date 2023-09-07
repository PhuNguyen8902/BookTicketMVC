/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller;

import com.bookticket.dto.Api.ApiTicketRequest;
import com.bookticket.dto.Message;
import com.bookticket.dto.Request.RouteRequest;
import com.bookticket.pojo.IncreasedPrice;
import com.bookticket.service.IncreasedPriceService;
import com.bookticket.service.TicketService;
import java.util.List;
import java.util.Objects;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTicket(@RequestBody ApiTicketRequest createTicket) {
        boolean rs = this.ticketSer.addOnlTicket(createTicket);
        if (rs) {
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("suscess", "Successful booking.");

            return ResponseEntity.status(HttpStatus.CREATED).body(jsonResponse.toString());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.builder().message("Create Fail").build());

        }
    }
}
