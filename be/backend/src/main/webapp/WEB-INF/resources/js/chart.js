
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

            for (var i = 0; i < data.length; i++) {
                nameRouteList.push(data[i].route.name);
                amountRouteList.push(data[i].amount);
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

            if (type == null) {
                for (let i = 0; i < data.length; i++) {
                    let date = new Date(data[i].name);
                    let year = date.getFullYear();

                    if (!listName.includes(year)) {
                        listName.push(year);
                    }
                    listAmount.push(data[i].amount);
                }
            } else if (type == "year") {
                listName = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
                for (let i = 0; i < listName.length; i++) {
                    let month = listName[i];
                    let correspondingData = data.find(item => {
                        let date = new Date(item.name);
                        return date.getMonth() + 1 === month; 
                    });

                    if (correspondingData) {
                        listAmount.push(correspondingData.amount);
                    } else {
                        listAmount.push(0);
                    }
                }
            } else if (type == "quarter") {
                listName = [1, 2, 3, 4];
                for (let i = 0; i < listName.length; i++) {
                    let quarter = listName[i];
                    let correspondingData = data.filter(item => {
                        let date = new Date(item.name);
                        let quarterOfDate = Math.floor(date.getMonth() / 3) + 1;
                        return quarterOfDate === quarter;
                    });

                    if (correspondingData.length > 0) {
                        let sumAmount = correspondingData.reduce((sum, item) => sum + item.amount, 0);
                        listAmount.push(sumAmount);
                    } else {
                        listAmount.push(0);
                    }
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