package com.bookticket.pojo;

import com.bookticket.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-21T14:23:20")
@StaticMetamodel(Address.class)
public class Address_ { 

    public static volatile SingularAttribute<Address, String> town;
    public static volatile SingularAttribute<Address, String> district;
    public static volatile SingularAttribute<Address, Integer> id;
    public static volatile SingularAttribute<Address, String> ward;
    public static volatile SingularAttribute<Address, User> userId;

}