/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.repository;

import com.bookticket.pojo.StationRoute;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface StationRouteRepository {

    boolean addStationRoute(StationRoute stationRoute);

    boolean editStationRoute(StationRoute stationRoute);

     List<StationRoute> findStationRouteByTwoId( Integer routeId);

}
