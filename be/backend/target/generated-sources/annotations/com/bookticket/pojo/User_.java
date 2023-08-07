package com.bookticket.pojo;

import com.bookticket.enums.Role;
import com.bookticket.pojo.Feedback;
import com.bookticket.pojo.Ticket;
import com.bookticket.pojo.Trip;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

// <<<<<<< phu3
@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-07T12:15:41")
// =======
// @Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-07T12:06:24")
// >>>>>>> main
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SetAttribute<User, Ticket> ticketSet1;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, Role> role;
    public static volatile SetAttribute<User, Ticket> ticketSet;
    public static volatile SingularAttribute<User, String> phone;
    public static volatile SetAttribute<User, Feedback> feedbackSet;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SetAttribute<User, Trip> tripSet;
    public static volatile SingularAttribute<User, String> avatar;
    public static volatile SingularAttribute<User, String> id;
    public static volatile SingularAttribute<User, Short> isActive;
    public static volatile SingularAttribute<User, String> email;

}