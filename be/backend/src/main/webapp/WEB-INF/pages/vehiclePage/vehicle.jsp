<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:url value="" var="action"/>
<nav>
    <div class="container">
        <h1 >Vehicle Page</h1>
        <div class="d-flex">
            <button class="btn btn-success"><a href="/backend/admin/vehicle/add">Add Route</a></button>
            <form class="d-flex" action="${action}">
                <input class="form-control me-2" type="text" name="kw" placeholder="Nhập từ khóa...">
                <button class="btn btn-primary" type="submit">Tìm</button>
            </form>
        </div>
        <table class="table">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Seat Capacity</th>
                    <th>License Plate</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${vehicles}" var="v">
                    <tr>
                        <td>${v.id}</td>
                        <td>${v.seatCapacity}</td>
                        <td>${v.licensePlate}</td>
                        <td>
                            <c:url value="/admin/vehicle/${v.id}" var="api" />
                            <a href="${api}" class="btn btn-success">Update</a>
                            <button class="btn btn-danger" onclick="deleteVehicle('/backend/api/vehicle/${v.id}')">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <nav aria-label="Page navigation example " class="d-flex justify-content-center">
            <ul class="pagination">
                <c:forEach begin="1" end="${totalPage}" var="total"> 
                    <li class="page-item">
                      
                         <c:set var="pageUrl" value="${action}?page=${total}&kw=${param.kw}" />
                        <a class="page-link" href="${pageUrl}">${total}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>

    </div>

</nav>