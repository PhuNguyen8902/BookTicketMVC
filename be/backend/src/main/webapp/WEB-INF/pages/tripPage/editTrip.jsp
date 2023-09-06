<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">Trip Detail</h1>

<c:url value="/admin/trip" var="action" />

<form:form modelAttribute="Trip" method="post" action="${action}" enctype="multipart/form-data">

    <form:hidden path="id" />

    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="datetime-local" class="form-control " path="departureTime" id="departureTime" 
                    placeholder="Departure Time" name="departureTime"  />
        <c:if test="${not empty param.departureError}">
            <span class="text-danger">${param.departureError}</span>
        </c:if>
        <label for="departureTime">Departure Time:</label>
        <form:errors path="departureTime" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="datetime-local" class="form-control" path="arrivalTime" id="arrivalTime" 
                    placeholder="Arrival Time" name="arrivalTime" />
        <label for="arrivalTime">Arrival Time:</label>
        <form:errors path="arrivalTime" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="price" id="price" 
                    placeholder="Price" name="price" />
        <c:if test="${not empty param.priceError}">
            <span class="text-danger">${param.priceError}</span>
        </c:if>
        <label for="price">Price:</label>
        <form:errors path="price" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3">
        <form:label for="routeNameCombo" path="routeName" class="pt-0">Route</form:label>
        <form:select class="form-select" id="routeNameCombo" name="routeName" path="routeName">
            <c:forEach items="${routeName}" var="c">
                <c:choose>
                    <c:when test="${c.id == Trip.routeId}">
                        <option value="${c.id}" selected>${c.name}: ${c.startStation} - ${c.endStation}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${c.id}">${c.name}: ${c.startStation} - ${c.endStation}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <form:errors path="routeName" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3">
        <form:label for="driverNameCombo" path="driverName" class="pt-0">Driver Email</form:label>
        <form:select class="form-select" id="driverNameCombo" name="driverName" path="driverName">
            <c:forEach items="${driverName}" var="c">
                <c:choose>
                    <c:when test="${c.id == Trip.driverId}">
                        <option value="${c.id}" selected>${c.email}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${c.id}">${c.email}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <form:errors path="driverName" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3">
        <form:label for="seatCapacityCombo" path="seatCapacity" class="pt-0">Seat Capacity</form:label>
        <form:select class="form-select" id="seatCapacityCombo" name="seatCapacity" path="seatCapacity">
            <c:forEach items="${vehicleName}" var="c">
                <c:choose>
                    <c:when test="${c.id == Trip.vehicleId}">
                        <option value="${c.id}" selected>ID:${c.id} - ${c.seatCapacity}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${c.id}">ID:${c.id} - ${c.seatCapacity}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <form:errors path="seatCapacity" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3 d-flex justify-content-center">
        <button class="btn btn-info mt-1" type="submit">
            Update Trip 
        </button>
        <a href="/backend/admin/trip/addTicket/${id}" class="btn btn-success">addTicket</a>
    </div>


</form:form>