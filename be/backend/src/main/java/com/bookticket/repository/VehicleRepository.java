/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.repository;

import com.bookticket.pojo.Vehicle;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vegar
 */
public interface VehicleRepository {
    List<Vehicle> getVehicles(Map<String, String> params); 
}
