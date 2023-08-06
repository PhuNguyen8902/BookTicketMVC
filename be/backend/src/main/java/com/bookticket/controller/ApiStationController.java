
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller;

import com.bookticket.pojo.Station;
import com.bookticket.service.StationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vegar
 */
@RestController
public class ApiStationController {
    
    @Autowired
    private StationService stationService;  
    
    @GetMapping("/api/station")
    public ResponseEntity<List<Station>> getStation(){
        return new ResponseEntity<>(
                this.stationService.getStation(),
                HttpStatus.OK);
    }
}