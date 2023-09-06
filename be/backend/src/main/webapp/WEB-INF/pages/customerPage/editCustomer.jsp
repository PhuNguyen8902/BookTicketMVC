<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/admin/customer" var="action" />

<h1 class="text-center text-info mt-1">Customer Detail</h1>

<form:form modelAttribute="customer" method="post" action="${action}" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />

    <form:hidden path="id" />
    <form:hidden path="avatar" />
    <form:hidden path="password" />
    <form:hidden path="preEmail" />
    
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="email" id="email" 
                    placeholder="Email" name="email" />
         <c:if test="${not empty param.emailError}">
            <span class="text-danger">${param.emailError}</span>
        </c:if>
        <label for="email">Email:</label>
        <form:errors path="email" element="div"  cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" 
                    placeholder="Name" name="name" />
        <label for="name">Name:</label>
        <form:errors path="name" element="div"  cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="phone" id="phone" 
                    placeholder="Phone" name="phone" />
        <label for="phone">Phone:</label>
        <form:errors path="phone" element="div"  cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3 d-flex justify-content-center">
        <button class="btn btn-info mt-1" type="submit">
            Update Customer 
        </button>
    </div>
</form:form>