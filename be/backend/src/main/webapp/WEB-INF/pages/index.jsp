
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


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
<h1 class="text-center text-info mt-1"><%= username%></h1>
<p>Your role: <%= userRole%></p>
<%
} else {
%>

<%
    }
%>