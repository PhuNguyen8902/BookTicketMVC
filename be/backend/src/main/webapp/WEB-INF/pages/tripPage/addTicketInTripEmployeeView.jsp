<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:url value="" var="action" />
<h1 class="text-center text-info mt-1">Add Ticket</h1>
<form:form modelAttribute="addTicketInTrip" method="post" action="${action}" enctype="multipart/form-data">

    <form:errors path="*" element="div" cssClass="alert alert-danger" />

    <c:if test="${not empty param.timeError}">
        <span class="text-danger">${param.timeError}</span>
    </c:if>

    <form:hidden path="tripId" />
    <form:hidden path="eventName" />
    <form:hidden path="increasedPercentage" />
        <form:hidden path="increasePriceId" />



    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="userName" id="userName" 
                    placeholder="Username" name="userName" />
        <label for="name">Username:</label>
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
        <form:input type="text" class="form-control" path="increasePrice" id="increasePriceCombo" 
                    placeholder="Increase Price" name="increasePrice" readonly="readonly" 
                    value="${addTicketInTrip.eventName}: ${addTicketInTrip.increasedPercentage}%" />
        <label for="increasePriceCombo">Increase Price:</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:label for="ticTypeCombo" path="ticType" class="pt-0">Type</form:label>
        <form:select class="form-select" id="ticTypeCombo" name="ticType" path="ticType">
            <option value="1">Adult</option>
            <option value="0">Children</option>
        </form:select>
        <form:errors path="ticType" element="div" cssClass="text-danger" />
    </div>


    <!--<div class="form-floating mb-3">-->
    <%--<form:label for="increasePriceCombo" path="increasePrice" class="pt-0">Increase Price</form:label>--%>
    <%--<form:select class="form-select" id="increasePriceCombo" name="increasePrice" path="increasePrice">--%>
    <%--<c:forEach items="${IncreasedPriceInfo}" var="c">--%>
        <!--<option value="${c.id}">${c.eventName}: ${c.increasedPercentage}%</option>-->
    <%--</c:forEach>--%>
    <%--</form:select>--%>
    <%--<form:errors path="increasePrice" element="div" cssClass="text-danger" />--%>
    <!--</div>-->


    <script>
    </script>
    <div class="form-floating mb-3 mt-3 d-flex justify-content-center">
        <button class="btn btn-info mt-1" type="submit">
            Add Ticket
        </button>
    </div>
    <div class="form-floating mb-3 mt-3 d-flex justify-content-center">
        <a href="/backend/employee/trip" class="btn btn-info mt-1">
            Back to trip
        </a>
    </div>

</form:form>
