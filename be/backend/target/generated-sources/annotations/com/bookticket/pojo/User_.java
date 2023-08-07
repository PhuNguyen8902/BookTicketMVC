package com.bookticket.pojo;

import com.bookticket.enums.Role;
import com.bookticket.pojo.Feedback;
import com.bookticket.pojo.Ticket;
import com.bookticket.pojo.Trip;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

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