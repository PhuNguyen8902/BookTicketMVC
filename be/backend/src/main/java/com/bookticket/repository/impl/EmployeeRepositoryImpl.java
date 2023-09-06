/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository.impl;

import com.bookticket.dto.Request.EmployeeRequest;
import com.bookticket.enums.Role;
import com.bookticket.pojo.User;
import com.bookticket.repository.EmployeeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ADMIN
 */
@Repository
@PropertySource("classpath:configs.properties")
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<EmployeeRequest> getAllEmployee(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<User> query = b.createQuery(User.class);
        Root root = query.from(User.class);
        
        String employeeRole = "ROLE_EMPLOYEE";
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
        List<EmployeeRequest> employeeRequests = new ArrayList();
        
        for(User user : users){
            EmployeeRequest employeeRequest = new EmployeeRequest();
            
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
    public List<User> getEmployeeInfo() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = b.createQuery(Object[].class);
        Root rEmployee = query.from(User.class);
        
        List<Predicate> predicates = new ArrayList<>();
        
        predicates.add(b.equal(rEmployee.get("isActive"), 1));
        String roleString = "ROLE_EMPLOYEE";
        String roleString2 = "ROLE_ADMIN";
        
        Role role = Role.valueOf(roleString);
        Role role2 = Role.valueOf(roleString2);
        
        predicates.add(b.or(
                b.equal(rEmployee.get("role"), role),
                b.equal(rEmployee.get("role"), role2)
        ));
        
        query.where(predicates.toArray(new Predicate[predicates.size()]));
        
        query = query.select(rEmployee);
        
        Query q = s.createQuery(query);
        return q.getResultList();
    }

    @Override
    public boolean addEmployee(User user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean editEmployee(User user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteEmployee(User user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
