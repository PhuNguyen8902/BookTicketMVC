package com.bookticket.controller;

import com.bookticket.dto.Api.ApiRoute;
import com.bookticket.pojo.Route;
import com.bookticket.service.RouteService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("api/route")

public class ApiRouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping("/demo")
    public ResponseEntity<List<Map<String, Object>>> getRoute() {
//        return new ResponseEntity<>(
//                    this.routeService.getRoute(),
//                        HttpStatus.OK);
        return ResponseEntity.ok(this.routeService.getRoute());
    }

    @GetMapping("")
    public ResponseEntity<  List<ApiRoute>> getRouteDemo(@RequestParam Map<String, String> params) {
//        return new ResponseEntity<>(
//                    this.routeService.getRoute(),
//                        HttpStatus.OK);
        return ResponseEntity.ok(this.routeService.getRouteDemo(params));
    }
      @GetMapping("/total-pages")
    public ResponseEntity<Long> countPage() {
//        return new ResponseEntity<>(
//                    this.routeService.getRoute(),
//                        HttpStatus.OK);
        return ResponseEntity.ok(this.routeService.calculateTotalPages());
    }
}
