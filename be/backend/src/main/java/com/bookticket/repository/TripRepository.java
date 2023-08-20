/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.repository;

import com.bookticket.dto.Api.ApiTrip;
import com.bookticket.dto.Response.TripChartResponse;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vegar
 */
public interface TripRepository {
    List<Object[]> getTrips(Map<String, String> params);
    List<TripChartResponse> getListRouteCountsInTrip();
}
