<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">ADD Vehicle</h1>

<c:url value="" var="action" />

<form:form modelAttribute="addVehicleModel" method="post" action="${action}" enctype="multipart/form-data">

    <form:errors path="*" element="div" cssClass="alert alert-danger" />

    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="seatCapacity" id="seatCapacity" 
                    placeholder="Seat Capacity" name="seatCapacity" />
        <label for="name">Seat Capacity:</label>
        <form:errors path="seatCapacity" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="licensePlate" id="licensePlate" 
                    placeholder="License Plate" name="licensePlate" />
        <label for="name">License Plate:</label>
        <form:errors path="licensePlate" element="div" cssClass="text-danger" />
    </div>
    
    <script>
    </script>
    <div class="form-floating mb-3 mt-3 d-flex justify-content-center">
        <button class="btn btn-info mt-1" type="submit">
            Add Vehicle
        </button>
    </div>


</form:form>
