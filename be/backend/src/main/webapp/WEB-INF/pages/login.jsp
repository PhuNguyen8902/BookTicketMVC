<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/login-user" var="action" />

<h2>Login Page</h2>
<%--<form:form modelAttribute="loginRequest" method="post" action="${action}" enctype="multipart/form-data">
    <label for="email" >Username:</label>
    <form:input type="text" path="email" id="email" name="email" /><br/>
    <label for="password" >Password:</label>
    <form:input type="password" path="password" id="password" name="password" /><br/>
    <button class="btn btn-info mt-1" type="submit">
        Login
    </button>
</form:form>--%>
<form  method="post" action="${action}" enctype="multipart/form-data">
    <label for="email" >Username:</label>
    <input type="text" path="email" id="email" name="email" /><br/>
    <label for="password" >Password:</label>
    <input type="password" path="password" id="password" name="password" /><br/>
    <button class="btn btn-info mt-1" type="submit">
        Login
    </button>
</form>
<%
        org.springframework.security.core.Authentication auth = 
            org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        
        if (auth != null && auth.isAuthenticated()) {
            String username = auth.getName();
            String userRole = "";
            
            for (org.springframework.security.core.GrantedAuthority authority : auth.getAuthorities()) {
                userRole = authority.getAuthority();
                break; // Lấy quyền đầu tiên (có thể chỉnh sửa theo nhu cầu)
            }
    %>
            <p>Welcome, <%= username %>! Your role: <%= userRole %></p>
    <%
        } else {
    %>
            <p>User not logged in.</p>
    <%
        }
    %>
