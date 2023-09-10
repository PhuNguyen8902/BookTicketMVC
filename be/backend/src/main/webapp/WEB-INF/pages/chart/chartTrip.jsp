<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--<div style="width: 100%;overflow: hidden">
    <canvas id="densityChart" width="400" height="200"></canvas>
</div>-->
<div style="display: flex;">
    <div style="flex: 1.5;">
        <canvas id="densityChart" width="400" height="200"></canvas>
    </div>
    <div id="tableContainer" style="flex: 0.5 ;">
        <table id="dataTable" class="table">
            <thead>
                <tr>
                    <th>Year</th>
                    <th>Route</th>
                    <th>Amount</th>
                </tr>
            </thead>
            <tbody>
<!--                <tr>
                    <td rowspan="3">2021</td>
                    <td>Tuyến 1</td>
                    <td>5</td>
                </tr>
                <tr>
                    <td>Tuyến 2</td>
                    <td>6</td>
                </tr>
                <tr>
                    <td>Tuyến 3</td>
                    <td>1</td>
                </tr>-->

            </tbody>
        </table>
    </div>
</div>

<div class="d-flex justify-content-around mb-3">
    <select class="form-select" aria-label="Default select year"id="yearSelector">
        <option value ="0" selected>Open this select year</option>
        <option value="2021">2021</option>
        <option value="2022">2022</option>
        <option value="2023">2023</option>
    </select>
    <select class="form-select" aria-label="Default select month"id="monthSelector" style="display: none;">
        <option value ="0" selected>Open this select month</option>
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
        <option value="6">6</option>
        <option value="7">7</option>
        <option value="8">8</option>
        <option value="9">9</option>
        <option value="10">10</option>
        <option value="11">11</option>
        <option value="12">12</option>
    </select>
    <select class="form-select" aria-label="Default select quarter"id="quarterSelector"style="display: none;">
        <option value ="0" selected>Open this select quarter</option>
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>

    </select>
</div>
<button id="submit" class="btn btn-success d-flex" style="margin-left: 50%">
    Submit
</button>
<script>
    let year = "";
    let month = "";
    let quarter = "";
    var params = new URLSearchParams();
    var yearSelector = document.getElementById('yearSelector');
    var monthSelector = document.getElementById('monthSelector');
    var quarterSelector = document.getElementById('quarterSelector');

    yearSelector.addEventListener('change', function () {
        year = yearSelector.value;
        if (year == "0") {
            monthSelector.style.display = "none";
            month = 0;
            quarterSelector.style.display = "none";
            quarter = 0;
        } else {
            monthSelector.style.display = "inline-block";
            monthSelector.value = 0;
            month = 0;
            quarterSelector.style.display = "inline-block";
            quarterSelector.value = 0;
            quarter = 0;

        }
    });
    monthSelector.addEventListener('change', function () {
        month = monthSelector.value;
        if (month == "0") {
            quarterSelector.style.display = "inline-block";
            quarterSelector.value = 0;

            month = 0;

        } else {
            quarterSelector.style.display = "none";

        }
    });
    quarterSelector.addEventListener('change', function () {
        quarter = quarterSelector.value;
        if (quarter == "0") {
            monthSelector.style.display = "inline-block";
            monthSelector.value = 0;

            quarter = 0;

        } else {
            monthSelector.style.display = "none";

        }
    });
    var submitBtn = document.getElementById('submit');
    submitBtn.addEventListener('click', function () {
        if (month == 0) {
            params.delete("month");
        } else {
            params.set("month", month);
        }
        if (quarter == 0) {
            params.delete("quarter");
        } else {
            params.set("quarter", quarter);
        }
        if (year == 0) {
            params.delete("year");
        } else {
            params.set("year", year);
        }
        var newUrl = window.location.pathname + '?' + params.toString();
        window.history.replaceState({}, '', newUrl);
        updateChart();
    });
</script>
<script src="<c:url value="/js/chart.js"/>"></script>
