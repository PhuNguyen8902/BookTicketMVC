/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository.impl;


import com.bookticket.dto.Request.TripRequest;

import com.bookticket.dto.Response.TripChartResponse;
import com.bookticket.pojo.Route;

import com.bookticket.pojo.Station;
import com.bookticket.pojo.Trip;
import com.bookticket.pojo.User;
import com.bookticket.pojo.Vehicle;
import com.bookticket.repository.TripRepository;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
    @Autowired
    private Environment env;

    @Override
    public List<Object[]> getTrips(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = b.createQuery(Object[].class);

        Root rTrip = query.from(Trip.class);
        Root rVehicle = query.from(Vehicle.class);
        Root rUser = query.from(User.class);
        Root rRoute = query.from(Route.class);
        Root rStartStation = query.from(Station.class);
        Root rEndStation = query.from(Station.class);

        query.where(
                b.and(
                        b.equal(rTrip.get("vehicleId"), rVehicle.get("id")),
                        b.equal(rTrip.get("driverId"), rUser.get("id")),
                        b.equal(rTrip.get("routeId"), rRoute.get("id")),
                        b.equal(rTrip.get("routeId").get("startStationId"), rStartStation.get("id")),
                        b.equal(rTrip.get("routeId").get("endStationId"), rEndStation.get("id")),
                        b.equal(rTrip.get("isActive"), "1")
                )
        );

        System.out.println("PARAM: " + params);

        if (!params.isEmpty()) {
            List<Predicate> predicates = new ArrayList<>();
            String kwStartStation = params.get("startStation");
            String kwEndStation = params.get("endStation");
            String kwStartDate = params.get("startDate");
            if (kwStartStation != null && !kwStartStation.isEmpty()) {
                predicates.add(b.like(rStartStation.get("name"), String.format("%%%s%%", kwStartStation)));
            }
            if (kwEndStation != null && !kwEndStation.isEmpty()) {
                predicates.add(b.like(rEndStation.get("name"), String.format("%%%s%%", kwEndStation)));
            }
            if (kwStartDate != null && !kwStartDate.isEmpty()) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Use the appropriate date format
                try {
                    Date startDate = dateFormat.parse(kwStartDate);
                    Calendar cal = new GregorianCalendar();
                    cal.setTime(startDate); // Set the calendar's time to the parsed start date
                    cal.add(Calendar.DAY_OF_MONTH, 1); // Add one day to the calendar
                    Date nextDate = cal.getTime();
                    predicates.add(b.greaterThanOrEqualTo(rTrip.get("departureTime"), startDate));
                    predicates.add(b.lessThan(rTrip.get("departureTime"), nextDate));

                } catch (ParseException e) {
                    // Handle the parsing exception if necessary
                }

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
                rEndStation.get("name"),
                rRoute.get("id"),
                rVehicle.get("id")

        );

        query.groupBy(rTrip.get("id"));

        Query q = s.createQuery(query);
        List<Object[]> resultList = q.getResultList();
        return resultList;
    }

    @Override

    public List<TripRequest> getAdminTrips(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = b.createQuery(Object[].class);
        Root rTrip = query.from(Trip.class);
        Root rVehicle = query.from(Vehicle.class);
        Root rRoute = query.from(Route.class);
        Root rUser = query.from(User.class);
        Root rStartStation = query.from(Station.class);
        Root rEndStation = query.from(Station.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rTrip.get("driverId"), rUser.get("id")));
        predicates.add(b.equal(rTrip.get("vehicleId"), rVehicle.get("id")));
        predicates.add(b.equal(rTrip.get("routeId"), rRoute.get("id")));
        predicates.add(b.equal(rTrip.get("routeId").get("startStationId"), rStartStation.get("id")));
        predicates.add(b.equal(rTrip.get("routeId").get("endStationId"), rEndStation.get("id")));
        predicates.add(b.equal(rTrip.get("isActive"), "1"));
        
        if (params != null) {
            String endStationKw = params.get("endStationKw");
            String kw = params.get("kw");
            if (endStationKw != null && !endStationKw.isEmpty()) {
                predicates.add(b.like(rEndStation.get("name"), String.format("%%%s%%", endStationKw)));
            }
            if (kw != null && !kw.isEmpty()) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Use the appropriate date format
                try {
                    Date startDate = dateFormat.parse(kw);
                    Calendar cal = new GregorianCalendar();
                    cal.setTime(startDate); // Set the calendar's time to the parsed start date
                    cal.add(Calendar.DAY_OF_MONTH, 1); // Add one day to the calendar
                    Date nextDate = cal.getTime();
                    predicates.add(b.greaterThanOrEqualTo(rTrip.get("departureTime"), startDate));
                    predicates.add(b.lessThan(rTrip.get("departureTime"), nextDate));

                } catch (ParseException e) {
                    // Handle the parsing exception if necessary
                }
            }

        }
        query.where(predicates.toArray(new Predicate[predicates.size()]));

        query.multiselect(
                rTrip.get("id"),
                rTrip.get("departureTime"),
                rTrip.get("arrivalTime"),
                rTrip.get("price"),
                rVehicle.get("seatCapacity"),
                rUser.get("name"),
                rRoute.get("name"),
                rStartStation.get("name"),
                rEndStation.get("name")
        );

        query.groupBy(rTrip.get("id"));
        query.orderBy(b.asc(rTrip.get("id")));

        Query q = s.createQuery(query);
        List<Object[]> demoRsList = q.getResultList();
        int size = demoRsList.size();
        int ps = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        int totalPage = (int) Math.ceil((double) size / ps);

        if (params != null) {
            String p = params.get("page");
            if (p != null && !p.isEmpty()) {
                int page = Integer.parseInt(p);
                int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));

                q.setMaxResults(pageSize);
                q.setFirstResult((page - 1) * pageSize);
            }
        }

        DecimalFormat decimalFormat = new DecimalFormat("#.####"); // Format to four decimal places

        List<Object[]> resultList = q.getResultList();
        List<TripRequest> trips = new ArrayList<>();
        for (Object[] trip : resultList) {
            TripRequest t = new TripRequest();
            t.setId((Integer) trip[0]);
            
            String departureTime = trip[1].toString();
            t.setDepartureTime(departureTime);
            
            String arrivalTime = trip[2].toString();
            t.setArrivalTime(arrivalTime);

            String formattedValue = decimalFormat.format(trip[3]);
            t.setPrice(formattedValue);
            Short seat = Short.parseShort(trip[4].toString());
            t.setSeatCapacity(seat.toString());
            t.setDriverName((String) trip[5]);
            t.setRouteName((String) trip[6]);
            t.setStartStation((String) trip[7]);
            t.setEndStation((String) trip[8]);
            t.setTotalPage(totalPage);
            trips.add(t);
        }

        return trips;
    }

    @Override
    public boolean addTrip(Trip trip) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(trip);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean editTrip(Trip trip) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(trip);
            return true;
        } catch (Exception e) {
            System.out.println("ERR: " + e);
            return false;
        } 
    }

    @Override
    public boolean deleteTrip(Trip trip) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(trip);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } 
    }

    @Override
    public Trip getTripById(Integer id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Trip> query = b.createQuery(Trip.class);
        Root rTrip = query.from(Trip.class);
        
        query.where(b.equal(rTrip.get("id"), id));
        
        query.select(rTrip);
        Query q = s.createQuery(query);
        
        return (Trip) q.getSingleResult();
    }


    public List<TripChartResponse> getListRouteCountsInTrip(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = criteriaBuilder.createTupleQuery();
        Root<Trip> root = query.from(Trip.class);

        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {
            String year = params.get("year");
            if (year != null && !year.isEmpty()) {
                int targetYear = Integer.parseInt(year);

                Expression<Integer> yearExpression = criteriaBuilder.function("year", Integer.class, root.get("departureTime"));
                Predicate yearPredicate = criteriaBuilder.equal(yearExpression, targetYear);

                predicates.add(yearPredicate);
            }
              String month = params.get("month");
            if (month != null && !month.isEmpty()) {
                int targetMonth = Integer.parseInt(month);

                Expression<Integer> monthExpression = criteriaBuilder.function("month", Integer.class, root.get("departureTime"));
                Predicate monthPredicate = criteriaBuilder.equal(monthExpression, targetMonth);

                predicates.add(monthPredicate);
            }
              String quarter = params.get("quarter");
            if (quarter != null && !quarter.isEmpty()) {
                int targetQuarter = Integer.parseInt(quarter);

                Expression<Integer> quarterExpression = criteriaBuilder.function("quarter", Integer.class, root.get("departureTime"));
                Predicate quarterPredicate = criteriaBuilder.equal(quarterExpression, targetQuarter);

                predicates.add(quarterPredicate);
            }
        }
        query.where(predicates.toArray(new Predicate[predicates.size()]));

        query.multiselect(root.get("routeId"), criteriaBuilder.count(root.get("routeId")));
        query.groupBy(root.get("routeId"));

        Query q = session.createQuery(query);

        List<Tuple> resultTuples = q.getResultList();

        List<TripChartResponse> tripChartResponses = new ArrayList<>();
        for (Tuple tuple : resultTuples) {
            Route routeId = (Route) tuple.get(0);
            Long amount = (Long) tuple.get(1);
            tripChartResponses.add(new TripChartResponse(routeId, amount.intValue()));
        }

        return tripChartResponses;
    }

    @Override
    public List<Object[]> getTripInfo() {
        
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = b.createQuery(Object[].class);
        Root rTrip = query.from(Trip.class);
        Root rRoute = query.from(Route.class);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rTrip.get("isActive"), "1"));
        predicates.add(b.equal(rTrip.get("routeId"), rRoute.get("id")));
        query.where(predicates.toArray(new Predicate[predicates.size()]));
        
        query.multiselect(
                rTrip.get("id"),
                rTrip.get("departureTime"),
                rTrip.get("arrivalTime"),
                rRoute.get("name")
             
        );
        
        query.groupBy(rTrip.get("id"));
        Query q = s.createQuery(query);
        
        return q.getResultList();
    }

}
