/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.service.impl;

import com.bookticket.dto.Request.VehicleRequest;
import com.bookticket.pojo.Vehicle;
import com.bookticket.repository.VehicleRepository;
import com.bookticket.service.VehicleService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vegar
 */
@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    
    @Autowired
    private Environment env;

    @Override
    public List<VehicleRequest> getVehicles(Map<String, String> params) {

        return this.vehicleRepository.getVehicles(params);
    }

    @Override
    public boolean addVehicle(VehicleRequest vr) {
        Vehicle vehicle = new Vehicle();
        
        vehicle.setId(vr.getId());
        vehicle.setSeatCapacity(vr.getSeatCapacity());
        vehicle.setLicensePlate(vr.getLicensePlate());
        
        
        return this.vehicleRepository.addVehicle(vehicle);
    }

    @Override
    public boolean editVehicle(VehicleRequest vr) {
        Vehicle vehicle = new Vehicle();
        
        vehicle.setId(vr.getId());
        vehicle.setSeatCapacity((vr.getSeatCapacity()));
        vehicle.setLicensePlate(vr.getLicensePlate());
        
        return this.vehicleRepository.editVehicle(vehicle);
    }

    @Override
    public boolean deleteVehicle(VehicleRequest vr) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public Vehicle getVehicleById(Integer id){
        return vehicleRepository.getVehicleById((id));
    }

    @Override
    public List<Vehicle> getVehicleName() {
        return this.vehicleRepository.getVehicleName();
    }

}
