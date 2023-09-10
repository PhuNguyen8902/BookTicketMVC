/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository.impl;

import com.bookticket.pojo.StationRoute;
import com.bookticket.repository.StationRouteRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class StationRouteRepositoryImpl implements StationRouteRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public boolean addStationRoute(StationRoute stationRoute) {
        try {
            Session s = this.factory.getObject().getCurrentSession();
            s.save(stationRoute);
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public boolean editStationRoute(StationRoute stationRoute) {
        try {
            Session session = this.factory.getObject().getCurrentSession();

        StationRoute mergedStationRoute = (StationRoute) session.merge(stationRoute);

            return true;
        } catch (HibernateException e) {
            System.out.println(e);
            return false;
        }

    }

    @Override
    public List<StationRoute> findStationRouteByTwoId(Integer routeId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<StationRoute> query = builder.createQuery(StationRoute.class);
        Root root = query.from(StationRoute.class);
        query = query.select(root);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get("routeId"), routeId));
        query.where(predicates.toArray(new Predicate[predicates.size()]));

        Query q = s.createQuery(query);
        try {
            return (List<StationRoute>) q.getResultList();
        } catch (HibernateException e) {
            return null;
        }
    }

}
