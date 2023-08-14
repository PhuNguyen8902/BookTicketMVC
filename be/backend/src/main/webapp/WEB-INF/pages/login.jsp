<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/login-user" var="action" />

<h2>Login Page</h2>
<form method="post" action="${action}" enctype="multipart/form-data">
    <div class="form-floating mb-3 mt-3">
        <input type="text" class="form-control" id="email" placeholder="Email..." name="email">
        <label for="email">Email</label>
    </div>

    <div class="form-floating mt-3 mb-3">
        <input type="password" class="form-control" id="password" placeholder="Password..." name="password">
        <label for="password">Password</label>
    </div>

    <div class="form-floating mt-3 mb-3">
        <input type="submit" value="Login" class="btn btn-danger" />
    </div>
</form>
<%
    org.springframework.security.core.Authentication auth
            = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();

    if (auth != null && auth.isAuthenticated()) {
        String username = auth.getName();
        String userRole = "";

        for (org.springframework.security.core.GrantedAuthority authority : auth.getAuthorities()) {
            userRole = authority.getAuthority();
            break; // Lấy quyền đầu tiên (có thể chỉnh sửa theo nhu cầu)
        }
%>
<p>Welcome, <%= username%>! Your role: <%= userRole%></p>
<%
} else {
%>
<p>User not logged in.</p>
<%
    }
%>
