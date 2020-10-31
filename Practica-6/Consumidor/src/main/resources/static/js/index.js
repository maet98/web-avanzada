
var chart = new Highcharts.Chart({
       chart: {
           renderTo: 'container',
           type: 'line',
           with: 500
       },
       title: {
           text: 'All Temperatures and Humidities'
       },
       tooltip: {
           valueSuffix: " c"
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
       series: [
           {
               name: "Humidity Sensor 1",
               data: [],
               tooltip: {
                   valueSuffix: " %"
               },
           },
           {
               name: "Temperatures Sensor 1",
               data: [],
               tooltip: {
                   valueSuffix: " °C"
               },

           },
           {
               name: "Humidity Sensor 2",
               data: [],
               tooltip: {
                   valueSuffix: " %"
               },
           },
           {
               name: "Temperatures Sensor 2",
               data: [],
               tooltip: {
                   valueSuffix: " °C"
               },

           }
       ]
   }
);


var chart1 = new Highcharts.Chart({
    chart: {
        renderTo: 'container1',
        type: 'line',
        with: 500
    },
    title: {
        text: 'Temperatures and Humidities sensor 1'
    },
    tooltip: {
        valueSuffix: " c"
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
    series: [
        {
            name: "Humidity",
            data: [],
            tooltip: {
                valueSuffix: " %"
            },
        },
        {
            name: "Temperatures",
            data: [],
            tooltip: {
                valueSuffix: " °C"
            },

            }
        ]
    }
);


var chart2 = new Highcharts.Chart({
    chart: {
        renderTo: 'container2',
        type: 'line',
        with: 500
    },
    title: {
        text: 'Temperatures and Humidities sensor 2'
    },
    tooltip: {
        valueSuffix: " c"
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
    series: [
        {
            name: "Humidity",
            data: [],
            tooltip: {
                valueSuffix: " %"
            },
        },
        {
            name: "Temperatures",
            data: [],
                tooltip: {
                    valueSuffix: " °C"
                },
        }]
    }
);

var humidityGeneralSensor1 = chart.series[0];
var temperatureGeneralSensor1 = chart.series[1];
var humidityGeneralSensor2 = chart.series[2];
var temperatureGeneralSensor2 = chart.series[3];


var humiditySensor1 = chart1.series[0];
var temperaturSensor1 = chart1.series[1];


var humiditySensor2 = chart2.series[0];
var temperaturSensor2 = chart2.series[1];


function updateChart(parsedData) {
    var date = new Date(parsedData.fechaGeneracion);
    if (parsedData.idDispositivo === 1) {
        humidityGeneralSensor1.addPoint({x: date, y: parsedData.humedad});
        humiditySensor1.addPoint({x: date, y: parsedData.humedad});
        temperatureGeneralSensor1.addPoint({x: date, y: parsedData.temperatura});
        temperaturSensor1.addPoint({x: date, y: parsedData.temperatura});
    } else if (parsedData.idDispositivo === 2){
        humidityGeneralSensor2.addPoint({x: date, y: parsedData.humedad});
        humiditySensor2.addPoint({x: date, y: parsedData.humedad});
        temperatureGeneralSensor2.addPoint({x: date, y: parsedData.temperatura});
        temperaturSensor2.addPoint({x: date, y: parsedData.temperatura});
    }
}

var stompClient;

function connect() {
    var socket = new SockJS("/practica6")
    stompClient = Stomp.over(socket);
    stompClient.connect({},function(frame) {
        console.log("Connected");
        stompClient.subscribe('/topic/data', function (data) {
            updateChart(JSON.parse(data.body));
        })
    })
}

connect()

