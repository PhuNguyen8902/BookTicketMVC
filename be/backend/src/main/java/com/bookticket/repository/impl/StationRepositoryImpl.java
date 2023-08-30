/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository.impl;

import com.bookticket.dto.Request.StationRequest;
import com.bookticket.pojo.Address;
import com.bookticket.pojo.Station;
import com.bookticket.repository.StationRepository;
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
public class StationRepositoryImpl implements StationRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Autowired
    private Environment env;

    @Override
    public List<Station> getStation() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Station> query = builder.createQuery(Station.class);
        Root root = query.from(Station.class);
        query = query.select(root);

        Query q = s.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<StationRequest> getAdminStation(Map<String, String> params){
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = b.createQuery(Object[].class);
        Root rStation = query.from(Station.class);
        Root rAddress = query.from(Address.class);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rStation.get("addressId"), rAddress.get("id")));
        predicates.add(b.equal(rStation.get("isActive"), "1"));
        
        
        query.multiselect(
                rStation.get("id"),
                rStation.get("name"),
                rAddress.get("town"),
                rAddress.get("district"),
                rAddress.get("ward")
        );
        if (params != null) {
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(rStation.get("name"), String.format("%%%s%%", kw)));
            }
        }
        
        query.where(predicates.toArray(new Predicate[predicates.size()]));
        
        query.groupBy(rStation.get("id"));
        
        Query q = s.createQuery(query);
        List<Station> stationList = q.getResultList();
        int size = stationList.size();
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
        
        List<Object[]> stations = q.getResultList();
        List<StationRequest> list = new ArrayList<>();
        for(Object[] o : stations){
            StationRequest sr = new StationRequest();
            sr.setId((Integer) o[0]);
            sr.setName((String) o[1]);
            sr.setTown((String) o[2]);
            sr.setDistrict((String) o[3]);
            sr.setWard((String) o[4]);
            
            sr.setTotalPage(totalPage);
            
            list.add(sr);
        }
        
        return list;
    }
    
    @Override
    public List<Object[]> getNameStation() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root root = query.from(Station.class);
        query = query.multiselect(root.get("id"), root.get("name"));

        Query q = s.createQuery(query);
        return q.getResultList();
    }

    @Override
    public Station getStaionById(Integer id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Station> query = builder.createQuery(Station.class);
        Root root = query.from(Station.class);
        query = query.select(root);
        query.where(builder.equal(root.get("id"), id));

        Query q = s.createQuery(query);
        try {
            return (Station) q.getSingleResult();
        } catch (HibernateException e) {
            return null;
        }
    }

    @Override
    public Station getStationByName(String name) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Station> query = builder.createQuery(Station.class);
        Root root = query.from(Station.class);
        query = query.select(root);
        query.where(builder.equal(root.get("name"), name));

        Query q = s.createQuery(query);
        try {
            return (Station) q.getSingleResult();
        } catch (HibernateException e) {
            return null;
        }
    }

    @Override
    public boolean addStation(Station stn) {
        Session s = this.factory.getObject().getCurrentSession();
        try{
            s.save(stn);
            return true;
        }catch(Exception ex){
            return false;
        }
    }

    @Override
    public boolean editStation(Station stn) {
        Session s = this.factory.getObject().getCurrentSession();
        try{
            s.update(stn);
            return true;
        }catch(Exception ex){
            return false;
        }
    }

    @Override
    public boolean deleteStation(Station stn) {
        Session s = this.factory.getObject().getCurrentSession();
        try{
            s.update(stn);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
}
