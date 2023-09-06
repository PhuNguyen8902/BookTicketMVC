/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.service.impl;

import com.bookticket.dto.Api.ApiTrip;

import com.bookticket.dto.Request.TripRequest;
import com.bookticket.pojo.Trip;

import com.bookticket.dto.Response.TripChartResponse;

import com.bookticket.repository.TripRepository;
import com.bookticket.service.TripService;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class TripServiceImpl implements TripService{

    @Autowired
    private TripRepository tripRepository;
    
    @Override
    public List<ApiTrip> getTrips(Map<String, String> params) {
        List<ApiTrip> trip = new ArrayList<>();
        List<Object[]> l = this.tripRepository.getTrips(params);
        DecimalFormat decimalFormat = new DecimalFormat("#.####"); // Format to four decimal places
        
        for (Object[] o : l){
            ApiTrip t = new ApiTrip();
            
            t.setId((Integer)o[0]);
            t.setDepartureTime((Date) o[1]);
            t.setArrivalTime((Date) o[2]);
            
            String formattedValue = decimalFormat.format(o[3]);
            t.setPrice(formattedValue);
            
            t.setSeatCapacity((Short) o[4]);
            t.setDriverName((String) o[5]);
            t.setStartStation((String) o[6]);
            t.setEndStation((String) o[7]);
            t.setRouteId((Integer) o[8]);
            t.setVehicleId((Integer) o[9]);
            trip.add(t);
        }
        
        return trip;
    }

    @Override

    public List<TripRequest> getAdminTrips(Map<String, String> params) {
        return this.tripRepository.getAdminTrips(params);
    }

    @Override
    public boolean addTrip(Trip trip) {
        if(this.tripRepository.editTrip(trip)){
            return true;
        }
        return false;
    }

    @Override
    public boolean editTrip(Trip tr) {
        if(this.tripRepository.editTrip(tr))
            return true;
        return false;
    }

    @Override
    public boolean deleteTrip(Trip tr) {
        return this.tripRepository.deleteTrip(tr);
    }

    @Override
    public Trip getTripById(Integer id) {
        return this.tripRepository.getTripById(id);
    }

 

    public List<TripChartResponse> getListRouteCountsInTrip(Map<String, String> params) {
        return this.tripRepository.getListRouteCountsInTrip(params);
    }

    @Override
    public List<TripRequest> getTripInfo() {
        
        List<Object[]> trips = this.tripRepository.getTripInfo();
        List<TripRequest> tripRequests = new ArrayList<>();
        for(Object[] trip : trips){
            TripRequest tripRequest = new TripRequest();
            tripRequest.setId((Integer) trip[0]);
            tripRequest.setDepartureTime((String) trip[1].toString());
            tripRequest.setArrivalTime((String) trip[2].toString());
            tripRequest.setRouteName((String) trip[3]);
            
            tripRequests.add(tripRequest);
        }
        
        return tripRequests;
    }

    @Override
    public List<TripRequest> getTripsByDriverId(Map<String, String> map, String id) {
        return this.tripRepository.getTripsByDriverId(map, id);
    }


    
    
}
