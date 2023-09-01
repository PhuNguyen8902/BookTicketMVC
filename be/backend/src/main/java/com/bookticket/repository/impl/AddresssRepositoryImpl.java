/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository.impl;

import com.bookticket.dto.Request.AddressRequest;
import com.bookticket.pojo.Address;
import com.bookticket.pojo.Vehicle;
import com.bookticket.repository.AddressRepository;
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
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vegar
 */
@Repository
public class AddresssRepositoryImpl implements AddressRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Autowired
    private Environment env;
    
    @Override
    public List<AddressRequest> getAdminAddress(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Address> query = b.createQuery(Address.class);
        Root rAddress = query.from(Address.class);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rAddress.get("isActive"), 1));
        if (params != null) {
            String kw = params.get("kw");     
            if (kw != null && !kw.isEmpty()) {
                    predicates.add(b.equal(rAddress.get("town"), String.format("%%%s%%", kw)));
            }
        }
        
        query.where(predicates.toArray(new Predicate[predicates.size()]));
        query.select(rAddress);
         
        Query q = s.createQuery(query);
        
        List<Address> addressListTest = q.getResultList();
        int size = addressListTest.size();
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
        
        List<Address> vehicleList = q.getResultList();
        List<AddressRequest> list = new ArrayList<>();
        for (Address o : vehicleList) {
            AddressRequest a = new AddressRequest();
            a.setId(o.getId());
            a.setTown(o.getTown());
            a.setDistrict(o.getDistrict());
            a.setWard(o.getWard());
            
            a.setTotalPage(totalPage);
            list.add(a);
        }
        
        return list;
    }

    @Override
    public List<Address> getAdminAdressInfo() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Address> query = b.createQuery(Address.class);
        Root rAddress = query.from(Address.class);
        
        query.select(rAddress);
        Query q = s.createQuery(query);
        
        return q.getResultList();
    }

    @Override
    public boolean addAddress(Address a) {
        Session s = this.factory.getObject().getCurrentSession();
        try{
            s.save(a);
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean editAddress(Address a) {
        Session s = this.factory.getObject().getCurrentSession();
        try{
            s.update(a);
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteAddress(Address a) {
        Session s = this.factory.getObject().getCurrentSession();
        try{
            s.update(a);
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Address getAddressById(Integer id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Address> query = b.createQuery(Address.class);
        Root rAddress = query.from(Address.class);
        
        query.where(b.equal(rAddress.get("id"), id));
        
        query.select(rAddress);
        Query q = s.createQuery(query);
        
        return (Address) q.getSingleResult();
        
    }
    
}
