<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:url value="/admin/onlTicket" var="action" />
<h1 class="text-center text-info mt-1">Ticket Detail</h1>

<form:form modelAttribute="ticket" method="post" action="${action}" enctype="multipart/form-data">

    <form:errors path="*" element="div" cssClass="alert alert-danger" />

    <form:hidden path="id" />
    <form:hidden path="selectSeat" />
    <form:hidden path="payment" />
    
    <div class="form-floating mb-3">
        <form:label for="userName" path="userName" class="pt-0">Customer</form:label>
        <form:select class="form-select" id="userNameCombo" name="userName" path="userName">
            <c:forEach items="${customerInfo}" var="c">
                <c:choose>
                    <c:when test="${c.email == ticket.userName}">
                        <option value="${c.id}" selected>${c.email}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${c.id}">${c.email}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <form:errors path="userName" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="seat" id="seat" 
                    placeholder="Seat" name="seat" />
        <c:if test="${not empty param.seatError}">
            <span class="text-danger">${param.seatError}</span>
        </c:if>
        <label for="name">Seat:</label>
        <form:errors path="seat" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="datetime-local" class="form-control " path="date" id="date" 
                    placeholder="Date" name="date"  />
        <label for="date">Date:</label>
        <form:errors path="date" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3">
        <form:label for="routeCombo" path="route" class="pt-0">Trip</form:label>
        <form:select class="form-select" id="routeCombo" name="route" path="route">
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
        <form:label for="increasePriceCombo" path="increasePrice" class="pt-0">Increase Price</form:label>
        <form:select class="form-select" id="increasePriceCombo" name="increasePrice" path="increasePrice">
            <c:forEach items="${IncreasedPriceInfo}" var="c">
                <c:choose>
                    <c:when test="${c.eventName == ticket.increasePrice}">
                        <option value="${c.id}" selected>${c.eventName}: ${c.increasedPercentage}%</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${c.id}">${c.eventName}: ${c.increasedPercentage}%</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <form:errors path="increasePrice" element="div" cssClass="text-danger" />
    </div>
     <div class="form-floating mb-3">
        <form:label for="employeeCombo" path="employee" class="pt-0">Employee</form:label>
        <form:select class="form-select" id="employeeCombo" name="employee" path="employee">
            <c:forEach items="${employeeInfo}" var="c">
                <c:choose>
                    <c:when test="${c.name == ticket.employee}">
                        <option value="${c.id}" selected>${c.email}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${c.id}">${c.email}</option>
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
            Update Ticket
        </button>
    </div>


</form:form>
