/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.service;

import com.bookticket.dto.Api.ApiStation;
import com.bookticket.dto.Request.StationRequest;
import com.bookticket.pojo.Station;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vegar
 */
public interface StationService {
    List<ApiStation> getStation();
    List<StationRequest> getAdminStation(Map<String, String> params);
    boolean addStation(Station station);
    boolean editStation(Station station);
    boolean deleteStation(Station station);
       List<Map<String, Object>> getNameStation();
       Station getStaionById(Integer id);
           Station getStationByName(String name);


}
