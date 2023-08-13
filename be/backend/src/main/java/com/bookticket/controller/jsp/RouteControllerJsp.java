/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller.jsp;

import com.bookticket.dto.Api.ApiRoute;
import com.bookticket.dto.Request.RouteRequest;
import com.bookticket.pojo.Route;
import com.bookticket.pojo.User;
import com.bookticket.service.RouteService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.el.ELException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ADMIN
 */
@Controller
public class RouteControllerJsp {

    @Autowired
    private RouteService routeService;

    @GetMapping("/route")
    public String list(@RequestParam Map<String, String> params, Model model) {
        if (!params.containsKey("page")) {
            params.put("page", "1");
        }
        List<ApiRoute> routeList = this.routeService.getRouteDemo(params);
        model.addAttribute("route", routeList);
        model.addAttribute("totalPage", routeList.get(0).getTotalPage());
        return "route";
    }

    @PostMapping("/route")
    public String editRoute(@ModelAttribute(value = "route") @Valid ApiRoute p,
            BindingResult rs) {

        if (p.getEndStation() != null && p.getEndStation().equals(p.getStartStation())) {
            rs.rejectValue("endStation", "endStation.sameAsStart", "End Station cannot be the same as Start Station.");
            return "eachRoute";
        }
        if (rs.hasErrors()) {

            return "eachRoute";
        }
        RouteRequest routeRequest = new RouteRequest();
        routeRequest.setId(p.getId());
        routeRequest.setName(p.getName());
        routeRequest.setStartStation(Integer.valueOf(p.getStartStation()));
        routeRequest.setEndStation(Integer.valueOf(p.getEndStation()));
        routeRequest.setDistance(p.getDistance().toString());
        routeRequest.setDuration(p.getDuration().toString());
        if (this.routeService.editRoute(routeRequest) == true) {
            return "redirect:/route";
        }
        return "eachRoute";
    }

    @GetMapping("/route/{id}")
    public String routeDetail(Model model, @PathVariable(value = "id") Integer id) {
        Route route = this.routeService.getRouteById(id);
        ApiRoute apiRoute = new ApiRoute();
        apiRoute.setId(id);
        apiRoute.setName(route.getName());
        apiRoute.setStartStation(route.getStartStationId().getName());
        apiRoute.setEndStation(route.getEndStationId().getName());
        apiRoute.setDistance(route.getDistance());
        apiRoute.setDuration(route.getDuration());
        model.addAttribute("route", apiRoute);
        return "eachRoute";
    }

    @GetMapping("/route/add")
    public String newRoute(Model model) {
        model.addAttribute("addRouteModel", new RouteRequest());
        return "addRoute";
    }

    @PostMapping("/route/add")
    public String addRoute(@ModelAttribute(value = "addRouteModel") @Valid ApiRoute p,
            BindingResult rs, Model model) {

        if (p.getEndStation() != null && p.getEndStation().equals(p.getStartStation())) {
            rs.rejectValue("endStation", "endStation.sameAsStart", "End Station cannot be the same as Start Station.");
                        return "addRoute"; 
        }

        if (rs.hasErrors()) {
            return "addRoute"; 
        }

        RouteRequest routeRequest = new RouteRequest();
        routeRequest.setId(p.getId());
        routeRequest.setName(p.getName());
        routeRequest.setStartStation(Integer.valueOf(p.getStartStation()));
        routeRequest.setEndStation(Integer.valueOf(p.getEndStation()));
        routeRequest.setDistance(p.getDistance().toString());
        routeRequest.setDuration(p.getDuration().toString());

        if (this.routeService.addRoute(routeRequest)) {
            return "redirect:/route";
        }
        return "addRoute";
    }

}
