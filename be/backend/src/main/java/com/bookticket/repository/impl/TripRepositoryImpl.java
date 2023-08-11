/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository.impl;

import com.bookticket.pojo.Route;
import com.bookticket.pojo.Station;
import com.bookticket.pojo.Trip;
import com.bookticket.pojo.User;
import com.bookticket.pojo.Vehicle;
import com.bookticket.repository.TripRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
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
public class TripRepositoryImpl implements TripRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Object[]> getTrips(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = b.createQuery(Object[].class);

        Root rTrip = query.from(Trip.class);
        Root rRoute = query.from(Route.class);
        Root rVehicle = query.from(Vehicle.class);
        Root rUser = query.from(User.class);
        Root rStartStation = query.from(Station.class);
        Root rEndStation = query.from(Station.class);

        query.where(
                b.and(
                        b.equal(rTrip.get("vehicleId"), rVehicle.get("id")),
                        b.like(rTrip.get("driverId"), rUser.get("id")),
//                        b.equal(rTrip.get("routeId"), rRoute.get("id")),
                        b.equal(rTrip.get("routeId").get("startStationId"), rStartStation.get("id")),
                        b.equal(rTrip.get("routeId").get("endStationId"), rEndStation.get("id"))
                )
        );

        System.out.println("PARAM: "+ params);

        if (!params.isEmpty()) {
            List<Predicate> predicates = new ArrayList<>();
            String kwStartStation = params.get("startStation");
            String kwEndStation = params.get("endStation");
            if (kwStartStation != null && !kwStartStation.isEmpty()) {
                predicates.add(b.like(rStartStation.get("name"), String.format("%%%s%%", kwStartStation)));
            }
            if (kwEndStation != null && !kwEndStation.isEmpty()) {
                predicates.add(b.like(rEndStation.get("name"), String.format("%%%s%%", kwEndStation)));
            }
            // sau khi groupby thi xai having 
            query.having(predicates.toArray(new Predicate[predicates.size()]));
        }

        query.multiselect(
                rTrip.get("id"),
                rTrip.get("departureTime"),
                rTrip.get("arrivalTime"),
                rTrip.get("price"),
                rVehicle.get("seatCapacity"),
                rUser.get("name"),
                rStartStation.get("name"),
                rEndStation.get("name")
        );

        query.groupBy(rTrip.get("id"));
        
        Query q = s.createQuery(query);
        List<Object[]> resultList = q.getResultList();
        System.out.println(resultList.size());
        return resultList;
    }

}
