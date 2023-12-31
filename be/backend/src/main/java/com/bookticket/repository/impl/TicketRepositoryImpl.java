/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository.impl;

import com.bookticket.dto.Api.ApiTicketResponse;
import com.bookticket.dto.Response.RevenueChartResponse;
import com.bookticket.enums.Payment;
import com.bookticket.pojo.IncreasedPrice;
import com.bookticket.pojo.OrderOnline;
import com.bookticket.pojo.Route;
import com.bookticket.pojo.Station;
import com.bookticket.pojo.Ticket2;
import com.bookticket.pojo.Trip;
import com.bookticket.pojo.User;
import com.bookticket.pojo.Vehicle;
import com.bookticket.repository.TicketRepository;
import com.bookticket.repository.UserRepository;
import java.io.Serializable;
import java.text.DateFormat;
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
public class TicketRepositoryImpl implements TicketRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private Environment env;

    @Override
    public List<RevenueChartResponse> getListRevenueInTicket(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = criteriaBuilder.createTupleQuery();
        Root<Ticket2> root = query.from(Ticket2.class);
        Root<OrderOnline> rOrder = query.from(OrderOnline.class);

        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {
            String year = params.get("year");
            if (year != null && !year.isEmpty()) {
                int targetYear = Integer.parseInt(year);

                Expression<Integer> yearExpression = criteriaBuilder.function("year", Integer.class, rOrder.get("orderDate"));
                Predicate yearPredicate = criteriaBuilder.equal(yearExpression, targetYear);

                predicates.add(yearPredicate);
            }

        }
                predicates.add(criteriaBuilder.equal(rOrder.get("ticketId"), root.get("id")));

        query.where(predicates.toArray(new Predicate[predicates.size()]));

        query.multiselect(rOrder.get("orderDate"), root.get("price"));

        Query q = session.createQuery(query);

        List<Tuple> resultTuples = q.getResultList();

        List<RevenueChartResponse> revenueChartResponse = new ArrayList<>();
        for (Tuple tuple : resultTuples) {
            Date date = (Date) tuple.get(0);
            Double amount = (Double) tuple.get(1);
            revenueChartResponse.add(new RevenueChartResponse(date, amount));
        }

        return revenueChartResponse;
    }

    @Override
    public List<Short> getAllSeatTicketByTripId(Integer id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Ticket2> query = b.createQuery(Ticket2.class);
        Root rTicket = query.from(Ticket2.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rTicket.get("tripId"), id));
        query.where(predicates.toArray(new Predicate[predicates.size()]));

        query.select(rTicket.get("seat"));
        Query q = s.createQuery(query);

        return q.getResultList();
    }

    @Override
    public List<ApiTicketResponse> getListTickets(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = b.createQuery(Object[].class);
        Root rTicket = query.from(Ticket2.class);
        Root rOrder = query.from(OrderOnline.class);
        Root rUser = query.from(User.class);
        Root rTrip = query.from(Trip.class);
        Root rRoute = query.from(Route.class);
        Root rVehicle = query.from(Vehicle.class);
        Root rStationStart = query.from(Station.class);
        Root rStationEnd = query.from(Station.class);
        Root rIncrease = query.from(IncreasedPrice.class);
        Root rEmp = query.from(User.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rTicket.get("tripId").get("id"), rTrip.get("id")));
        predicates.add(b.equal(rTrip.get("routeId").get("id"), rRoute.get("id")));
        predicates.add(b.equal(rTrip.get("vehicleId").get("id"), rVehicle.get("id")));
        predicates.add(b.equal(rRoute.get("startStationId").get("id"), rStationStart.get("id")));
        predicates.add(b.equal(rRoute.get("endStationId").get("id"), rStationEnd.get("id")));
        predicates.add(b.equal(rOrder.get("userId").get("id"), rUser.get("id")));
        predicates.add(b.equal(rOrder.get("empId").get("id"), rEmp.get("id")));

        predicates.add(b.equal(rOrder.get("ticketId").get("id"), rTicket.get("id")));
        predicates.add(b.equal(rOrder.get("increasedPriceId").get("id"), rIncrease.get("id")));

        if (params != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Use the appropriate date format
            String user = params.get("user");
            if (user != null && !user.isEmpty()) {
                predicates.add(b.equal(rOrder.get("userId").get("id"), user));
            }
            String type = params.get("type");
            if (type != null && !type.isEmpty()) {
                if ("1".equals(type)) {
                    predicates.add(b.equal(rTicket.get("ticketType"), "1"));
                } else if ("0".equals(type)) {
                    predicates.add(b.equal(rTicket.get("ticketType"), "0"));
                }
            }
            String payment = params.get("payment");
            if (payment != null && !payment.isEmpty()) {
                Payment pay = Payment.valueOf(payment);
                predicates.add(b.equal(rOrder.get("payment"), pay));

            }
            String get = params.get("get");
            if (get != null && !get.isEmpty()) {
                if ("1".equals(get)) {
                    predicates.add(b.equal(rTicket.get("isGet"), "1"));
                } else if ("0".equals(get)) {
                    predicates.add(b.equal(rTicket.get("isGet"), "0"));
                }
            }
            String startDate = params.get("startDate");
            if (startDate != null && !startDate.isEmpty()) {
                try {
                    Date start = dateFormat.parse(startDate);
                    Calendar cal = new GregorianCalendar();
                    cal.setTime(start); // Set the calendar's time to the parsed start date
                    cal.add(Calendar.DAY_OF_MONTH, 1); // Add one day to the calendar
                    Date nextDate = cal.getTime();
                    predicates.add(b.greaterThanOrEqualTo(rTrip.get("departureTime"), start));
                    predicates.add(b.lessThan(rTrip.get("departureTime"), nextDate));

                } catch (ParseException e) {
                }
            }
            String bookDate = params.get("bookDate");
            if (bookDate != null && !bookDate.isEmpty()) {
                try {
                    Date start = dateFormat.parse(bookDate);
                    Calendar cal = new GregorianCalendar();
                    cal.setTime(start); // Set the calendar's time to the parsed start date
                    cal.add(Calendar.DAY_OF_MONTH, 1); // Add one day to the calendar
                    Date nextDate = cal.getTime();
                    predicates.add(b.greaterThanOrEqualTo(rTicket.get("date"), start));
                    predicates.add(b.lessThan(rTicket.get("date"), nextDate));

                } catch (ParseException e) {
                }

            }
            String startStation = params.get("startStation");
            if (startStation != null && !startStation.isEmpty()) {
                predicates.add(b.equal(rStationStart.get("name"), startStation));
            }
            String endStation = params.get("endStation");
            if (endStation != null && !endStation.isEmpty()) {
                predicates.add(b.equal(rStationEnd.get("name"), endStation));
            }
            String ticketId = params.get("ticketId");
            if (ticketId != null && !ticketId.isEmpty()) {
                predicates.add(b.equal(rTicket.get("id"), ticketId));
            }
        }
        query.where(predicates.toArray(new Predicate[predicates.size()]));

        query.multiselect(rTicket.get("seat"), rTicket.get("price"), rTrip.get("arrivalTime"),
                rTicket.get("cusName"), rIncrease.get("increasedPercentage"), rIncrease.get("eventName"),
                rTrip.get("departureTime"), rOrder.get("orderDate"), rTrip.get("id"), rRoute.get("id"),
                rVehicle.get("seatCapacity"), rVehicle.get("licensePlate"), rOrder.get("payment"), rTicket.get("ticketType"),
                rStationStart.get("name"), rStationEnd.get("name"), rTicket.get("id"), rTicket.get("isGet"),
                rOrder.get("id"), rEmp.get("name"));

        Query q = s.createQuery(query);

        List<Object[]> demoList = q.getResultList();

        int size = demoList.size();
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
        List<Object[]> resultList = q.getResultList();
        List<ApiTicketResponse> tic = new ArrayList<>();
        for (Object[] result : resultList) {
            ApiTicketResponse r = new ApiTicketResponse();
            r.setSeat((Short) result[0]);
            r.setPrice((Double) result[1]);
            r.setUserName(result[3].toString());
            r.setIncreasedPercentage((Short) result[4]);
            r.setEventName(result[5].toString());
            Date departureTime = (Date) result[6];
            long longDepartureTime = departureTime.getTime();
            r.setDepartureTime(longDepartureTime);
            Date nowTime = (Date) result[7];
            long longNowTime = nowTime.getTime();
            r.setBookTime(longNowTime);
            r.setTripId((Integer) result[8]);
            r.setRouteId((Integer) result[9]);
            r.setSeatCapacity((Short) result[10]);
            r.setLicensePlate(result[11].toString());
            r.setPayment(result[12].toString());
            r.setType((Short) result[13]);
            r.setStartStation(result[14].toString());
            r.setEndStation(result[15].toString());
            r.setTicketId((Integer) result[16]);
            Date arrivalTime = (Date) result[2];
            long longArrivalTime = arrivalTime.getTime();
            r.setArrivalTime(longArrivalTime);
            r.setIsGet((Short) result[17]);
            r.setOrderId((Integer) result[18]);
            r.setEmpName(result[19].toString());
            r.setTotalPage(totalPage);
            tic.add(r);
        }

        return tic;
    }

    @Override
    public int addTicket(Ticket2 ticket) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            Serializable id = s.save(ticket);
            return (int) id;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public boolean addOrder(OrderOnline o) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(o);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Ticket2 getTicket2ById(Integer id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Ticket2> query = b.createQuery(Ticket2.class);
        Root rTicket = query.from(Ticket2.class);

        query.where(b.equal(rTicket.get("id"), id));

        query.select(rTicket);
        Query q = s.createQuery(query);

        return (Ticket2) q.getSingleResult();
    }

    @Override
    public OrderOnline getOrderById(Integer id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<OrderOnline> query = b.createQuery(OrderOnline.class);
        Root rTicket = query.from(OrderOnline.class);

        query.where(b.equal(rTicket.get("id"), id));

        query.select(rTicket);
        Query q = s.createQuery(query);

        return (OrderOnline) q.getSingleResult();
    }

    @Override
    public boolean updateTicket(Ticket2 ticket) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(ticket);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateOrder(OrderOnline o) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(o);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public OrderOnline getOrderByTicket2Id(Integer intgr) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<OrderOnline> query = b.createQuery(OrderOnline.class);
        Root rOrder = query.from(OrderOnline.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rOrder.get("ticketId").get("id"), intgr));

        query.where(predicates.toArray(new Predicate[predicates.size()]));

        query.select(rOrder);
        Query q = s.createQuery(query);

        return (OrderOnline) q.getSingleResult();
    }

}
