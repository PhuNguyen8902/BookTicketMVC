/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository.impl;

import com.bookticket.pojo.Vehicle;
import com.bookticket.repository.VehicleRepository;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vegar
 */
@Repository
public class VehicleRepositoryImpl implements VehicleRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Vehicle> getVehicles(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Vehicle> query = b.createQuery(Vehicle.class);
        Root rVehicle = query.from(Vehicle.class);
        
        query = query.select(rVehicle);
        
        
        Query q = s.createQuery(query);
        return q.getResultList();
    }
    
    
}
