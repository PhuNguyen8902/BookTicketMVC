/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository.impl;

import com.bookticket.enums.Role;
import com.bookticket.pojo.User;
import com.bookticket.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
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
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public boolean addUser(User user) {
        List<User> u = getUsers(user.getEmail());
        if (u.isEmpty()) {
            try {
                Session s = this.factory.getObject().getCurrentSession();
                s.save(user);
                return true;
            } catch (HibernateException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public List<User> getUsers(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root root = query.from(User.class);
        query = query.select(root);
        if (!username.isEmpty()) {
            Predicate p = builder.equal(root.get("email").as(String.class), username.trim());
            query = query.where(p);
        }
        Query q = s.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<Object[]> getDriverName() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = b.createQuery(Object[].class);
        Root rDriver = query.from(User.class);
        
        List<Predicate> predicates = new ArrayList<>();
        
        predicates.add(b.equal(rDriver.get("isActive"), 1));
        String roleString = "ROLE_DRIVER";

        Role role = Role.valueOf(roleString);
        predicates.add(b.equal(rDriver.get("role"), role));
        query.where(predicates.toArray(new Predicate[predicates.size()]));
        
        query = query.multiselect(
                rDriver.get("id"),
                rDriver.get("email"),
                rDriver.get("name")
        );
        
        Query q = s.createQuery(query);
        return q.getResultList();
        
    }

    @Override
    public User getUserById(String id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<User> query = b.createQuery(User.class);
        Root rUser = query.from(User.class);
        
        query.where(b.equal(rUser.get("id"), id));
        query.select(rUser);
        
        Query q = s.createQuery(query);
        return (User) q.getSingleResult();
    }

}
