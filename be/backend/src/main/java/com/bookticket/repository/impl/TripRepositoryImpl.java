/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository.impl;

import com.bookticket.dto.Response.TripChartResponse;
import com.bookticket.pojo.Route;
import com.bookticket.pojo.Station;
import com.bookticket.pojo.Trip;
import com.bookticket.pojo.User;
import com.bookticket.pojo.Vehicle;
import com.bookticket.repository.TripRepository;
import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
        Root rVehicle = query.from(Vehicle.class);
        Root rUser = query.from(User.class);
        Root rStartStation = query.from(Station.class);
        Root rEndStation = query.from(Station.class);

        query.where(
                b.and(
                        b.equal(rTrip.get("vehicleId"), rVehicle.get("id")),
                        b.equal(rTrip.get("driverId"), rUser.get("id")),
                        b.equal(rTrip.get("routeId").get("startStationId"), rStartStation.get("id")),
                        b.equal(rTrip.get("routeId").get("endStationId"), rEndStation.get("id"))
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
                rEndStation.get("name")
        );

        query.groupBy(rTrip.get("id"));

        Query q = s.createQuery(query);
        List<Object[]> resultList = q.getResultList();
        return resultList;
    }

    @Override
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
}
