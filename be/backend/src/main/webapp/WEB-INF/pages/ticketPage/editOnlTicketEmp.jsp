<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:url value="/employee/onlTicket" var="action" />
<h1 class="text-center text-info mt-1">Ticket Detail</h1>

<form:form modelAttribute="ticket" method="post" action="${action}" enctype="multipart/form-data">

    <form:errors path="*" element="div" cssClass="alert alert-danger" />

    <%--<form:hidden path="id" />--%>
    <form:hidden path="selectSeat" />
        <form:hidden path="increasePriceId" />

    <%--<form:hidden path="payment" />--%>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="id" id="id" 
                    placeholder="Id" name="id" readonly="true" />
        <label for="id">Id:</label>

    </div>
    <div class="form-floating mb-3">

        <form:input type="text" class="form-control" path="userName" id="userName" 
                    placeholder="userName" name="userName" readonly="true" />
        <label for="userName">Customer:</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="payment" id="payment" 
                    placeholder="payment" name="payment" readonly="true" />
        <label for="payment">Payment:</label>

    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="ticTypeStr" id="ticTypeStr" 
                    placeholder="ticTypeStr" name="ticTypeStr" readonly="true" />
        <label for="ticTypeStr">Type:</label>
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

    <div class="form-floating mb-3">
        <form:label for="routeCombo" path="route" class="pt-0">Trip</form:label>
        <form:select class="form-select" id="routeCombo" name="route" path="route">
            <c:forEach items="${ticket.listTrip}" var="c">
                <c:choose>
                    <c:when test="${c.id == ticket.tripId}">
                        <option value="${c.id}" selected>${c.routeName}: ${c.departureTime} - ${c.arrivalTime} - ${c.price}VND</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${c.id}">${c.routeName}: ${c.departureTime} - ${c.arrivalTime} - ${c.price}VND</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <form:errors path="route" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="date" id="date" 
                    placeholder="date" name="date" readonly="true" />
        <label for="date">Book Date:</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="increasePrice" id="increasePrice" 
                    placeholder="increasePrice" name="increasePrice" readonly="true" />
        <label for="increasePrice">Increase Price:</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="employee" id="employee" 
                    placeholder="employee" name="employee" readonly="true" />
        <label for="employee">Employee:</label>
    </div>

    <script>
    </script>
    <div class="form-floating mb-3 mt-3 d-flex justify-content-center">
        <button class="btn btn-info mt-1" type="submit">
            Update Ticket
        </button>
    </div>


</form:form>
