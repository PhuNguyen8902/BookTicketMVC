<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="width: 100%;overflow: hidden">
    <canvas id="densityChart2" width="400" height="200"></canvas>
</div>
<div class="d-flex justify-content-around mb-3">
    <select class="form-select" aria-label="Default select year"id="yearSelector">
        <option value ="0" selected>Open this select year</option>
        <option value="2021">2021</option>
        <option value="2022">2022</option>
        <option value="2023">2023</option>
    </select>
    <select class="form-select" aria-label="Default select quarter"id="typeSelector"style="display: none;">
        <option value ="0" selected>Open this select type</option>
        <option value="1">Month</option>
        <option value="2">Quarter</option>
    </select>
</div>
<button id="submit" class="btn btn-success d-flex" style="margin-left: 50%">
    Submit
</button>
<script>
    let year = "";
    let type = "";
    var params = new URLSearchParams();
    var yearSelector = document.getElementById('yearSelector');
    var typeSelector = document.getElementById('typeSelector');

    yearSelector.addEventListener('change', function () {
        year = yearSelector.value;
        if (year === "0") {
            typeSelector.style.display = "none";
            type = 0;
        } else {
            typeSelector.style.display = "inline-block";
            typeSelector.value = 0;
            type = 0;
        }
    });
    typeSelector.addEventListener('change', function () {
        type = typeSelector.value;
    });
    var submitBtn = document.getElementById('submit');
    submitBtn.addEventListener('click', function () {
        if (year == 0) {
            params.delete("year");
        } else {
            params.set("year", year);
        }
        var newUrl = window.location.pathname + '?' + params.toString();
        window.history.replaceState({}, '', newUrl);
        if (type == 2) {
            updateChartRevenue("quarter");
        } else {
            if (year != 0) {
                updateChartRevenue("year");
            } else {
                updateChartRevenue();
            }
        }
    });
</script>
<script src="<c:url value="/js/chart.js"/>"></script>
