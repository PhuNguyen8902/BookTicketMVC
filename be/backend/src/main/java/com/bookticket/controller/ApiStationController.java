
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller;

import com.bookticket.dto.Api.ApiStation;
import com.bookticket.service.StationService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vegar
 */
@RestController
@RequestMapping("api/station")

public class ApiStationController {
    
    @Autowired
    private StationService stationService;  
    
    @GetMapping("")
    public ResponseEntity<List<ApiStation>> getStation(){
        return new ResponseEntity<>(
                this.stationService.getStation(),
                HttpStatus.OK);
    }
    @GetMapping("/name")
    public ResponseEntity<List<Map<String, Object>>> getNameStation(){
        return new ResponseEntity<>(
                this.stationService.getNameStation(),
                HttpStatus.OK);
    }
}
