$.ajax({
    url:'admin/data',
    success: function(result) {
        var question = JSON.parse(result).question;
        var data = JSON.parse(result).series;
        drawGraph(question,data)
    }
})

function drawGraph(questions,data ){
    console.log(data)
    Highcharts.chart(
        'container',
        {
            chart: {
                type: 'bar',
                with: 500
            },
            title: {
                text: 'All registered survey'
            },
            xAxis: {
                categories: questions
            },
            yAxis: {
                tickInterval: 5
            },
            tooltip: {
                valueSuffix: " votes"
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -40,
                y: 80,
                floating: true,
                borderWidth: 1
            },
            series: data
        }
    )
}
