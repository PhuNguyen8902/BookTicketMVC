<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">ADD Trip</h1>

<c:url value="" var="action" />

<form:form modelAttribute="addTripModel" method="post" action="${action}" enctype="multipart/form-data">
    
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="departureTime" id="departureTime" 
                    placeholder="Departure Time" name="departureTime" />
        <label for="departureTime">Departure Time:</label>
        <form:errors path="departureTime" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="arrivalTime" id="arrivalTime" 
                    placeholder="Arrival Time" name="arrivalTime" />
        <label for="arrivalTime">Arrival Time:</label>
        <form:errors path="arrivalTime" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="price" id="price" 
                    placeholder="Price" name="price" />
        <label for="price">Price:</label>
        <form:errors path="price" element="div" cssClass="text-danger" />
    </div>
     <div class="form-floating mb-3">
        <form:label for="driverNameCombo" path="driverName" class="pt-0">Seat Capacity</form:label>
        <form:select class="form-select" id="driverNameCombo" name="driverName" path="driverName">
            <c:forEach items="${driverName}" var="c">
                <option value="${c.name}">${c.name}</option>
            </c:forEach>
        </form:select>
        <form:errors path="driverName" element="div" cssClass="text-danger" />
    </div>
     <div class="form-floating">
        <form:label for="startStationCombo" path="startStation" class="pt-0">Start Station</form:label>
        <form:select class="form-select" id="startStationCombo" name="startStation" path="startStation">
            <c:forEach items="${nameStation}" var="c">
                <option value="${c.id}">${c.name}</option>
            </c:forEach>
        </form:select>
        <form:errors path="startStation" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mt-4">
        <form:label for="endStationCombo" path="endStation" class="pt-0">End Station</form:label>
        <form:select class="form-select" id="endStationCombo" name="endStation" path="endStation">
            <c:forEach items="${nameStation}" var="c">
                <option value="${c.id}">${c.name}</option>
            </c:forEach>
        </form:select>
        <form:errors path="endStation" element="div" cssClass="text-danger" />
    </div>
    
    
    
</form:form>