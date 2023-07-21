package com.bookticket.pojo;

import com.bookticket.pojo.Comment;
import com.bookticket.pojo.Product;
import com.bookticket.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-21T14:23:20")
@StaticMetamodel(Comment.class)
public class Comment_ { 

    public static volatile SetAttribute<Comment, Comment> commentSet;
    public static volatile SingularAttribute<Comment, Product> productId;
    public static volatile SingularAttribute<Comment, Integer> id;
    public static volatile SingularAttribute<Comment, User> userId;
    public static volatile SingularAttribute<Comment, String> content;
    public static volatile SingularAttribute<Comment, Comment> rootComment;

}