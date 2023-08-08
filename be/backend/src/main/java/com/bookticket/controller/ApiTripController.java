package com.bookticket.controller;


import com.bookticket.dto.Api.ApiTrip;
import com.bookticket.service.TripService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    
    @GetMapping("/api/trip")
    public ResponseEntity<List<ApiTrip>> getTrips(@RequestParam Map<String, String> params){
        return ResponseEntity.ok(this.tripService.getTrips(params));
    }
}
