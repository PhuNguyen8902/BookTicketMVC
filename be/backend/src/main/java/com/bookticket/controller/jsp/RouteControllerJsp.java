/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller.jsp;

import com.bookticket.dto.Api.ApiRoute;
import com.bookticket.pojo.Route;
import com.bookticket.pojo.User;
import com.bookticket.service.RouteService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
