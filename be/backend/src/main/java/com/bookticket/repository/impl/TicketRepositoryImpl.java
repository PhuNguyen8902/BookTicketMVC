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
public class TicketRepositoryImpl implements TicketRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Autowired
    private Environment env;

    @Override
    public List<TicketRequest> getTickets(Map<String, String> params) {
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
        predicates.add(b.equal(rTicket.get("userId"), rUser.get("id")));
        predicates.add(b.equal(rTicket.get("employeeId"), rEmployee.get("id")));
        predicates.add(b.equal(rTicket.get("increasedPriceId"), rIncreasedPrice.get("id")));
        
        if(params != null) {
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                    predicates.add(b.equal(rTicket.get("name"), String.format("%%%s%%", kw)));
            }

        }
         
        query.where(predicates.toArray(new Predicate[predicates.size()]));
        
        
        query.multiselect(
                rTicket.get("id"),
                rTicket.get("seat"),
                rTrip.get("departureTime"),
                rTrip.get("arrivalTime"),
                rTicket.get("price"),
                rTicket.get("type"),
                rTicket.get("payment"),
                rTicket.get("date"),
                rTicket.get("name"),
                rUser.get("name"),
                rEmployee.get("name")
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
         for(Object[] ticket: resultList){
             TicketRequest t = new TicketRequest();
             t.setId((Integer) ticket[0]);
             t.setSeat((Integer) ticket[1]);
             
             String formatDepartureTime = ticket[2].toString();
             t.setDepartureTime(formatDepartureTime);
             
             String formatArrivalTime = ticket[3].toString();
             t.setArrivalTime(formatArrivalTime);
             
             String formatPrice = decimalFormat.format(ticket[4]);
             t.setPrice(formatPrice);
             
             
             
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
            Date date = (Date)tuple.get(0);
            Double amount = (Double) tuple.get(1);
            revenueChartResponse.add(new RevenueChartResponse(date, amount));
        }

        return revenueChartResponse;
    }

}
