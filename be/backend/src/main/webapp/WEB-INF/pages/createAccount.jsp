<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/admin/create-account" var="action" />

<h2>Create Account Page</h2>
<form:form modelAttribute="create" method="post" action="${action}" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />

    <div class="form-floating mb-3 mt-3">
        <input type="file" class="form-control" id="image" name="image" accept="image/*">
        <label for="image">Image</label>
    </div>
    <div>
        <form:errors path="image" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <input type="text" class="form-control" id="name" placeholder="Name..." name="name">
        <label for="name">Name</label>
    </div>
    <div>
        <form:errors path="name" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <input type="tel" class="form-control" id="phone" placeholder="Phone..." name="phone">
        <label for="phone">Phone</label>
    </div>
    <div>
        <form:errors path="phone" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <input type="email" class="form-control" id="email" placeholder="Email..." name="email">
        <label for="email">Email</label>
    </div>
    <div>
        <form:errors path="email" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mt-3 mb-3">
        <input type="password" class="form-control" id="password" placeholder="Password..." name="password">
        <label for="password">Password</label>
    </div>
    <div>
        <form:errors path="password" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <select class="form-control" id="role" name="role">
            <option value="ROLE_EMPLOYEE">Employee</option>
            <option value="ROLE_CUSTOMER">Customer</option>
            <option value="ROLE_DRIVER">Driver</option>
        </select>
        <label for="role">Role</label>
    </div>

    <div class="form-floating mt-3 mb-3">
        <input type="submit" value="Create Account" class="btn btn-danger" />
    </div>
</form:form>