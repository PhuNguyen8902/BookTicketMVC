/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository.impl;

import com.bookticket.dto.Response.RevenueChartResponse;
import com.bookticket.dto.Response.TripChartResponse;
import com.bookticket.pojo.Route;
import com.bookticket.pojo.Ticket;
import com.bookticket.pojo.Trip;
import com.bookticket.repository.TicketRepository;
import java.math.BigDecimal;
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

    @Override
    public List<Object[]> getTickets() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = b.createQuery(Object[].class);
        Root rTicket = query.from(Ticket.class);
        query.multiselect(
                rTicket.get("id"),
                rTicket.get("price"),
                rTicket.get("isActive"),
                rTicket.get("type"),
                rTicket.get("payment"),
                rTicket.get("date")
        );

        query.groupBy(rTicket.get("id"));

        Query q = s.createQuery(query);

        List<Object[]> resultList = q.getResultList();

        return q.getResultList();
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

        query.groupBy(root.get("date"));

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
