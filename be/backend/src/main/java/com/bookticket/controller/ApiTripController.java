package com.bookticket.controller;


import com.bookticket.dto.Api.ApiChangeTicket;
import com.bookticket.dto.Api.ApiTicketRequest;
import com.bookticket.dto.Api.ApiTrip;
import com.bookticket.dto.Message;
import com.bookticket.pojo.Trip;
import com.bookticket.service.TicketService;
import com.bookticket.service.TripService;
import java.util.List;
import java.util.Map;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vegar
 */
@RestController
public class ApiTripController {
    
    @Autowired
    private TripService tripService;
    
    @Autowired
    private TicketService ticketSer;
    
    @GetMapping("/api/trip")
    public ResponseEntity<List<ApiTrip>> getTrips(@RequestParam Map<String, String> params){
        return ResponseEntity.ok(this.tripService.getTrips(params));
    }
      @GetMapping("api/trip/get/{routeId}")
    public ResponseEntity<List<ApiTrip>> getListTripByRoute(@PathVariable Integer routeId) {
        List<ApiTrip> list = this.tripService.getListTripByRoute(routeId);
 
        return ResponseEntity.ok(list);
    }
    
  
}
