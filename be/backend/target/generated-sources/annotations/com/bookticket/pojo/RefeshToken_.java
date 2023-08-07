package com.bookticket.pojo;

import com.bookticket.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

// <<<<<<< phu3
@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-07T12:15:41")
// =======
// @Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-07T12:06:24")
// >>>>>>> main
@StaticMetamodel(RefeshToken.class)
public class RefeshToken_ { 

    public static volatile SingularAttribute<RefeshToken, Date> expiryDate;
    public static volatile SingularAttribute<RefeshToken, Integer> id;
    public static volatile SingularAttribute<RefeshToken, User> userId;
    public static volatile SingularAttribute<RefeshToken, String> token;

}