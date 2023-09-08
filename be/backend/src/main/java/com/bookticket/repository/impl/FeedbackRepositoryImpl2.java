/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository.impl;

import com.bookticket.dto.Request.FeedbackRequest;
import com.bookticket.pojo.Feedback;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import com.bookticket.repository.FeedbackRepository2;

/**
 *
 * @author ADMIN
 */
@Repository
public class FeedbackRepositoryImpl2 implements FeedbackRepository2 {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public boolean addFeedback(Feedback feedback) {
        Session s = this.factory.getObject().getCurrentSession();
         System.out.println("-------------------------------------------------------feedback");
                System.out.println(feedback.getContent());
                                System.out.println("trip ne"+feedback.getTripId().getId());
                                                                System.out.println(feedback.getUserId().getId());
        try {
            s.save(feedback);
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

}
