<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">IncreasedPrice Detail</h1>

<c:url value="/admin/increasedPrice" var="action" />

<form:form modelAttribute="IncreasedPrice" method="post" action="${action}" enctype="multipart/form-data">

    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    
    <form:hidden path="id" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="eventName" id="eventName" 
                    placeholder="Event Name" name="eventName" />
        <label for="name">Event Name: </label>
        <form:errors path="eventName" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="increasedPercentage" id="increasedPercentage" 
                    placeholder="Increased Percentage" name="increasedPercentage" />
        <label for="name">Increased Percentage:</label>
        <form:errors path="increasedPercentage" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="Date" class="form-control" path="startDate" id="startDate" 
                    placeholder="Start Date" name="startDate" />
        <label for="name">Start Date:</label>
        <form:errors path="startDate" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="Date" class="form-control" path="endDate" id="endDate" 
                    placeholder="End Date" name="endDate" />
        <label for="name">End Date:</label>
        <form:errors path="endDate" element="div" cssClass="text-danger" />
    </div>
    
    <script>
    </script>
    <div class="form-floating mb-3 mt-3 d-flex justify-content-center">
        <button class="btn btn-info mt-1" type="submit">
            Update IncreasedPrice
        </button>
    </div>


</form:form>
