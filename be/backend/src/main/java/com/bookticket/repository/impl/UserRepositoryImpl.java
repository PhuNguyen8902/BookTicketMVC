/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository.impl;

import com.bookticket.dto.Request.CustomerRequest;
import com.bookticket.dto.Request.DriverRequest;
import com.bookticket.enums.Role;
import com.bookticket.pojo.User;
import com.bookticket.repository.UserRepository;
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
 * @author ADMIN
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

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

    @Override
    public List<User> getCustomerInfo() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = b.createQuery(Object[].class);
        Root rCustomer = query.from(User.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(b.equal(rCustomer.get("isActive"), 1));
        String roleString = "ROLE_CUSTOMER";

        Role role = Role.valueOf(roleString);
        predicates.add(b.equal(rCustomer.get("role"), role));
        query.where(predicates.toArray(new Predicate[predicates.size()]));

        query = query.select(rCustomer);

        Query q = s.createQuery(query);

        return q.getResultList();
    }

    @Override
    public boolean editUser(User user) {
        try {
            Session s = this.factory.getObject().getCurrentSession();
            s.update(user);
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public boolean deleteUser(User user) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(user);
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public List<CustomerRequest> getCustomers(Map<String, String> params) {
         Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<User> query = b.createQuery(User.class);
        Root root = query.from(User.class);
        
        String employeeRole = "ROLE_CUSTOMER";
        Role role = Role.valueOf(employeeRole);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(root.get("role"), role));
        predicates.add(b.equal(root.get("isActive"), "1"));
        if (params != null) {
            String kw = params.get("kw");     
            if (kw != null && !kw.isEmpty()) {
                    predicates.add(b.like(root.get("email"), String.format("%%%s%%", kw)));
            }
        }
        query.where(predicates.toArray(new Predicate[predicates.size()]));

        query = query.select(root);
        Query q = s.createQuery(query);

        List<User> employeeList = q.getResultList();
        int size = employeeList.size();
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
        
        List<User> users = q.getResultList();
        List<CustomerRequest> employeeRequests = new ArrayList();
        
        for(User user : users){
            CustomerRequest employeeRequest = new CustomerRequest();
            
            employeeRequest.setId(user.getId());
            employeeRequest.setEmail(user.getEmail());
            employeeRequest.setPassword(user.getPassword());
            employeeRequest.setPhone(user.getPhone());
            employeeRequest.setAvatar(user.getAvatar());
            employeeRequest.setName(user.getName());
            
            employeeRequest.setTotalPage(totalPage);
                    
            employeeRequests.add(employeeRequest);        
        }
        
        return employeeRequests;
    }

    @Override
    public List<DriverRequest> getDrivers(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<User> query = b.createQuery(User.class);
        Root root = query.from(User.class);
        
        String employeeRole = "ROLE_DRIVER";
        Role role = Role.valueOf(employeeRole);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(root.get("role"), role));
        predicates.add(b.equal(root.get("isActive"), "1"));
        if (params != null) {
            String kw = params.get("kw");     
            if (kw != null && !kw.isEmpty()) {
                    predicates.add(b.like(root.get("email"), String.format("%%%s%%", kw)));
            }
        }
        query.where(predicates.toArray(new Predicate[predicates.size()]));

        query = query.select(root);
        Query q = s.createQuery(query);

        List<User> employeeList = q.getResultList();
        int size = employeeList.size();
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
        
        List<User> users = q.getResultList();
        List<DriverRequest> employeeRequests = new ArrayList();
        
        for(User user : users){
            DriverRequest employeeRequest = new DriverRequest();
            
            employeeRequest.setId(user.getId());
            employeeRequest.setEmail(user.getEmail());
            employeeRequest.setPassword(user.getPassword());
            employeeRequest.setPhone(user.getPhone());
            employeeRequest.setAvatar(user.getAvatar());
            employeeRequest.setName(user.getName());
            
            employeeRequest.setTotalPage(totalPage);
                    
            employeeRequests.add(employeeRequest);        
        }
        
        return employeeRequests;
    }

}
