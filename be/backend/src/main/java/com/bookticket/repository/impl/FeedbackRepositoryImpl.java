/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository.impl;

import com.bookticket.dto.Request.FeedbackRequest;
import com.bookticket.pojo.Feedback;
import com.bookticket.pojo.Trip;
import com.bookticket.pojo.User;
import com.bookticket.repository.FeedbackRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
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
public class FeedbackRepositoryImpl implements FeedbackRepository{
    
    @Autowired
    private Environment env;
    
    @Autowired
    private LocalSessionFactoryBean factory;
            
    @Override
    public List<FeedbackRequest> getFeedBacks(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = b.createQuery(Object[].class);
        Root rFeedbackRequest = query.from(Feedback.class);
        Root rTrip = query.from(Trip.class);
        Root rUser = query.from(User.class);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rFeedbackRequest.get("tripId"), rTrip.get("id")));
        predicates.add(b.equal(rFeedbackRequest.get("userId"), rUser.get("id")));

        
        
        if (params != null) {
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(rTrip.get("routeName"), String.format("%%%s%%", kw)));
                
            }
        }
        
        query.where(predicates.toArray(new Predicate[predicates.size()]));
        
        query = query.multiselect(
                rFeedbackRequest.get("id"),
                rFeedbackRequest.get("content"),
                rTrip.get("id"),
                rTrip.get("routeId").get("name"),
                rTrip.get("departureTime"),
                rTrip.get("arrivalTime"),
                rUser.get("email")
        );
        
        
        query.groupBy(rFeedbackRequest.get("id"));
        query.orderBy(b.desc(rTrip.get("id")));
        
        
        Query q = s.createQuery(query);
        
        
        List<Feedback> feedbackListTest = q.getResultList();
        int size = feedbackListTest.size();
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
        
        List<Object[]> feedbackList = q.getResultList();
        List<FeedbackRequest> list = new ArrayList<>();
        for (Object[] o : feedbackList) {
            FeedbackRequest t = new FeedbackRequest();
            t.setId((Integer) o[0]);
            t.setContent((String) o[1]);
            t.setTripId((Integer) o[2]);
            t.setRouteName((String) o[3]);
            t.setDepartureTime(o[4].toString());
            t.setArrivalTime(o[5].toString());
            t.setUserName((String) o[6]);
            t.setTotalPage(totalPage);
            
            list.add(t);
        }
        
        return list;
        
    }
     @Override
    public boolean addFeedback(Feedback feedback) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(feedback);
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }
    
}
