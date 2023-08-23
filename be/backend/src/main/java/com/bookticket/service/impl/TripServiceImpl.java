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
            trip.add(t);
        }
        
        return trip;
    }

    @Override

    public List<TripRequest> getAdminTrips(Map<String, String> params) {
        return this.tripRepository.getAdminTrips(params);
    }

    @Override
    public boolean addTrip(TripRequest trip) {
        Trip t = new Trip();
        t.setId(trip.getId());
        t.setArrivalTime(trip.getArrivalTime());
        t.setDepartureTime(trip.getDepartureTime());
        t.setPrice(Double.valueOf(trip.getPrice()));
        
        
        if(this.tripRepository.addTrip(t)){
            return true;
        }
        return false;
    }

    @Override
    public boolean editTrip(TripRequest tr) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteTrip(TripRequest tr) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Trip getTripById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

 

    public List<TripChartResponse> getListRouteCountsInTrip(Map<String, String> params) {
        return this.tripRepository.getListRouteCountsInTrip(params);
    }


    
    
}
