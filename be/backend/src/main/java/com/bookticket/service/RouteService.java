/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.service;

import com.bookticket.dto.Api.ApiRoute;
import com.bookticket.pojo.Route;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vegar
 */

public interface RouteService {
    List<Map<String, Object>> getRoute();
//        List<Map<String, Object>> getRouteDemo();
    List<ApiRoute> getRouteDemo(Map<String, String> params);
    int calculateTotalPages();

}
