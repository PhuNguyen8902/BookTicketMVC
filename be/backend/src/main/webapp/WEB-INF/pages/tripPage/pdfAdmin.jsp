<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:url value="" var="action" />
<h1 class="text-center text-info mt-1">Add Ticket</h1>

<form:form modelAttribute="ticket" method="post" action="${action}" enctype="multipart/form-data">

    <form:errors path="*" element="div" cssClass="alert alert-danger" />

  
    <form:hidden path="tripId" />
    <form:hidden path="id" />


    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="userName" id="userName" 
                    placeholder="Username" name="userName" readonly="true"/>
        <label for="name">Username:</label>
        <form:errors path="userName" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="seat" id="seat" 
                    placeholder="Seat" name="seat" readonly="true"/>
        <label for="name">Seat:</label>
        <form:errors path="seat" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="price" id="seat" 
                    placeholder="Price" name="price" readonly="true"/>
        <label for="name">Price:</label>
        <form:errors path="price" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="date" id="seat" 
                    placeholder="Date" name="date" readonly="true"/>
        <label for="name">Date:</label>
        <form:errors path="date" element="div" cssClass="text-danger" />
    </div>
     <div class="form-floating mb-3">
        <form:label for="routeCombo" path="route" class="pt-0">Trip</form:label>
        <form:select class="form-select" id="routeCombo" name="route" path="route" disabled="true">
            <c:forEach items="${tripInfo}" var="c">
                <c:choose>
                    <c:when test="${c.id == ticket.tripId}">
                        <option value="${c.id}" selected>${c.routeName}: ${c.departureTime} - ${c.arrivalTime}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${c.id}">${c.routeName}: ${c.departureTime} - ${c.arrivalTime}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <form:errors path="route" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3">
        <form:label for="increasePriceCombo" path="increasePrice" class="pt-0">Event Name</form:label>
        <form:select class="form-select" id="increasePriceCombo" name="increasePrice" path="increasePrice" disabled="true">
            <c:forEach items="${IncreasedPriceInfo}" var="c">
                <c:choose>
                    <c:when test="${c.eventName == ticket.increasePrice}">
                        <option value="${c.id}" selected>${c.eventName}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${c.id}">${c.eventName}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <form:errors path="increasePrice" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3">
        <form:label for="employeeCombo" path="employee" class="pt-0">Employee</form:label>
        <form:select class="form-select" id="employeeCombo" name="employee" path="employee" disabled="true">
            <c:forEach items="${employeeInfo}" var="c">
                <c:choose>
                    <c:when test="${c.name == ticket.employee}">
                        <option value="${c.id}" selected>${c.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${c.id}">${c.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <form:errors path="employee" element="div" cssClass="text-danger" />
    </div>                

    <script>
    </script>
    <div class="form-floating mb-3 mt-3 d-flex justify-content-center">
        <button class="btn btn-info mt-1" type="submit">
            Export BDF
        </button>
    </div>
    <div class="form-floating mb-3 mt-3 d-flex justify-content-center">
        <a href="/backend/employee/trip/addTicket/${tripId}" class="btn btn-info mt-1">
            back to Add Ticket
        </a>
    </div>

</form:form>
