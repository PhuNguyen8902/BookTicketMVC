<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:url value="/admin/address" var="action" />
<h1 class="text-center text-info mt-1">Address Detail</h1>

<form:form modelAttribute="address" method="post" action="${action}" enctype="multipart/form-data">

    <form:errors path="*" element="div" cssClass="alert alert-danger" />

    <form:hidden path="id" />
  
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="town" id="town" 
                    placeholder="Town" name="town" />
        <label for="name">Town:</label>
        <form:errors path="town" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="district" id="district" 
                    placeholder="District" name="district" />
        <label for="name">District:</label>
        <form:errors path="district" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="ward" id="ward" 
                    placeholder="Ward" name="ward" />
        <label for="name">Ward: </label>
        <form:errors path="ward" element="div" cssClass="text-danger" />
    </div>
    
    <script>
    </script>
    <div class="form-floating mb-3 mt-3 d-flex justify-content-center">
        <button class="btn btn-info mt-1" type="submit">
            Update Address
        </button>
    </div>


</form:form>
