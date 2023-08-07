/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.service.impl;

import com.bookticket.repository.TripRepository;
import com.bookticket.service.TripService;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
public class TripServiceImpl implements TripService{

    @Autowired
    private TripRepository tripRepository;
    
    @Override
    public List<Map<String, Object>> getTrips() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        // set e
        NumberFormat formatter = new DecimalFormat("###.#####");
        
        for (Object[] o : this.tripRepository.getTrips()){
            map.put("id", o[0]);
            map.put("departureTime", o[1]);
            map.put("arrivalTime", o[2]);
            map.put("price", formatter.format(o[3]));
        }
        
        list.add(map);
        return list;
    }
    
    
}
