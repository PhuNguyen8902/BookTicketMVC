package com.bookticket.pojo;

import com.bookticket.pojo.Category;
import com.bookticket.pojo.Comment;
import com.bookticket.pojo.OrderDetail;
import com.bookticket.pojo.ProductImg;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-21T22:01:49")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, String> image;
    public static volatile SingularAttribute<Product, Integer> quantity;
    public static volatile SetAttribute<Product, Comment> commentSet;
    public static volatile SingularAttribute<Product, Integer> price;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, String> description;
    public static volatile SetAttribute<Product, ProductImg> productImgSet;
    public static volatile SingularAttribute<Product, Integer> id;
    public static volatile SingularAttribute<Product, Boolean> isActive;
    public static volatile SingularAttribute<Product, Category> categoryId;
    public static volatile SetAttribute<Product, OrderDetail> orderDetailSet;

}