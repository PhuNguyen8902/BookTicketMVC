<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/login" var="action" />

<h2>Login Page</h2>
<form:form modelAttribute="loginRequest" method="post" action="${action}" enctype="multipart/form-data">
    <form:label for="email" path="email">Username:</form:label>
    <form:input type="text" path="email" id="email" name="email" /><br/>
    <form:label for="password" path="password">Password:</form:label>
    <form:input type="password" path="password" id="password" name="password" /><br/>
    <button class="btn btn-info mt-1" type="submit">
        Login
    </button>
</form:form>
 <p>Hello, ${pageContext.request.userPrincipal.password}!</p>

