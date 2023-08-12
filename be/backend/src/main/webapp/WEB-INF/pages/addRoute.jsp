<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">ADD ROUTE</h1>

<c:url value="/route/add" var="action" />

<form:form modelAttribute="addRouteModel" method="post" action="${action}" enctype="multipart/form-data">

    <form:errors path="*" element="div" cssClass="alert alert-danger" />

    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" 
                    placeholder="Name" name="name" />
        <label for="name">Name:</label>
        <form:errors path="name" element="div" cssClass="text-danger" />
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
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="distance" id="distance" 
                    placeholder="Distance" name="distance" />
        <label for="name">Distance</label>
        <form:errors path="distance" element="div" cssClass="text-danger" />

    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="duration" id="duration" 
                    placeholder="Duration" name="duration" />
        <label for="name">Duration</label>
        <form:errors path="duration" element="div" cssClass="text-danger" />
    </div>
    <script>
        console.log(`${route.startStation}`);
    </script>
    <div class="form-floating mb-3 mt-3 d-flex justify-content-center">
        <button class="btn btn-info mt-1" type="submit">
            Add Route
        </button>
    </div>


</form:form>