<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:url value="" var="action"/>
<nav>
    <div class="container">
        <h1 >Route Page</h1>
        <table class="table">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Start Station</th>
                    <th>End Station</th>
                    <th>Distance</th>
                    <th>Duration</th>

                </tr>
            </thead>
            <tbody>
                <c:forEach items="${route}" var="r">
                    <tr>
                        <td>${r.id}</td>
                        <td>${r.name}</td>
                        <td>${r.startStation}</td>
                        <td>${r.endStation}</td>
                        <td>${r.distance} (km)</td>
                        <td>${r.duration} (h)</td>
                        <td>
                            <c:url value="/route/${r.id}" var="api" />
                            <a href="${api}" class="btn btn-success">Update</a>
                            <button class="btn btn-danger" onclick="deleteProduct('${api}')">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <nav aria-label="Page navigation example " class="d-flex justify-content-center">
            <ul class="pagination">
                <c:forEach begin="1" end="${totalPage}" var="total"> 
                    <li class="page-item">
                        <c:url value="${action}" var="pageUrl">
                            <c:param name="page" value="${total}" />
                        </c:url>
                        <!--<button class="page-link" onclick="pagination('${total}')">${total}</button>-->
                        <a class="page-link" href="${pageUrl}">${total}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>

    </div>

</nav>