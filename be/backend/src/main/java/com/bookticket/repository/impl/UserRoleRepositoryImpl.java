/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository.impl;

import com.bookticket.pojo.RefeshToken;
import com.bookticket.pojo.UserRoles;
import com.bookticket.repository.UserRoleRepository;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ADMIN
 */
@Repository

public class UserRoleRepositoryImpl implements UserRoleRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public boolean setCustomer(UserRoles userRoles) {
        try {
            Session s = this.factory.getObject().getCurrentSession();
            s.save(userRoles);
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public UserRoles findRoleOfUser(String userId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<UserRoles> query = builder.createQuery(UserRoles.class);
        Root root = query.from(UserRoles.class);
        query = query.select(root);
        Predicate p = builder.equal(root.get("userId").as(String.class), userId.trim());
        query = query.where(p);
        Query q = s.createQuery(query);
        return (UserRoles) q.getSingleResult();

    }

}
