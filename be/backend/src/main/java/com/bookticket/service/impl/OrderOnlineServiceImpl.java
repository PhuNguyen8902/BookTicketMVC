/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.service.impl;

import com.bookticket.pojo.OrderOnline;
import com.bookticket.repository.OrderOnlineRepository;
import com.bookticket.service.OrderOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADMIN
 */
@Service
@Transactional
public class OrderOnlineServiceImpl implements OrderOnlineService{

    @Autowired
    private OrderOnlineRepository orderRepo;
    
    @Override
    public boolean addOrder(String code, String message,String userId) {
        OrderOnline order = new OrderOnline();
        order.setCode(Integer.valueOf(code));
        order.setMessage(message);
        order.setUserId(userId);
        return this.orderRepo.addOrder(order);
    }
    
}
