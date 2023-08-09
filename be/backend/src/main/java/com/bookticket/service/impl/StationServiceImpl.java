/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.service.impl;

import com.bookticket.pojo.Station;
import com.bookticket.repository.StationRepository;
import com.bookticket.service.StationService;
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
public class StationServiceImpl implements StationService {

    @Autowired
    private StationRepository StationRepo;

    @Override
    public List<Station> getStation() {
        return StationRepo.getStation();
    }

    @Override
    public List<Map<String, Object>> getNameStation() {
        List<Map<String, Object>> list = new ArrayList<>();

        for (Object[] o : this.StationRepo.getNameStation()) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", o[0]);
            map.put("name", o[1]);
         
            list.add(map);

        }

        return list;
    }

    @Override
    public Station getStaionById(Integer id) {
        return this.StationRepo.getStaionById(id);
    }
}
