/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository.impl;

import com.bookticket.pojo.Station;
import com.bookticket.repository.StationRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vegar
 */
@Repository
public class StationRepositoryImpl implements StationRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<Station> getStation(){
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Station> query = builder.createQuery(Station.class);
        Root root = query.from(Station.class);
        query = query.select(root);
        
        Query q = s.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<Object[]> getNameStation() {
         Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root root = query.from(Station.class);
        query = query.multiselect(root.get("id"),root.get("name"));
        
        Query q = s.createQuery(query);
        return q.getResultList();
    }
    
    @Override
    public Station getStaionById(Integer id){
     Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Station> query = builder.createQuery(Station.class);
        Root root = query.from(Station.class);
        query = query.select(root);
        query.where(builder.equal(root.get("id"),id));
        
        Query q = s.createQuery(query);
        try{
        return (Station) q.getSingleResult();
        }catch(HibernateException e){
            return null;
        }
    }
}
