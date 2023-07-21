package com.bookticket.pojo;

import com.bookticket.pojo.Address;
import com.bookticket.pojo.Comment;
import com.bookticket.pojo.Orders;
import com.bookticket.pojo.RefeshToken;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-21T22:01:49")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SetAttribute<User, Address> addressSet;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> role;
    public static volatile SetAttribute<User, Comment> commentSet;
    public static volatile SingularAttribute<User, String> phone;
    public static volatile SingularAttribute<User, RefeshToken> refeshToken;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, String> id;
    public static volatile SetAttribute<User, Orders> ordersSet;
    public static volatile SingularAttribute<User, String> email;

}