/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository.impl;

import com.bookticket.pojo.Route;
import com.bookticket.pojo.Station;
import com.bookticket.pojo.Trip;
import com.bookticket.pojo.Vehicle;
import com.bookticket.repository.TripRepository;
import java.sql.Driver;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vegar
 */
@Repository
public class TripRepositoryImpl implements TripRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Object[]> getTrips(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = b.createQuery(Object[].class);

        Root rTrip = query.from(Trip.class);
        Join<Vehicle, Trip> vehicleJoin = rTrip.join("vehicleId");
        Join<Driver, Trip> driverJoin = rTrip.join("driverId");
        Join<Route, Trip> routeJoin = rTrip.join("routeId");
        
        Join<Route, Station> startStationJoin = routeJoin.join("startStationId");
        Join<Route, Station> endStationJoin = routeJoin.join("endStationId");
       

//        routeJoin.get("startStationId");
        query.multiselect(
                rTrip.get("id"),
                rTrip.get("departureTime"),
                rTrip.get("arrivalTime"),
                rTrip.get("price"),
                vehicleJoin.get("seatCapacity"),
                driverJoin.get("name"),
                startStationJoin.get("name"),
                endStationJoin.get("name")
);

        query.groupBy(rTrip.get("id"));

        Query q = s.createQuery(query);
//        List<Object[]> resultList = q.getResultList();

        return q.getResultList();
    }

}
