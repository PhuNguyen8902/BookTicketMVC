/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller;

import com.bookticket.dto.Request.VehicleRequest;
import com.bookticket.service.VehicleService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vegar
 */
@RestController
public class ApiVehicleController {
    
    @Autowired
    private VehicleService vehicleService;
    
    @GetMapping("/api/vehicle")
    public ResponseEntity<List<VehicleRequest>> getTrips(@RequestParam Map<String, String> params){
        return ResponseEntity.ok(this.vehicleService.getVehicles(params));
    }
    
}
