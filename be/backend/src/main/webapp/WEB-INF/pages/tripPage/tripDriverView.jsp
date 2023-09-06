<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:url value="" var="action"/>
<nav>
    <div class="container">
        <h1 >Trip Page</h1>
        <div class="d-flex">
            <form class="d-flex" action="${action}">
                <input class="form-control me-2" type="date" name="kw" placeholder="Nhập ngày đi...">
                <input class="form-control me-2" type="text" name="endStationKw" placeholder="Nhập điểm đến...">
                <button class="btn btn-primary" type="submit">Tìm</button>
            </form>
        </div>
        <table class="table">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Departure Time</th>
                    <th>Arrival Time</th>
                    <th>Route</th>
                    <th>Start Station</th>
                    <th>End Station</th>
                    <th>Price</th>
                    <th>Driver Name</th>
                    <th>Seat Capacity</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${trips}" var="t">
                    <tr>
                        <td>${t.id}</td>
                        <td>${t.departureTime}</td>
                        <td>${t.arrivalTime}</td>
                        <td>${t.routeName}</td>
                        <td>${t.startStation}</td>
                        <td>${t.endStation}</td>
                        <td>${t.price}</td>
                        <td>${t.driverName}</td>
                        <td>${t.seatCapacity}</td>
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