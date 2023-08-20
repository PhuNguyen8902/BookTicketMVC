/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bookticket.service;

import com.bookticket.pojo.OrderOnline;

/**
 *
 * @author ADMIN
 */
public interface OrderOnlineService {
    boolean addOrder(String code,String message,String userId);
}
