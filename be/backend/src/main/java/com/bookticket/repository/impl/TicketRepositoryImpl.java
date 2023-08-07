/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository.impl;

import com.bookticket.pojo.IncreasedPrice;
import com.bookticket.pojo.Seat;
import com.bookticket.pojo.Ticket;
import com.bookticket.pojo.Trip;
import com.bookticket.pojo.User;
import com.bookticket.repository.TicketRepository;

import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
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
//        Root rSeat = query.from(Seat.class);
//        Root rTrip = query.from(Trip.class);
//        Root rUser = query.from(User.class);
//        Root rIncreasedPrice = query.from(IncreasedPrice.class);
        

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

}
