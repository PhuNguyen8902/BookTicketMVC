/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.service;

import com.bookticket.dto.Api.ApiRoute;
import com.bookticket.dto.Request.RouteRequest;
import com.bookticket.pojo.Route;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vegar
 */
public interface RouteService {

    List<Map<String, Object>> getRoute();

    List<ApiRoute> getRouteDemo(Map<String, String> params);

    boolean addRoute(RouteRequest createRoute);

    boolean editRoute(RouteRequest editRoute);

    boolean deleteRoute(RouteRequest editRoute);

    Route getRouteById(Integer id);

}
