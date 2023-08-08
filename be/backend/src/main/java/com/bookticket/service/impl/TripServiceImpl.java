/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.service.impl;

import com.bookticket.dto.Api.ApiTrip;
import com.bookticket.repository.TripRepository;
import com.bookticket.service.TripService;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class TripServiceImpl implements TripService{

    @Autowired
    private TripRepository tripRepository;
    
    @Override
    public List<ApiTrip> getTrips(Map<String, String> params) {
        List<ApiTrip> trip = new ArrayList<>();
         
        for (Object[] o : this.tripRepository.getTrips(params)){
            ApiTrip t = new ApiTrip();
            
            t.setId((Integer)o[0]);
            t.setDepartureTime((Date) o[1]);
            t.setArrivalTime((Date) o[2]);
            t.setPrice((Double) o[3]);
            t.setSeatCapacity((Short) o[4]);
            t.setDriverName((String) o[5]);
            t.setStartStation((String) o[6]);
            t.setEndStation((String) o[7]);
            trip.add(t);
        }
        
        return trip;
    }
    
    
}
