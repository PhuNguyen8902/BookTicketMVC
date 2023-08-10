package com.bookticket.controller;

import com.bookticket.dto.Api.ApiRoute;
import com.bookticket.dto.Message;
import com.bookticket.dto.Request.RouteRequest;
import com.bookticket.dto.Request.RegisterRequest;
import com.bookticket.dto.Response.TokenResponse;
import com.bookticket.pojo.Route;
import com.bookticket.service.RouteService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
        return ResponseEntity.ok(this.routeService.getRouteDemo(params));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addRoute(@RequestBody RouteRequest createRoute) {
        Integer startStationId = createRoute.getStartStation();
        Integer endStationId = createRoute.getEndStation();
        if (Objects.equals(startStationId, endStationId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.builder().name("endStation").message("Can't choose the same 2 stations").build());

        }
        try {
            Double distance = Double.valueOf(createRoute.getDistance());
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.builder().name("distance").message("Please enter the number").build());

        }
        try {
            Double duration = Double.valueOf(createRoute.getDuration());
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.builder().name("duration").message("Please enter the number").build());

        }
        boolean rs = this.routeService.addRoute(createRoute);
        if (rs) {
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("suscess", "Sản phẩm đã được thêm thành công.");

            return ResponseEntity.status(HttpStatus.CREATED).body(jsonResponse.toString());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.builder().message("Create Fail").build());

        }

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseEntity<?> editRoute(@RequestBody RouteRequest editRoute) {
        Integer startStationId = editRoute.getStartStation();
        Integer endStationId = editRoute.getEndStation();
        if (Objects.equals(startStationId, endStationId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.builder().name("endStation").message("Can't choose the same 2 stations").build());

        }
        try {
            Double distance = Double.valueOf(editRoute.getDistance());
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.builder().name("distance").message("Please enter the number").build());

        }
        try {
            Double duration = Double.valueOf(editRoute.getDuration());
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.builder().name("duration").message("Please enter the number").build());

        }
        boolean rs = this.routeService.editRoute(editRoute);
        if (rs) {
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("suscess", "Sản phẩm đã được thay đổi thành công.");

            return ResponseEntity.status(HttpStatus.CREATED).body(jsonResponse.toString());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.builder().message("Edit Fail").build());

        }

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<?> deleteRoute(@RequestBody RouteRequest editRoute) {
     
        boolean rs = this.routeService.deleteRoute(editRoute);
        if (rs) {
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("suscess", "Sản phẩm đã được xóa thành công.");

            return ResponseEntity.status(HttpStatus.CREATED).body(jsonResponse.toString());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.builder().message("Delete Fail").build());

        }

    }

}
