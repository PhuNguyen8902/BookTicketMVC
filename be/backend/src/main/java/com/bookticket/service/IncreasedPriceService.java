/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.service;

import com.bookticket.dto.Request.IncreasedPriceRequest;
import com.bookticket.pojo.IncreasedPrice;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vegar
 */
public interface IncreasedPriceService {
    List<IncreasedPriceRequest> getIncreasedPrice(Map<String, String> params);
    boolean addIncreasedPrice(IncreasedPrice ip);
    boolean editIncreasedPrice(IncreasedPrice ip);
    boolean deleteIncreasedPrice(IncreasedPrice ip);
    IncreasedPrice getIncreasedPriceById(Integer id);
    List<IncreasedPrice> getIncreasedPriceInfo();
}
