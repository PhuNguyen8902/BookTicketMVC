/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.repository;

import com.bookticket.dto.Request.VehicleRequest;
import com.bookticket.pojo.Vehicle;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vegar
 */
public interface VehicleRepository {
    List<VehicleRequest> getVehicles(Map<String, String> params); 
    List<Vehicle> getVehicleName();
    boolean addVehicle(Vehicle vehicle);
    boolean editVehicle(Vehicle vehicle);
    boolean deleteVehicle(Vehicle vehicle);
    Vehicle getVehicleById(Integer id);
}
