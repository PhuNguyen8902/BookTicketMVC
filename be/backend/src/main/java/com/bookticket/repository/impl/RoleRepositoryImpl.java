/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository.impl;

import com.bookticket.pojo.Roles;
import com.bookticket.pojo.User;
import com.bookticket.repository.RoleRepository;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ADMIN
 */
@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public String getRole(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Roles> query = builder.createQuery(Roles.class);
        Root root = query.from(Roles.class);
        query = query.select(root.get("roleName"));
        Predicate p = builder.equal(root.get("roleId"), id);
        query = query.where(p);
        Query q = s.createQuery(query);
        return (String) q.getSingleResult();
    }

}
