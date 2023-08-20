<%@page contentType="text/html" pageEncoding="UTF-8"%>
<canvas id="densityChart" width="400" height="200"></canvas>
<select id="yearSelector">
    <option value="2021">2021</option>
    <option value="2022">2022</option>
</select>
<script>
    var yearSelector = document.getElementById('yearSelector');
    yearSelector.addEventListener('change', function() {
        console.log(yearSelector.value);
    });

//        var params = new URLSearchParams();
//        params.set('year', yearSelector.value);

        $.ajax({
            url: "data-provider",
            type: "GET",
            dataType: "json",
            success: function(data) {
                var ctx = document.getElementById('densityChart').getContext('2d');

                var nameRouteList = [];
                var amountRouteList = [];

                for (var i = 0; i < data.length; i++) {
                    nameRouteList.push(data[i].route.name);
                    amountRouteList.push(data[i].amount);
                }

                var densityChart = new Chart(ctx, {
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
</script>
