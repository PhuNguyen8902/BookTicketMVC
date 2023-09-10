var densityChart = null;
var url = window.location.origin;
function updateChart() {

    $.ajax({
        url: `${url}/backend/admin/chart/trip/data?` + params.toString(),
        type: "GET",
        dataType: "json",
        success: function (data) {
            var ctx = document.getElementById('densityChart').getContext('2d');
            var nameRouteList = [];
            var amountRouteList = [];
            var tableBody = document.getElementById('dataTable').getElementsByTagName('tbody')[0];
            tableBody.innerHTML = "";
//            var currentYear = null;

            if (data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    nameRouteList.push(data[i].route.name);
                    amountRouteList.push(data[i].amount);
                    var rowData = data[i];
                    var yearRow = tableBody.insertRow();
                    var yearCell = yearRow.insertCell();
                    yearCell.textContent = params.get("year");
                    var routeCell = yearRow.insertCell();
                    routeCell.textContent = rowData.route.name;
                    var amountCell = yearRow.insertCell();
                    amountCell.textContent = rowData.amount;
                }
            }

            if (densityChart) {
                densityChart.destroy();
            }
            densityChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: nameRouteList,
                    datasets: [{
                            label: 'Density',
                            data: amountRouteList,
                            backgroundColor: 'rgba(75, 192, 192, 0.2)',
                            borderColor: 'rgba(75, 192, 192, 1)',
                            borderWidth: 1
                        }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        }
    });
}


var densityChart2 = null;
function updateChartRevenue(type) {
    $.ajax({
        url: `${url}/backend/admin/chart/revenue/data?` + params.toString(),
        type: "GET",
        dataType: "json",
        success: function (data) {
            var ctx = document.getElementById('densityChart2').getContext('2d');
            let listName = [];
            let listAmount = [];
            var tableBody2 = document.getElementById('dataTable2').getElementsByTagName('tbody')[0];
            tableBody2.innerHTML = "";
            var tableHead2 = document.getElementById('dataTable2').getElementsByTagName('thead')[0];
            tableHead2.innerHTML = "";
            if (type == null) {
                let listYearOfTable = [];

                var tableHead2 = document.getElementById('dataTable2').getElementsByTagName('thead')[0];
                tableHead2.innerHTML = `
        <tr>
            <th>Year</th>
            <th>Revenue</th>
        </tr>
    `;
                for (let i = 0; i < data.length; i++) {
                    var rowData = data[i];
                    let date = new Date(data[i].name);
                    let year = date.getFullYear();
                    let price = data[i].amount;
                    let yearIndex = listName.indexOf(year);
                    if (!listName.includes(year)) {
                        listName.push(year);
                        listAmount.push(price);
                    } else {
                        listAmount[yearIndex] += price;
                    }

                    var tableBody2 = document.getElementById('dataTable2').getElementsByTagName('tbody')[0];
                    if (!listYearOfTable.includes(year)) {
                        listYearOfTable.push(year);
                        var yearRow = tableBody2.insertRow();
                        var yearCell = yearRow.insertCell();
                        yearCell.textContent = year;
                        var priceCell = yearRow.insertCell();
                        priceCell.textContent = price;
                    } else {

                        var rows = tableBody2.getElementsByTagName('tr');
                        for (var j = 0; j < rows.length; j++) {
                            var cells = rows[j].getElementsByTagName('td');
                            if (cells.length > 0 && cells[0].textContent === year.toString()) {
                                var existingPriceCell = cells[1];
                                var existingValue = parseFloat(existingPriceCell.textContent) || 0;
                                var newValue = existingValue + price;
                                existingPriceCell.textContent = newValue;

                                break;
                            }
                        }

                    }
                }
            } else if (type == "year") {

                var tableHead2 = document.getElementById('dataTable2').getElementsByTagName('thead')[0];
                tableHead2.innerHTML = `
        <tr>
            <th>Month</th>
            <th>Revenue</th>
        </tr>
    `;
                listName = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
                listAmount = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
                for (let i = 0; i < data.length; i++) {
                    let date = new Date(data[i].name);
                    let month = date.getMonth();
                    let price = data[i].amount;
                    listAmount[month] += price;
                }

                for (let i = 0; i < listName.length; i++) {
                    var newRow = tableBody2.insertRow();
                    var newCell = newRow.insertCell();
                    var newCell2 = newRow.insertCell();
                    newCell.textContent = listName[i];
                    newCell2.textContent = listAmount[i];

                }

            } else if (type == "quarter") {
                var tableHead2 = document.getElementById('dataTable2').getElementsByTagName('thead')[0];
                tableHead2.innerHTML = `
        <tr>
            <th>Quarter</th>
            <th>Revenue</th>
        </tr>
    `;

                listName = [1, 2, 3, 4];
                listAmount = [0, 0, 0, 0];
                for (let i = 0; i < data.length; i++) {
                    let date = new Date(data[i].name);
                    let month = date.getMonth() + 1;
                    console.log(month);
                    let quarter;
                    if (month >= 0 && month <= 2) {
                        quarter = 1;
                    } else if (month >= 3 && month <= 5) {
                        quarter = 2;
                    } else if (month >= 6 && month <= 8) {
                        quarter = 3;
                    } else {
                        quarter = 4;
                    }
                    let price = data[i].amount;
                    listAmount[quarter - 1] += price;
                 
                }
                   for (let i = 0; i < listName.length; i++) {
                        var newRow = tableBody2.insertRow();
                        var newCell = newRow.insertCell();
                        var newCell2 = newRow.insertCell();
                        newCell.textContent = listName[i];
                        newCell2.textContent = listAmount[i];

                    }
            }
            if (densityChart) {
                densityChart.destroy();
            }
            densityChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: listName,
                    datasets: [{
                            label: 'Density',
                            data: listAmount,
                            backgroundColor: 'rgba(75, 192, 192, 0.2)',
                            borderColor: 'rgba(75, 192, 192, 1)',
                            borderWidth: 1
                        }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        }
    });
}

updateChart();
updateChartRevenue(null);