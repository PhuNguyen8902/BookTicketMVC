/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository.impl;

import com.bookticket.dto.Request.IncreasedPriceRequest;
import com.bookticket.pojo.IncreasedPrice;
import com.bookticket.repository.IncreasedPriceRepository;
import java.util.ArrayList;
import java.util.Date;
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
public class IncreasedPriceRepositoryImpl implements IncreasedPriceRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private Environment env;

    @Override
    public List<IncreasedPriceRequest> getIncreasedPrice(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = b.createQuery(Object[].class);
        Root rIncreasedPrice = query.from(IncreasedPrice.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rIncreasedPrice.get("isActive"), 1));
        if (params != null) {
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(rIncreasedPrice.get("eventName"), String.format("%%%s%%", kw)));
            }
        }

        query.where(predicates.toArray(new Predicate[predicates.size()]));
        query.select(rIncreasedPrice);

        Query q = s.createQuery(query);

        List<IncreasedPriceRequest> increasedPriceRequestListTest = q.getResultList();
        int size = increasedPriceRequestListTest.size();
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

        List<IncreasedPrice> vehicleList = q.getResultList();
        List<IncreasedPriceRequest> list = new ArrayList<>();
        for (IncreasedPrice o : vehicleList) {
            IncreasedPriceRequest i = new IncreasedPriceRequest();
            i.setId(o.getId());
            i.setEventName(o.getEventName());
            i.setIncreasedPercentage(o.getIncreasedPercentage().toString());
            if (o.getStartDate() != null) {
                i.setStartDate(o.getStartDate().toString());
            } else {
                i.setStartDate("");
            }
            if (o.getEndDate() != null) {
                i.setEndDate(o.getEndDate().toString());
            } else {
                i.setEndDate("");
            }

            i.setTotalPage(totalPage);
            list.add(i);
        }

        return list;
    }

    @Override
    public boolean addIncreasedPrice(IncreasedPrice ip) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(ip);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean editIncreasedPrice(IncreasedPrice ip) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(ip);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteIncreasedPrice(IncreasedPrice ip) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(ip);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public IncreasedPrice getIncreasedPriceById(Integer id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = b.createQuery(Object[].class);
        Root rIncreasedPrice = query.from(IncreasedPrice.class);

        query.where(b.equal(rIncreasedPrice.get("id"), id));

        query.select(rIncreasedPrice);

        Query q = s.createQuery(query);

        return (IncreasedPrice) q.getSingleResult();
    }

    @Override
    public List<IncreasedPrice> getIncreasedPriceInfo() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = b.createQuery(Object[].class);
        Root rIncreasedPrice = query.from(IncreasedPrice.class);

        query.where(b.equal(rIncreasedPrice.get("isActive"), "1"));

        query.select(rIncreasedPrice);

        Query q = s.createQuery(query);

        return q.getResultList();
    }

    @Override
    public IncreasedPrice checkIncreasePrice(long date) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<IncreasedPrice> query = b.createQuery(IncreasedPrice.class);
        Root rIncreasedPrice = query.from(IncreasedPrice.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rIncreasedPrice.get("isActive"), 1));
        predicates.add(b.lessThanOrEqualTo(rIncreasedPrice.get("startDate"), new Date(date)));
        predicates.add(b.greaterThanOrEqualTo(rIncreasedPrice.get("endDate"), new Date(date)));

        query.where(predicates.toArray(new Predicate[predicates.size()]));

        query.select(rIncreasedPrice);

        Query q = s.createQuery(query);
        List<IncreasedPrice> result = q.getResultList();

        if (result.isEmpty()) {
            return getNoneIncrease();
        } else {
            return result.get(0);
        }

    }

    private IncreasedPrice getNoneIncrease() {
           System.out.println("--------------------------------------result2nonene");
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<IncreasedPrice> query = b.createQuery(IncreasedPrice.class);
        Root rIncreasedPrice = query.from(IncreasedPrice.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rIncreasedPrice.get("isActive"), 1));
        predicates.add(b.equal(rIncreasedPrice.get("increasedPercentage"), 0));

        query.where(predicates.toArray(new Predicate[predicates.size()]));

        query.select(rIncreasedPrice);

        Query q = s.createQuery(query);
        IncreasedPrice result = (IncreasedPrice) q.getSingleResult();
        return result;
    }
}
