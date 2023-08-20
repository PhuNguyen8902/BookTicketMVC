/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository.impl;

import com.bookticket.pojo.OrderOnline;
import com.bookticket.pojo.User;
import com.bookticket.repository.OrderOnlineRepository;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ADMIN
 */
@Repository
public class OrderOnlineRepositoryImpl implements OrderOnlineRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public boolean addOrder(OrderOnline order) {
        try {
            Session s = this.factory.getObject().getCurrentSession();
            s.save(order);
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }
}
