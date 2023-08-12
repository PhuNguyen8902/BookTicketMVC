<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">ROUTE DETAIL</h1>

<c:url value="/route" var="action" />
<form:form modelAttribute="route" method="post" action="${action}" enctype="multipart/form-data">

    <form:errors path="*" element="div" cssClass="alert alert-danger" />

    <form:hidden path="id" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" 
                    placeholder="Route name" name="name" />
        <label for="name">Route name:</label>
        <form:errors path="name" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating">
        <form:label for="startStationCombo" path="startStation" class="pt-0">Start Station</form:label>
        <form:select class="form-select" id="startStationCombo" name="startStation" path="startStation">
            <c:forEach items="${nameStation}" var="c">
                <c:choose>
                    <c:when test="${c.name == route.startStation}">
                        <option value="${c.id}" selected>${c.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${c.id}">${c.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <form:errors path="startStation" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mt-4">
        <form:label for="endStationCombo" path="endStation" class="pt-0">End Station</form:label>
        <form:select class="form-select" id="endStationCombo" name="endStation" path="endStation">
            <c:forEach items="${nameStation}" var="c">
                <c:choose>
                    <c:when test="${c.name == route.endStation}">
                        <option value="${c.id}" selected>${c.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${c.id}">${c.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <form:errors path="endStation" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="distance" id="distance" 
                    placeholder="Route distance" name="distance" />
        <label for="name">Route distance</label>
        <form:errors path="distance" element="div" cssClass="text-danger" />
     
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="duration" id="duration" 
                    placeholder="Route duration" name="duration" />
        <label for="name">Route duration</label>
        <form:errors path="duration" element="div" cssClass="text-danger" />
    </div>
    <script>
        console.log(`${route.startStation}`);
    </script>
    <div class="form-floating mb-3 mt-3 d-flex justify-content-center">
        <button class="btn btn-info mt-1" type="submit">
            Update Route
        </button>
    </div>


</form:form>