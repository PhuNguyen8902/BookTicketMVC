package com.bookticket.pojo;

import com.bookticket.pojo.OrderDetail;
import com.bookticket.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-22T11:56:51")
@StaticMetamodel(Orders.class)
public class Orders_ { 

    public static volatile SingularAttribute<Orders, Double> totalPrice;
    public static volatile SingularAttribute<Orders, Date> receiveDate;
    public static volatile SingularAttribute<Orders, Integer> id;
    public static volatile SingularAttribute<Orders, String> state;
    public static volatile SingularAttribute<Orders, Date> orderDate;
    public static volatile SingularAttribute<Orders, User> userId;
    public static volatile SetAttribute<Orders, OrderDetail> orderDetailSet;

}