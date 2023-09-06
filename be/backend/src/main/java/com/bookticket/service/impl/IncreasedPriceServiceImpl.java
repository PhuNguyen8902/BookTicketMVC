/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.service.impl;

import com.bookticket.dto.Request.IncreasedPriceRequest;
import com.bookticket.pojo.IncreasedPrice;
import com.bookticket.repository.IncreasedPriceRepository;
import com.bookticket.service.IncreasedPriceService;
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
public class IncreasedPriceServiceImpl implements IncreasedPriceService{

    @Autowired
    private IncreasedPriceRepository increasedPriceRepository;
            
    @Override
    public List<IncreasedPriceRequest> getIncreasedPrice(Map<String, String> params) {
        return this.increasedPriceRepository.getIncreasedPrice(params);
    }

    @Override
    public List<IncreasedPrice> getIncreasedPriceInfo() {
        return this.increasedPriceRepository.getIncreasedPriceInfo();
    }

    @Override
    public boolean addIncreasedPrice(IncreasedPrice ip) {
        return this.increasedPriceRepository.addIncreasedPrice(ip);
    }

    @Override
    public boolean editIncreasedPrice(IncreasedPrice ip) {
        return this.increasedPriceRepository.editIncreasedPrice(ip);
    }

    @Override
    public IncreasedPrice getIncreasedPriceById(Integer id) {
        return this.increasedPriceRepository.getIncreasedPriceById(id);
    }

    @Override
    public boolean deleteIncreasedPrice(IncreasedPrice ip) {
        return this.increasedPriceRepository.deleteIncreasedPrice(ip);
    }

}
