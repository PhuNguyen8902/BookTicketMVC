/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.service;

import com.bookticket.dto.Api.ApiTrip;

import com.bookticket.dto.Request.TripRequest;
import com.bookticket.pojo.Trip;

import com.bookticket.dto.Response.TripChartResponse;

import java.util.List;
import java.util.Map;

/**
 *
 * @author vegar
 */
public interface TripService {
    List<ApiTrip> getTrips(Map<String, String> params);

    List<TripRequest> getAdminTrips(Map<String, String> params);
    boolean addTrip(Trip trip);
    boolean editTrip(Trip trip);
    boolean deleteTrip(Trip trip);
    Trip getTripById(Integer id);

      List<TripChartResponse> getListRouteCountsInTrip(Map<String, String> params);


}
