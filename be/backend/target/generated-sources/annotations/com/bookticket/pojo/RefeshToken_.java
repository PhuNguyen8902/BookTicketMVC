package com.bookticket.pojo;

import com.bookticket.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


@StaticMetamodel(RefeshToken.class)
public class RefeshToken_ { 

    public static volatile SingularAttribute<RefeshToken, Date> expiryDate;
    public static volatile SingularAttribute<RefeshToken, Integer> id;
    public static volatile SingularAttribute<RefeshToken, User> userId;
    public static volatile SingularAttribute<RefeshToken, String> token;

}