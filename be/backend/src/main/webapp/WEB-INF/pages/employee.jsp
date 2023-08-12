<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav>
    <div class="container">
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Tên sản phẩm</th>
                    <th>Gía</th>
                    <th>Id</th>
                    <th>Tên sản phẩm</th>
                 
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${employee}" var="p">
                    <tr>
                        <td>
                            <img src="${p.avatar}" alt="${p.name}" width="120" />
                        </td>
                        <td>${p.id}</td>
                        <td>${p.name}</td>
                        <td>${p.email} </td>
                        <td>${p.phone} </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</nav>