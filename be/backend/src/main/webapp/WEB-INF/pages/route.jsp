<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <nav aria-label="Page navigation example " class="d-flex justify-content-center">
            <ul class="pagination">
                <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                    <c:forEach begin="1" end="${totalPage}" var="total"> 
                    <li class="page-item"><button class="page-link" onclick="pagination('${total}')">${total}</button></li>
                    </c:forEach>
                <li class="page-item"><a class="page-link" href="#">Next</a></li>
            </ul>
        </nav>

    </div>

</nav>