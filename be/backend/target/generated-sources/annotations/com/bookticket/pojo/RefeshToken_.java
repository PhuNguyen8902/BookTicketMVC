package com.bookticket.pojo;

import com.bookticket.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-26T17:04:21")
@StaticMetamodel(RefeshToken.class)
public class RefeshToken_ { 

    public static volatile SingularAttribute<RefeshToken, Date> expiryDate;
    public static volatile SingularAttribute<RefeshToken, Integer> id;
    public static volatile SingularAttribute<RefeshToken, User> userId;
    public static volatile SingularAttribute<RefeshToken, String> token;

}