/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.repository;

import com.bookticket.pojo.Station;
import java.util.List;

/**
 *
 * @author vegar
 */

public interface StationRepository {
    List<Station> getStation();
    List<Object[]> getNameStation();
    Station getStaionById(Integer id);
}
