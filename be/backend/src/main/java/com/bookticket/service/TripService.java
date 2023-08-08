/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.service;

import com.bookticket.dto.Api.ApiTrip;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vegar
 */
public interface TripService {
    List<ApiTrip> getTrips(Map<String, String> params);
}
