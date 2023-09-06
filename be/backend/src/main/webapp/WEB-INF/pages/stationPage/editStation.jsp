<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">Station Detail</h1>

<c:url value="/admin/station" var="action" />

<form:form modelAttribute="Station" method="post" action="${action}" enctype="multipart/form-data">
    
    <form:hidden path="id" />

    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control " path="name" id="name" 
                    placeholder="Name" name="Name"  />
        <label for="Name">Name:</label>
        <form:errors path="name" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3">
        <form:label for="addressInfoCombo" path="addressInfo" class="pt-0">Address</form:label>
        <form:select class="form-select" id="addressInfoCombo" name="addressInfo" path="addressInfo">
            <c:forEach items="${addressInfo}" var="c">
                <c:choose>
                    <c:when test="${c.id == Station.addressInfo}">
                        <option value="${c.id}" selected>ID ${c.id}:  ${c.town}-${c.district}-${c.ward} 1</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${c.id}">ID ${c.id}:  ${c.town}-${c.district}-${c.ward}</option>
                    </c:otherwise>
                </c:choose>
                
            </c:forEach>
        </form:select>
        <form:errors path="addressInfo" element="div" cssClass="text-danger" />
    </div>
   <div class="form-floating mb-3 mt-3 d-flex justify-content-center">
        <button class="btn btn-info mt-1" type="submit">
            Update Station
        </button>
    </div>
    
</form:form>