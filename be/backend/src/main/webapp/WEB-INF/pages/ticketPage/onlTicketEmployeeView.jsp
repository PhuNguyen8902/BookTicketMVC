<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:url value="" var="action"/>
<nav>
    <div class="container">
        <h1>Ticket Page</h1>

        <div class="d-flex">
            <form class="row" action="${action}">
                <div class="row-auto mb-2">
                    <input class="form-control me-2" type="text" name="kw" placeholder="Nhập tên">
                </div>
                <div class="row-auto">
                    <div class="form-check form-switch mb-2">
                        <label class="form-check-label" for="isGetKw">Tickets haven't been taken.</label>
                        <input class="form-check-input" type="checkbox" name="isGetKw" id="isGetKw" value="0">
                    </div>
                </div>
                <div class="row-auto">
                    <button class="btn btn-primary" type="submit">Tìm</button>
                </div>
            </form>
        </div>
        <table class="table">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Seat</th>
                    <th>Route</th>
                    <th>Departure Time</th>
                    <th>Arrival Time</th>
                    <th>Date</th>
                    <th>Price</th>
                    <th>Event</th>
                    <th>Payment</th>
                    <th>Employee Name</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${tickets}" var="t">
                    <tr>
                        <td>${t.id}</td>
                        <td>${t.userName}</td>
                        <td>${t.seat}</td>
                        <td>${t.route}</td>
                        <td>${t.departureTime}</td>
                        <td>${t.arrivalTime}</td>
                        <td>${t.date}</td>
                        <td>${t.price}</td>
                        <td>${t.increasePrice}</td>
                        <td>${t.payment}</td>
                        <td>${t.employee}</td>
                        <td>
                            <c:choose>
                                <c:when test="${t.isGet == '0'}">
                                    <a href="onlTicket/confirm/${t.id}" class="btn btn-info">Get Ticket</a>
                                </c:when>
                            </c:choose>
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