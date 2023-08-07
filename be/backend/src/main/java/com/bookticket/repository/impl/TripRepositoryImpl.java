/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository.impl;

import com.bookticket.pojo.Route;
import com.bookticket.pojo.Station;
import com.bookticket.pojo.Trip;
import com.bookticket.repository.TripRepository;
import java.util.List;
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
public class TripRepositoryImpl implements TripRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Object[]> getTrips() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = b.createQuery(Object[].class);
        Root rTrip = query.from(Trip.class);
        Root rRoute = query.from(Route.class);
        Root rStation = query.from(Station.class);
        
        
        query.multiselect(
                rTrip.get("id"),
                rTrip.get("departureTime"),
                rTrip.get("arrivalTime"),
                rTrip.get("price")
        
        );
        
        
        query.groupBy(rTrip.get("id"));
        
        Query q = s.createQuery(query);
        
        
        return q.getResultList();
    }
    
            
}
