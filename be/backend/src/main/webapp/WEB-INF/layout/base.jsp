

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <tiles:insertAttribute name="title" />
        </title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
              rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
              crossorigin="anonymous">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" 
        crossorigin="anonymous"></script>
        <link rel="stylesheet" type="text/css" href="<c:url value="css/style.css"/>">
        <script src="<c:url value="js/pagination.js"/>"></script>
        <script src="<c:url value="js/fetchData.js"/>"></script>
    </head>
    <body class="height" onload="fetchInfo()">
        <div class="row height">
            <div class="col-2 height ">
                <tiles:insertAttribute name="sideBar" />
            </div>
            <div class="col-10 ">
                <%--<tiles:insertAttribute name="header" />--%>
                <div class="container mt-4">
                    <tiles:insertAttribute name="content" />
                </div>
                <%--<tiles:insertAttribute name="footer" />--%>
            </div>
        </div>
    </body>
</html>