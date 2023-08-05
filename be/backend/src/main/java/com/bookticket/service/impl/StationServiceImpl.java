/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.service.impl;

import com.bookticket.pojo.Station;
import com.bookticket.repository.StationRepository;
import com.bookticket.service.StationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vegar
 */
@Service
@Transactional
public class StationServiceImpl implements StationService{
    
    @Autowired
    private StationRepository StationRepo;
    
    @Override
    public List<Station> getStation() {
        return StationRepo.getStation();
    }
}
