/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.service.impl;

import com.bookticket.dto.Api.ApiRoute;
import com.bookticket.pojo.Route;
import com.bookticket.repository.RouteRepository;
import com.bookticket.service.RouteService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vegar
 */
@Service
@Transactional
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository RouteRepo;

    @Override
    public List<Map<String, Object>> getRoute() {
        List<Map<String, Object>> list = new ArrayList<>();

        for (Object[] o : this.RouteRepo.getRoute()) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", o[0]);
            map.put("name", o[1]);
            map.put("distance", o[2]);
            map.put("duration", o[3]);
            map.put("startStationName", o[4]);
            map.put("endStationName", o[5]);
            list.add(map);

        }

        return list;
    }

    @Override
    public List<ApiRoute> getRouteDemo(Map<String, String> params) {
        return this.RouteRepo.getRouteDemo(params);
//        List<Map<String, Object>> list = new ArrayList<>();
//        Map<String, Object> map = new HashMap<>();
//
//        for (Object[] o : this.RouteRepo.getRouteDemo()) {
//            map.put("id", o[0]);
//            map.put("name", o[1]);
//            map.put("distance", o[2]);
//            map.put("duration", o[3]);
//            map.put("startStationName", o[4]);
//            map.put("endStationName", o[5]);
//        }
//        list.add(map);
//
//        return list;
    }

    @Override
    public int calculateTotalPages()    {
        return this.RouteRepo.calculateTotalPages();
    }

}
