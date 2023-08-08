/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.repository;

import com.bookticket.dto.Api.ApiRoute;
import com.bookticket.pojo.Route;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vegar
 */
public interface RouteRepository {

    List<Object[]> getRoute();

    List<ApiRoute> getRouteDemo(Map<String, String> params);

    long countRoute();
//    int calculateTotalPages();

}
