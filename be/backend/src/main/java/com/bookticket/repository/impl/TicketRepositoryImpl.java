/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository.impl;

import com.bookticket.dto.Request.TicketRequest;
import com.bookticket.dto.Response.RevenueChartResponse;
import com.bookticket.dto.Response.TripChartResponse;
import com.bookticket.pojo.IncreasedPrice;
import com.bookticket.pojo.Route;
import com.bookticket.pojo.Ticket;
import com.bookticket.pojo.Trip;
import com.bookticket.pojo.User;
import com.bookticket.repository.TicketRepository;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.eclipse.persistence.internal.oxm.schema.model.Restriction;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.criteria.internal.path.SingularAttributePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vegar
 */
@Repository
public class TicketRepositoryImpl implements TicketRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private Environment env;

    @Override
    public List<TicketRequest> getOnlTickets(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = b.createQuery(Object[].class);
        Root rTicket = query.from(Ticket.class);
        Root rTrip = query.from(Trip.class);
        Root rUser = query.from(User.class);
        Root rEmployee = query.from(User.class);
        Root rIncreasedPrice = query.from(IncreasedPrice.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rTicket.get("tripId"), rTrip.get("id")));
        predicates.add(b.equal(rTicket.get("employeeId"), rEmployee.get("id")));
        predicates.add(b.equal(rTicket.get("increasedPriceId"), rIncreasedPrice.get("id")));
        predicates.add(b.equal(rTicket.get("userId"), rUser.get("id")));
        predicates.add(b.equal(rTicket.get("type"), "onl"));
        predicates.add(b.equal(rTicket.get("isActive"), "1"));


        if (params != null) {
            String kw = params.get("kw");
            String isGetKw = params.get("isGetKw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(rUser.get("name"), String.format("%%%s%%", kw)));
            }
            if (isGetKw != null && !isGetKw.isEmpty()) {
                predicates.add(b.equal(rTicket.get("isGet"), Short.valueOf(isGetKw)));
            }

        }

        query.where(predicates.toArray(new Predicate[predicates.size()]));

       
        query.multiselect(
                    rTicket.get("id"),
                    rTicket.get("seat"),
                    rTrip.get("routeId").get("name"),
                    rTrip.get("departureTime"),
                    rTrip.get("arrivalTime"),
                    rTicket.get("price"),
                    rTicket.get("type"),
                    rTicket.get("payment"),
                    rTicket.get("date"),
                    rEmployee.get("name"),
                    rIncreasedPrice.get("eventName"),
                    rUser.get("name"),
                    rTicket.get("isGet")
            );
      

        query.groupBy(rTicket.get("id"));
        query.orderBy(b.asc(rTicket.get("id")));

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
        List<TicketRequest> tickets = new ArrayList<>();
        for (Object[] ticket : resultList) {
            TicketRequest t = new TicketRequest();
            t.setId((Integer) ticket[0]);
            t.setSeat(Integer.valueOf(ticket[1].toString()));
            t.setRoute((String) ticket[2]);
            String formatDepartureTime = ticket[3].toString();
            t.setDepartureTime(formatDepartureTime);
            String formatArrivalTime = ticket[4].toString();
            t.setArrivalTime(formatArrivalTime);
            String formatPrice = decimalFormat.format(ticket[5]);
            t.setPrice(formatPrice);
            t.setType((String) ticket[6]);
            t.setPayment((String) ticket[7]);
            t.setDate((String) ticket[8].toString());
            t.setEmployee((String) ticket[9]);
            t.setIncreasePrice((String) ticket[10]);
            t.setUserName((String) ticket[11]);
            t.setIsGet((Short) ticket[12]);

            t.setTotalPage(totalPage);

            tickets.add(t);
        }

        return tickets;
    }

    @Override
    public List<TicketRequest> getOffTickets(Map<String, String> params){
         Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = b.createQuery(Object[].class);
        Root rTicket = query.from(Ticket.class);
        Root rTrip = query.from(Trip.class);
        Root rUser = query.from(User.class);
        Root rEmployee = query.from(User.class);
        Root rIncreasedPrice = query.from(IncreasedPrice.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rTicket.get("tripId"), rTrip.get("id")));
        predicates.add(b.equal(rTicket.get("employeeId"), rEmployee.get("id")));
        predicates.add(b.equal(rTicket.get("increasedPriceId"), rIncreasedPrice.get("id")));
        predicates.add(b.equal(rTicket.get("type"), "off"));
        predicates.add(b.equal(rTicket.get("isActive"), "1"));


        if (params != null) {
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(rTicket.get("name"), String.format("%%%s%%", kw)));
            }

        }

        query.where(predicates.toArray(new Predicate[predicates.size()]));

       
        query.multiselect(
                    rTicket.get("id"),
                    rTicket.get("seat"),
                    rTrip.get("routeId").get("name"),
                    rTrip.get("departureTime"),
                    rTrip.get("arrivalTime"),
                    rTicket.get("price"),
                    rTicket.get("type"),
                    rTicket.get("payment"),
                    rTicket.get("date"),
                    rEmployee.get("name"),
                    rIncreasedPrice.get("eventName"),
                    rTicket.get("name"),
                    rTicket.get("isGet")
            );
      

        query.groupBy(rTicket.get("id"));
        query.orderBy(b.asc(rTicket.get("id")));

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
        List<TicketRequest> tickets = new ArrayList<>();
        for (Object[] ticket : resultList) {
            TicketRequest t = new TicketRequest();
            t.setId((Integer) ticket[0]);
            t.setSeat(Integer.valueOf(ticket[1].toString()));
            t.setRoute((String) ticket[2]);
            String formatDepartureTime = ticket[3].toString();
            t.setDepartureTime(formatDepartureTime);
            String formatArrivalTime = ticket[4].toString();
            t.setArrivalTime(formatArrivalTime);
            String formatPrice = decimalFormat.format(ticket[5]);
            t.setPrice(formatPrice);
            t.setType((String) ticket[6]);
            t.setPayment((String) ticket[7]);
            t.setDate((String) ticket[8].toString());
            t.setEmployee((String) ticket[9]);
            t.setIncreasePrice((String) ticket[10]);
            t.setUserName((String) ticket[11]);
            t.setIsGet((Short) ticket[12]);
            
            t.setTotalPage(totalPage);

            tickets.add(t);
        }

        return tickets;
    }
    @Override
    public List<RevenueChartResponse> getListRevenueInTicket(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = criteriaBuilder.createTupleQuery();
        Root<Ticket> root = query.from(Ticket.class);

        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {
            String year = params.get("year");
            if (year != null && !year.isEmpty()) {
                int targetYear = Integer.parseInt(year);

                Expression<Integer> yearExpression = criteriaBuilder.function("year", Integer.class, root.get("date"));
                Predicate yearPredicate = criteriaBuilder.equal(yearExpression, targetYear);

                predicates.add(yearPredicate);
            }
//            String month = params.get("month");
//            if (month != null && !month.isEmpty()) {
//                int targetMonth = Integer.parseInt(month);
//
//                Expression<Integer> monthExpression = criteriaBuilder.function("month", Integer.class, root.get("date"));
//                Predicate monthPredicate = criteriaBuilder.equal(monthExpression, targetMonth);
//
//                predicates.add(monthPredicate);
//            }
//            String quarter = params.get("quarter");
//            if (quarter != null && !quarter.isEmpty()) {
//                int targetQuarter = Integer.parseInt(quarter);
//
//                Expression<Integer> quarterExpression = criteriaBuilder.function("quarter", Integer.class, root.get("date"));
//                Predicate quarterPredicate = criteriaBuilder.equal(quarterExpression, targetQuarter);
//
//                predicates.add(quarterPredicate);
//            }
        }
        query.where(predicates.toArray(new Predicate[predicates.size()]));
//        Expression<BigDecimal> priceExpression = root.get("price");
//                        System.out.println(root.get("date"));

        query.multiselect(root.get("date"), root.get("price"));

//        query.groupBy(root.get("date"));
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
    public boolean addOffTicket(Ticket ticket) {
        Session s = this.factory.getObject().getCurrentSession();
        try{
            s.save(ticket);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    @Override
    public boolean editOnlTicket(Ticket ticket) {
        Session s = this.factory.getObject().getCurrentSession();
        try{
            s.update(ticket);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean editOffTicket(Ticket ticket) {
        Session s = this.factory.getObject().getCurrentSession();
        try{
            s.update(ticket);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public boolean deleteTicket(Ticket ticket){
        Session s = this.factory.getObject().getCurrentSession();
        try{
            s.update(ticket);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    @Override
    public Ticket getTicketById(Integer id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Ticket> query = b.createQuery(Ticket.class);
        Root rTicket = query.from(Ticket.class);
        
        query.where(b.equal(rTicket.get("id"), id));
        
        query.select(rTicket);
        Query q = s.createQuery(query);
        
        return (Ticket) q.getSingleResult();
    }

    @Override
    public List<Short> getAllSeatTicketByTripId(Integer id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Ticket> query = b.createQuery(Ticket.class);
        Root rTicket = query.from(Ticket.class);
        
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rTicket.get("tripId"), id));
        predicates.add(b.equal(rTicket.get("isActive"), "1"));
        query.where(predicates.toArray(new Predicate[predicates.size()]));
        
        
        query.select(rTicket.get("seat"));
        Query q = s.createQuery(query);
        
        return q.getResultList();
    }

    @Override
    public List<Ticket> getTicketsByIncreasedPriceId(Integer id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Ticket> query = b.createQuery(Ticket.class);
        Root rTicket = query.from(Ticket.class);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rTicket.get("increasedPriceId"), id));
        predicates.add(b.equal(rTicket.get("isActive"), "1"));
        query.where(predicates.toArray(new Predicate[predicates.size()]));
        
        query.select(rTicket);
        
        Query q = s.createQuery(query);
        
        return q.getResultList();
    }

    

}
