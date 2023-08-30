/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.service.impl;

import com.bookticket.dto.Request.StationRequest;
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
        return this.StationRepo.getStation();
    }
    @Override
    public List<StationRequest> getAdminStation(Map<String, String> params) {
        return this.StationRepo.getAdminStation(params);
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

    @Override
    public Station getStationByName(String name) {
        return this.StationRepo.getStationByName(name);
    }

    @Override
    public boolean addStation(Station stn) {
        return this.StationRepo.addStation(stn);
    }

    @Override
    public boolean editStation(Station stn) {
        return this.StationRepo.editStation(stn);
    }

    @Override
    public boolean deleteStation(Station stn) {
        return this.StationRepo.deleteStation(stn);
    }
}
