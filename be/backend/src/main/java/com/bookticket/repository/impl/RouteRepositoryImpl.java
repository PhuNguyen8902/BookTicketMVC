/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository.impl;

import com.bookticket.pojo.Route;
import com.bookticket.pojo.Station;
import com.bookticket.repository.RouteRepository;
import java.util.ArrayList;
import java.util.HashMap;
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
public class RouteRepositoryImpl implements RouteRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Object[]> getRoute() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = b.createQuery(Object[].class);
        Root rRoute = query.from(Route.class);
        Root rStation = query.from(Station.class);

        Join<Route, Station> startStationJoin = rRoute.join("startStationId");
        Join<Route, Station> endStationJoin = rRoute.join("endStationId");

        query.multiselect(
                rRoute.get("id"),
                rRoute.get("name"),
                rRoute.get("distance"),
                rRoute.get("duration"),
                startStationJoin.get("name"),
                endStationJoin.get("name")
        );

        query.groupBy(rRoute.get("id"));

        Query q = s.createQuery(query);
//      List<Object[]> list = q.getResultList();

        return q.getResultList();

    }
}
