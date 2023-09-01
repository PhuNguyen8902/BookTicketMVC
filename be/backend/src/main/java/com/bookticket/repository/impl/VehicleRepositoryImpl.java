/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository.impl;

import com.bookticket.dto.Request.VehicleRequest;
import com.bookticket.pojo.Vehicle;
import com.bookticket.repository.VehicleRepository;
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
 * @author vegar
 */
@Repository
@PropertySource("classpath:configs.properties")
public class VehicleRepositoryImpl implements VehicleRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Autowired
    private Environment env;


    @Override
    public List<VehicleRequest> getVehicles(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = b.createQuery(Object[].class);
        Root rVehicle = query.from(Vehicle.class);

        query = query.select(rVehicle);
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rVehicle.get("isActive"), 1));
        if (params != null) {
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                try {
                    short seatCapacityShort = Short.parseShort(kw);
                    predicates.add(b.equal(rVehicle.get("seatCapacity"), seatCapacityShort));
                } catch (NumberFormatException e) {
                    // Handle the case when "kw" is not a valid short value
                    // You can choose to ignore it, log an error, or take other appropriate actions
                }
            }
        }
        
        query.where(predicates.toArray(new Predicate[predicates.size()]));
        
        Query q = s.createQuery(query);
        
        
        List<Vehicle> vehicleListTest = q.getResultList();
        int size = vehicleListTest.size();
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
        
        List<Vehicle> vehicleList = q.getResultList();
        List<VehicleRequest> list = new ArrayList<>();
        for (Vehicle o : vehicleList) {
            VehicleRequest t = new VehicleRequest();
            t.setId(o.getId());
            t.setSeatCapacity(o.getSeatCapacity());
            t.setLicensePlate(o.getLicensePlate());
            t.setTotalPage(totalPage);
            list.add(t);
        }
        
        return list;
    }

    @Override
    public boolean addVehicle(Vehicle vehicle) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(vehicle);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean editVehicle(Vehicle vehicle) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(vehicle);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteVehicle(Vehicle vehicle) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(vehicle);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Vehicle getVehicleById(Integer id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Vehicle> query = b.createQuery(Vehicle.class);
        Root rVehicle = query.from(Vehicle.class);
        
        query.where(b.equal(rVehicle.get("id"), id));
        
        query.select(rVehicle);
        Query q = s.createQuery(query);
        
        return (Vehicle) q.getSingleResult();
    }

    @Override
    public List<Vehicle> getVehicleName() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Vehicle> query = b.createQuery(Vehicle.class);
        Root rVehicle = query.from(Vehicle.class);
        
        query.where(b.equal(rVehicle.get("isActive"), "1"));
        
        query.select(rVehicle);
        Query q = s.createQuery(query);
        
        return q.getResultList();
    }

}
