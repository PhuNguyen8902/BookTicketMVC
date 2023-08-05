package com.bookticket.controller;


import com.bookticket.pojo.Route;
import com.bookticket.service.RouteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
public class ApiRouteController {
    
    @Autowired
    private RouteService routeService;
    
    @GetMapping("/api/route")
    public ResponseEntity<List<Route>> getRoute(){
        return new ResponseEntity<>(
                    this.routeService.getRoute(),
                        HttpStatus.OK);
    }
}
