<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:url value="" var="action"/>
<nav>
    <div class="container">
        <h1>Increased Price Page</h1>
        <div class="d-flex">
            <button class="btn btn-success"><a href="/backend/admin/increasedPrice/add">Add Increased Price</a></button>
            <form class="d-flex" action="${action}">
                <input class="form-control me-2" type="text" name="kw" placeholder="Nhập từ khóa...">
                <button class="btn btn-primary" type="submit">Tìm</button>
            </form>
        </div>
        <table class="table">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Event Name</th>
                    <th>Increased Percentage</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${increasedPrice}" var="i">
                    <tr>
                        <td>${i.id}</td>
                        <td>${i.eventName}</td>
                        <td>${i.increasedPercentage}</td>
                        <td>${i.startDate}</td>
                        <td>${i.endDate}</td>
                        <td>
                            <c:url value="/admin/increasedPrice/${i.id}" var="api" />
                            <a href="${api}" class="btn btn-success">Update</a>
                            <button class="btn btn-danger" onclick="deleteIncreasedPrice('/backend/api/increasedPrice/${i.id}', '${i.eventName}')">Delete</button>
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