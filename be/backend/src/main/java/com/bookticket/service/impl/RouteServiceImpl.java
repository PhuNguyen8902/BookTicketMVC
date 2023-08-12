/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.service.impl;

import com.bookticket.dto.Api.ApiRoute;
import com.bookticket.dto.Request.RouteRequest;
import com.bookticket.pojo.Route;
import com.bookticket.pojo.Station;
import com.bookticket.pojo.StationRoute;
import com.bookticket.repository.RouteRepository;
import com.bookticket.repository.StationRepository;
import com.bookticket.repository.StationRouteRepository;
import com.bookticket.service.RouteService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;
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
    @Autowired
    private StationRepository stationRepo;

    @Autowired
    private StationRouteRepository stationRouteRepo;

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
    public boolean addRoute(RouteRequest createRoute) {
        Integer startStationId = createRoute.getStartStation();
        Integer endStationId = createRoute.getEndStation();
        Station startStation = this.stationRepo.getStaionById(startStationId);
        Station endStation = this.stationRepo.getStaionById(endStationId);
        short isActive = 1;

        Route route = new Route();
        route.setName(createRoute.getName());
        route.setDistance(Double.valueOf(createRoute.getDistance()));
        route.setDuration(Double.valueOf(createRoute.getDuration()));
        route.setStartStationId(startStation);
        route.setEndStationId(endStation);
        route.setIsActive(isActive);

        return this.RouteRepo.addRoute(route);
    }

    @Override
    public boolean editRoute(RouteRequest editRoute) {
        Integer startStationId = editRoute.getStartStation();
        Integer endStationId = editRoute.getEndStation();
        Station startStation = this.stationRepo.getStaionById(startStationId);
        Station endStation = this.stationRepo.getStaionById(endStationId);
        short isActive = 1;

        Route route = new Route();
        route.setId(editRoute.getId());
        route.setName(editRoute.getName());
        route.setDistance(Double.valueOf(editRoute.getDistance()));
        route.setDuration(Double.valueOf(editRoute.getDuration()));
        route.setStartStationId(startStation);
        route.setEndStationId(endStation);
        route.setIsActive(isActive);

        boolean rs = this.RouteRepo.editRoute(route);
        if (rs == true) {
            Integer routeId = editRoute.getId();
            List<StationRoute> stationRouteList = this.stationRouteRepo.findStationRouteByTwoId(routeId);

            StationRoute r1 = new StationRoute();
            r1.setId(stationRouteList.get(0).getId());
            r1.setRouteId(stationRouteList.get(0).getRouteId());
            r1.setStationId(startStation);
            StationRoute r2 = new StationRoute();
            r2.setId(stationRouteList.get(1).getId());
            r2.setRouteId(stationRouteList.get(1).getRouteId());
            r2.setStationId(endStation);

            boolean rs1 = this.stationRouteRepo.editStationRoute(r1);
            boolean rs2 = this.stationRouteRepo.editStationRoute(r2);
            return true;
        }

        return rs;
    }
    @Override
    public boolean deleteRoute(RouteRequest editRoute) {
        Integer startStationId = editRoute.getStartStation();
        Integer endStationId = editRoute.getEndStation();
        Station startStation = this.stationRepo.getStaionById(startStationId);
        Station endStation = this.stationRepo.getStaionById(endStationId);
        short isActive = 0;

        Route route = new Route();
        route.setId(editRoute.getId());
        route.setName(editRoute.getName());
        route.setDistance(Double.valueOf(editRoute.getDistance()));
        route.setDuration(Double.valueOf(editRoute.getDuration()));
        route.setStartStationId(startStation);
        route.setEndStationId(endStation);
        route.setIsActive(isActive);

        boolean rs = this.RouteRepo.deleteRoute(route);
        return rs;
    }

    @Override
    public ApiRoute getRouteById(Integer intgr) {
        return this.RouteRepo.getRouteById(intgr);
    }
}
