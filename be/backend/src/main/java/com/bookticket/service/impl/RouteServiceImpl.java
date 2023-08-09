/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.service.impl;

import com.bookticket.dto.Api.ApiRoute;
import com.bookticket.dto.Message;
import com.bookticket.dto.Request.CreateRouteRequest;
import com.bookticket.pojo.Route;
import com.bookticket.pojo.Station;
import com.bookticket.repository.RouteRepository;
import com.bookticket.repository.StationRepository;
import com.bookticket.service.RouteService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private StationRepository stationRepo;

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
    }

    @Override
    public boolean addRoute(CreateRouteRequest createRoute) {
        Integer startStationId = createRoute.getStartStation();
        Integer endStationId = createRoute.getEndStation();
        Station startStation = this.stationRepo.getStaionById(startStationId);
        Station endStation = this.stationRepo.getStaionById(endStationId);

        Route route = new Route();
        route.setName(createRoute.getName());
        route.setDistance(Double.valueOf(createRoute.getDistance()));
        route.setDuration(Double.valueOf(createRoute.getDuration()));
        route.setStartStationId(startStation);
        route.setEndStationId(endStation);

        return this.RouteRepo.addRoute(route);
    }
}
