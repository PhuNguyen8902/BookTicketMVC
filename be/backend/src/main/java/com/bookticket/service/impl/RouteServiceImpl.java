/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.service.impl;

import com.bookticket.pojo.Route;
import com.bookticket.repository.RouteRepository;
import com.bookticket.service.RouteService;
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
public class RouteServiceImpl implements RouteService{

    @Autowired
    private RouteRepository RouteRepo;
    
    @Override
    public List<Route> getRoute() {
        return this.RouteRepo.getRoute();
    }
    
}
