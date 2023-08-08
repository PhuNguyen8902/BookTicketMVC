/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository.impl;

import com.bookticket.dto.Api.ApiRoute;
import com.bookticket.pojo.Route;
import com.bookticket.pojo.Station;
import com.bookticket.repository.RouteRepository;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vegar
 */
@Repository
@PropertySource("classpath:configs.properties")
public class RouteRepositoryImpl implements RouteRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private Environment env;

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

    @Override
    public List<ApiRoute> getRouteDemo(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = b.createQuery(Object[].class);
        Root rRoute = query.from(Route.class);
        Root rStationStart = query.from(Station.class);
        Root rStationEnd = query.from(Station.class);

        query.where(
                b.and(
                        b.equal(rRoute.get("startStationId"), rStationStart.get("id")),
                        b.equal(rRoute.get("endStationId"), rStationEnd.get("id"))
                )
        );

        query.multiselect(rRoute.get("id"), rRoute.get("name"), rStationStart.get("name"),
                rStationEnd.get("name"), rRoute.get("distance"), rRoute.get("duration"));

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(rRoute.get("name"), String.format("%%%s%%", kw)));
            }
            query.where(predicates.toArray(new Predicate[predicates.size()]));
        }

        query.groupBy(rRoute.get("id"));
        query.orderBy(b.asc(rRoute.get("id")));
        Query q = s.createQuery(query);

        if (params != null) {
            String p = params.get("page");
            if (p != null && !p.isEmpty()) {
                int page = Integer.parseInt(p);
                int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));

                q.setMaxResults(pageSize);
                q.setFirstResult((page - 1) * pageSize);
            }
        }

        List<Object[]> resultList = q.getResultList();

        List<ApiRoute> route = new ArrayList<>();
        for (Object[] result : resultList) {
            ApiRoute r = new ApiRoute();
            r.setId((Integer) result[0]);
            r.setName((String) result[1]);
            r.setStartStation(result[2].toString());
            r.setEndStation(result[3].toString());
            r.setDistance((Double) result[4]);
            r.setDuration((Double) result[5]);

            route.add(r);
        }
        return route;
    }

    @Override
    public long countRoute() {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Route> root = query.from(Route.class);
        query.select(builder.count(root)); // Đếm số lượng sản phẩm

        return session.createQuery(query).getSingleResult();
    }
//
//    @Override
//    public int calculateTotalPages() {
//        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
//        return (int) Math.ceil((double) countRoute() / pageSize);
//    }

}
