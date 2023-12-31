/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller.jsp;

import com.bookticket.dto.Response.RevenueChartResponse;
import com.bookticket.dto.Response.TripChartResponse;
import com.bookticket.service.TicketService;
import com.bookticket.service.TripService;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ADMIN
 */
@Controller
public class ChartControllerJsp {

    @Autowired
    private TripService tripService;

    @Autowired
    private TicketService ticketService;

    @GetMapping("/admin/chart")
    public String listBase(Model model) {
        return "chartBase";
    }

    @GetMapping("/admin/chart/trip")
    public String listTrip(Model model) {
        return "chartTrip";
    }

    @GetMapping("/admin/chart/revenue")
    public String listRevenue(Model model) {
        return "chartRevenue";
    }

    @GetMapping("/admin/chart/trip/data")
    public ResponseEntity<?> getRouteToChart(@RequestParam Map<String, String> params) {
        
        List<TripChartResponse> list = this.tripService.getListRouteCountsInTrip(params);
              if(list.isEmpty()){
                          return ResponseEntity.ok(Collections.emptyList());

              }else{

        return ResponseEntity.ok(list);}
    }

    @GetMapping("/admin/chart/revenue/data")
    public ResponseEntity<List<RevenueChartResponse>> getRevenueToChart(@RequestParam Map<String, String> params) {
        List<RevenueChartResponse> list = this.ticketService.getListRevenueInTicket(params);
        return ResponseEntity.ok(list);

    }
}
