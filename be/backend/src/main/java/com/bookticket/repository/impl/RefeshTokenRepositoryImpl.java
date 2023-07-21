/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.repository.impl;

import com.bookticket.pojo.RefeshToken;
import com.bookticket.repository.RefeshTokenRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
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
public class RefeshTokenRepositoryImpl implements RefeshTokenRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public RefeshToken getRefeshTokenByToken(String token) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<RefeshToken> query = builder.createQuery(RefeshToken.class);
        Root root = query.from(RefeshToken.class);
        query = query.select(root);
        Predicate p = builder.equal(root.get("token").as(String.class), token.trim());
        query = query.where(p);
        Query q = s.createQuery(query);
        try {
            return (RefeshToken) q.getSingleResult();
        } catch (NoResultException e) {
            return null; // Trả về null khi không tìm thấy RefreshToken
        }
    }

    private void deleteRefeshToken(String token) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaDelete<RefeshToken> deleteQuery = builder.createCriteriaDelete(RefeshToken.class);
        Root<RefeshToken> root = deleteQuery.from(RefeshToken.class);
        deleteQuery.where(builder.equal(root.get("token").as(String.class), token));
        Query delete = s.createQuery(deleteQuery);
        delete.executeUpdate();
    }

    @Override
    public boolean addRefeshToken(RefeshToken token) {
        try {
            Session s = this.factory.getObject().getCurrentSession();
            s.save(token);
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public boolean isRefeshTokenExpired(String token) {
//        String expirationDateString = "2023-07-19 22:00:52.889000";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
//        String dFormat = "yyyy-MM-dd HH:mm:ss.SSS";
        RefeshToken refeshToken = getRefeshTokenByToken(token);
        if (refeshToken != null) {
            Date expirationDate = refeshToken.getExpiryDate();
            Date currentDate = new Date();
//
//            try {
//                String e = sdf.format(expirationDate);
//                Date t = sdf.parse(e);
////                expirationDate = sdf.parse(expirationDateString);
//                String currentDateFormat = sdf.format(currentDate);
//                Date c = sdf.parse(currentDateFormat);
////                currentDate = sdf.parse(currentDateFormat);
////                currentDate = sdf.parse(currentDate);
//                System.out.println("------------------------------day ne");
//
//                System.out.println(expirationDate);
//                System.out.println(c);
//                System.out.println(currentDateFormat);
//                System.out.println(currentDate);
//                System.out.println(e);
//
//                System.out.println(t);
//
//                System.out.println("------------------------------day ne");
//
//                if (t.before(c)) {
//                    return true;
//                } else {
//                    deleteRefeshToken(token);
//                    return false;
//                }
//
//            } catch (ParseException ex) {
//                Logger.getLogger(RefeshTokenRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
////                 deleteRefeshToken(token);
////                return false;
//            }
//            SimpleDateFormat sdfOutput = new SimpleDateFormat(dFormat);
//            String currentDateFormatString = sdfOutput.format(currentDate);
    long c1 = expirationDate.getTime();
    long c2 = currentDate.getTime();

//            try {
//                Date c = sdf.parse(currentDateFormatString); // Chuyển chuỗi d thành đối tượng Date
                System.out.println("------------------------------day ne");
//                                System.out.println(c); // In ra giá trị của c kiểu Date
                                System.out.println(c1); // In ra giá trị của c kiểu Date
                                System.out.println(c2); // In ra giá trị của c kiểu Date

//
//                System.out.println(currentDate);
//                System.out.println(expirationDate);
                System.out.println("------------------------------day ne");

                if (c1>c2) {
                    return true;
                } else {
                    deleteRefeshToken(token);
                    return false;
                }
//            } catch (ParseException ex) {
//                // Xử lý nếu có lỗi trong quá trình parse
//                ex.printStackTrace();
//            }
//            System.out.println("------------------------------day ne");
//
//            System.out.println(currentDate);
//            System.out.println(expirationDate);
//            System.out.println("------------------------------day ne");
//
//            if (expirationDate.before(currentDate)) {
//                return true;
//            } else {
//                deleteRefeshToken(token);
//                return false;
//            }

        }
        return false;

    }

    @Override
    public RefeshToken getRefeshTokenByUserId(String userId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<RefeshToken> query = builder.createQuery(RefeshToken.class);
        Root root = query.from(RefeshToken.class);
        query = query.select(root);
        Predicate p = builder.equal(root.get("userId").get("id").as(String.class), userId.trim());
        query = query.where(p);
        Query q = s.createQuery(query);
        try {
            return (RefeshToken) q.getSingleResult();
        } catch (NoResultException e) {
            return null; // Trả về null khi không tìm thấy RefreshToken
        }
    }

}
