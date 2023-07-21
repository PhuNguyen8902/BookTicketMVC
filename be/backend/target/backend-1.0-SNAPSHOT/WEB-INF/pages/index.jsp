<%-- 
    Document   : index
    Created on : Jul 15, 2023, 1:49:10 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Ticket</title>
    </head>
    <body>
        <h1>${full}</h1>
        <a href="<c:url value="/test" />">day ne</a>

        <form:form method="post" action="/backend/hello-post/" modelAttribute="user">
            <form:input path="email"/>
            <form:input path="password"/>
            <input type="submit" value="Send" />
        </form:form>
    </body>
</html>
