<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:url value="" var="action"/>
<nav>
    <div class="container">
        <h1 >Address Page</h1>
        <div class="d-flex">
            <button class="btn btn-success"><a href="/backend/admin/address/add">Add Address</a></button>
            <form class="d-flex" action="${action}">
                <input class="form-control me-2" type="text" name="kw" placeholder="Nhập từ khóa...">
                <button class="btn btn-primary" type="submit">Tìm</button>
            </form>
        </div>
        <table class="table">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Town</th>
                    <th>District</th>
                    <th>Ward</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${address}" var="v">
                    <tr>
                        <td>${v.id}</td>
                        <td>${v.town}</td>
                        <td>${v.district}</td>
                        <td>${v.ward}</td>
                        <td>
                            <a href="address/${v.id}" class="btn btn-success">Update</a>
                            <button class="btn btn-danger" onclick="deleteAddress('/backend/admin/address/delete/${v.id}', ${v.id})">Delete</button>
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