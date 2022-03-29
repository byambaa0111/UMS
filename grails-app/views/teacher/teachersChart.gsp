<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'teacher.label', default: 'Teacher')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<section class="section">
    <!--page-header open-->
    <div class="page-header">
        <h1 class="page-title"></h1>
        <ol class="breadcrumb">
            <li class="breadcrumb-item active" aria-current="page">
                <a class="text-light-color" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
            </li>
            <li class="breadcrumb-item active" aria-current="page">
                <g:link class="text-light-color" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link>
            </li>
        </ol>
    </div>
    <!--page-header closed-->
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-header">
                    <h4><g:message code="default.char.label" args="[entityName]" default="Charts"/></h4>
                </div>

                <div class="card-body">
                    <!--row open-->
                    <div class="row">
                        <div class="col-12 col-md-12 col-lg-6">
                            <div class="card overflow-hidden">
                                <div class="card-header">
                                    <h4>Багшийн хүйс</h4>
                                </div>
                                <div class="card-body">
                                    <canvas id="pieChartGender" class="h-400 overflow-hidden"></canvas>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-md-6 col-lg-6">
                            <div class="card overflow-hidden">
                                <div class="card-header">
                                    <h4>Багшийн тоо(Боловсролын зэргээс)</h4>
                                </div>
                                <div class="card-body">
                                    <canvas id="pieChartDegree" class="h-400 overflow-hidden"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--row open-->
                    <div class="row">
                        <div class="col-12 col-md-12 col-lg-6">
                            <div class="card overflow-hidden">
                                <div class="card-header">
                                    <h4>Багшийн тоо(Албан тушаалаар)</h4>
                                </div>
                                <div class="card-body">

                                    <canvas id="piechartByTeacherType" class="h-400 overflow-hidden"></canvas>

                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-md-6 col-lg-6">
                            <div class="card overflow-hidden">
                                <div class="card-header">
                                    <h4>Багшийн тоо(Ажиллах хэлбэрээр)</h4>
                                </div>
                                <div class="card-body">
                                    <canvas id="doughChartJobType" class="h-400 overflow-hidden"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 ">
                            <div class="card overflow-hidden">
                                <div class="card-header">
                                    <h4>Багшийн тоо (Салбар сургуулиар)</h4>
                                </div>
                                <div class="card-body">

                                    <canvas id="lineChartFaculty" class="h-400 overflow-hidden"></canvas>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 ">
                            <div class="card overflow-hidden">
                                <div class="card-header">
                                    <h4>Багшийн тоо (Хөтөлбөрийн багаар)</h4>
                                </div>
                                <div class="card-body">

                                    <canvas id="barChartByDepartments" class="h-400 overflow-hidden"></canvas>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 ">
                            <div class="card overflow-hidden">
                                <div class="card-header">
                                    <h4>Багшийн тоо (Хөтөлбөрийн багаар)</h4>
                                </div>
                                <div class="card-body">

                                    <canvas id="barChartByLanguages" class="h-400 overflow-hidden"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!--row closed-->
                </div>
            </div>
        </div>
    </div>
</section>
<script type="application/javascript">
    $(document).ready(function loadData()
    {
        $.ajax({
            url:"<g:createLink url="[action:'chart',controller:'teacher']" />",
            dataType: "json",
            type:"post",
            data: [],
            success: function(data) {
                console.log("name value fair1"+JSON.stringify(data));
                var dataTeacherType  =  data['teacherType'];
                var dataByDepartment =  data['byDepartment'];
                var dataByLanguages  =  data['byLanguages'];

                var dataByJobType    =  data['jobType'];
                var dataByGender     =  data['byGender'];
                var dataByDegree     =  data['byDegree'];

                var dataByFaculty    =  data['byFaculties'];

                var labels = []
                var series = []

                dataTeacherType.forEach(element =>{
                        labels.push(element[0]);
                        series.push(element[1])
                    }
                );



                /* -----------------line chart-----------------------------------------------*/
                //[MTDS, Ахлах Багш, 14], [MTDS, Дадлагажигч Багш, 8], [MTDS, Профессор багш, 37],
                const mapByFaculty = new Map()

                var labels4 = []
                var series4 = []

                const serMap1 = new Map([["Дадлагажигч Багш",0],["Багш",0],["Ахлах Багш",0],["Профессор багш",0],["Дэд Профессор багш",0]])  //Дадлагажигч Багш
                const serMap2 = new Map([["Дадлагажигч Багш",0],["Багш",0],["Ахлах Багш",0],["Профессор багш",0],["Дэд Профессор багш",0]])    //Багш
                const serMap3 = new Map([["Дадлагажигч Багш",0],["Багш",0],["Ахлах Багш",0],["Профессор багш",0],["Дэд Профессор багш",0]])  //Ахлах Багш
                const serMap4 = new Map([["Дадлагажигч Багш",0],["Багш",0],["Ахлах Багш",0],["Профессор багш",0],["Дэд Профессор багш",0]])  //Профессор багш
                const serMap5 = new Map([["Дадлагажигч Багш",0],["Багш",0],["Ахлах Багш",0],["Профессор багш",0],["Дэд Профессор багш",0]])  //Дэд Профессор багш

                dataByFaculty.forEach(element =>{

                        switch(element[1]) {
                            case "Дадлагажигч Багш":
                                serMap1.set("Дадлагажигч Багш",element[2])
                                break;
                            case "Багш":
                                serMap2.set("Багш",element[2]) // code block
                                break;
                            case "Ахлах Багш":
                                serMap3.set("Ахлах Багш",element[2]) // code block// code block
                                break;
                            case "Профессор багш":
                                serMap4.set("Профессор багш",element[2]) // code block// code block
                                break;
                            case "Дэд Профессор багш":
                                serMap5.set("Дэд Профессор багш",element[2]) // code block// code block
                                break;
                            default:
                            // code block
                        }
                 console.log("======"+Array.from(serMap1.values()));
                if(!mapByFaculty.has(element[0])){
                    mapByFaculty.set(element[0],element[2])
                    }else{
                    mapByFaculty.set(element[0],mapByFaculty.get(element[0])+element[2])
                    }
                }

                );
                labels4 = Array.from(mapByFaculty.keys())
                series4 = Array.from(mapByFaculty.values())


                var ctx = document.getElementById("lineChartFaculty").getContext('2d');
                var myChart = new Chart(ctx, {
                    type: 'line',
                    data: {
                        labels: labels4,
                        datasets: [{
                            label: 'Нийт багш салбараар',
                            data: series4,
                            backgroundColor: 'transparent',
                            borderColor: 'rgb(118, 115, 230)',
                            borderWidth: 2,
                            pointBackgroundColor: '#ffffff',
                            pointRadius: 8
                        },{
                            label: 'Дадлагажигч Багш',
                            data: Array.from(serMap1.values()),
                            backgroundColor: 'transparent',
                            borderColor: 'rgb(118, 115, 20)',
                            borderWidth: 2,
                            pointBackgroundColor: '#ffffff',
                            pointRadius: 8
                        },
                            {
                                label: 'Багш',
                                data: Array.from(serMap2.values()),
                                backgroundColor: 'transparent',
                                borderColor: 'rgb(50, 50, 25)',
                                borderWidth: 2,
                                pointBackgroundColor: '#ffffff',
                                pointRadius: 8
                            },
                            {
                                label: 'Ахлах Багш',
                                data: Array.from(serMap3.values()),
                                backgroundColor: 'transparent',
                                borderColor: 'rgb(80, 115, 100)',
                                borderWidth: 2,
                                pointBackgroundColor: '#ffffff',
                                pointRadius: 8
                            },
                            {
                                label: 'Профессор багш',
                                data: Array.from(serMap4.values()),
                                backgroundColor: 'transparent',
                                borderColor: 'rgb(18, 115, 60)',
                                borderWidth: 2,
                                pointBackgroundColor: '#ffffff',
                                pointRadius: 8
                            },
                            {
                                label: 'Дэд Профессор багш',
                                data: Array.from(serMap5.values()),
                                backgroundColor: 'transparent',
                                borderColor: 'rgb(118, 15, 70)',
                                borderWidth: 2,
                                pointBackgroundColor: '#ffffff',
                                pointRadius: 8
                            },
                        ],


                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        legend: {
                            display:true
                        },
                        scales: {
                            yAxes: [{
                                ticks: {
                                    beginAtZero: true,
                                }
                            }],

                            xAxes: [{
                                ticks: {
                                    display: true,
                                    autoSkip: false
                                },
                                beginAtZero: true,

                                gridLines: {
                                    display: false
                                }
                            }]
                        },
                    }
                });
                /********************* PIE CHART DEGREE *********************/
                var labelsDegree = []
                var seriesDegree = []

                dataByDegree.forEach(element =>{
                        labelsDegree.push(element[0]);
                        seriesDegree.push(element[1])
                    }
                );
                var ctx = document.getElementById("pieChartDegree").getContext('2d');
                var myChart = new Chart(ctx, {
                    type: 'pie',
                    data: {
                        datasets: [{
                            data: seriesDegree,
                            backgroundColor: ['#f47b25', '#7673e6', '#b03cd5', '#31c92e'],
                            label: 'Dataset 1'
                        }],
                        labels: labelsDegree,
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        legend: {
                            position: 'bottom',
                        },
                    }
                });
                /********************* PIE CHART ЖЕНДЭР *********************/
                var labelsGender = []
                var seriesGender = []
                dataByGender.forEach(element =>{

                        labelsGender.push(element[0]);
                        seriesGender.push(element[1])
                    }
                );
                var ctx = document.getElementById("pieChartGender").getContext('2d');
                var myChart = new Chart(ctx, {
                    type: 'pie',
                    data: {
                        datasets: [{
                            data: seriesGender,
                            backgroundColor: ['#f47b25', '#7673e6', '#b03cd5', '#31c92e'],
                            label: 'Dataset 1'
                        }],
                        labels: labelsGender,
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        legend: {
                            position: 'bottom',
                        },
                    }
                });
                /********************* PIE CHART *********************/
                var ctx = document.getElementById("piechartByTeacherType").getContext('2d');
                var myChart = new Chart(ctx, {
                    type: 'pie',
                    data: {
                        datasets: [{
                            data: series,
                            backgroundColor: ['#f47b25', '#7673e6', '#b03cd5', '#31c92e'],
                            label: 'Dataset 1'
                        }],
                        labels: labels,
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        legend: {
                            position: 'bottom',
                        },
                    }
                });
                /********************* PIE2 CHART *********************/
                var labelsByJobType = []
                var seriesByJobType = []
                dataByJobType.forEach(element =>{
                        labelsByJobType.push(element[0]);
                        seriesByJobType.push(element[1])
                    }
                );
                /***********************Doughchart by department********************************/
                var ctx = document.getElementById("doughChartJobType").getContext('2d');
                var myChart = new Chart(ctx, {
                    type: 'doughnut',
                    data: {
                        datasets: [{
                            data: seriesByJobType,
                            backgroundColor: ['#f47b25', '#7673e6', '#b03cd5', '#31c92e'],
                            label: 'Dataset 1'
                        }],
                        labels:labelsByJobType,
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        legend: {
                            position: 'bottom',
                        },
                    }
                });


/*****************************Bar chart by department **************************************/
                var labels2 = []
                var series2 = []
                dataByDepartment.forEach(element =>{
                        labels2.push(element[0]);
                        series2.push(element[1])
                    }
                );
                var ctx = document.getElementById("barChartByDepartments").getContext('2d');
                var myChart = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels:labels2,
                        datasets: [{
                            label: 'By Departments',
                            data: series2,
                            backgroundColor: 'rgb(118, 115, 230)',
                            borderColor: 'rgb(118, 115, 230)',
                            borderWidth: 2.0,
                            pointBackgroundColor: '#ffffff',
                            pointRadius: 4
                        }]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        legend: {
                            display: true
                        },
                        scales: {
                            yAxes: [{
                                ticks: {
                                    beginAtZero: true,
                                }
                            }],

                            xAxes: [{
                                ticks: {
                                    display: true,
                                    autoSkip: false
                                },
                                beginAtZero: true,

                                gridLines: {
                                    display: true
                                }
                            }]
                        },
                    }
                });

/*****************************Bar chart by languages **************************************/
                var labelsByLanguages = []
                var seriesByLanguages = []
                dataByLanguages.forEach(element =>{
                    labelsByLanguages.push(element[0]);
                    seriesByLanguages.push(element[1])
                    }
                );
                var ctx = document.getElementById("barChartByLanguages").getContext('2d');
                var myChart = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels:labelsByLanguages,
                        datasets: [{
                            label: 'By Languages',
                            data: seriesByLanguages,
                            backgroundColor: 'rgb(115,125,230)',
                            borderColor: 'rgb(118, 115, 230)',
                            borderWidth: 2.0,
                            pointBackgroundColor: '#ffffff',
                            pointRadius: 4
                        }]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        legend: {
                            display: true
                        },
                        scales: {
                            yAxes: [{
                                ticks: {
                                    beginAtZero: true,
                                }
                            }],

                            xAxes: [{
                                ticks: {
                                    display: true,
                                    autoSkip: false
                                },
                                beginAtZero: true,

                                gridLines: {
                                    display: true
                                }
                            }]
                        },
                    }
                });
                console.log("fetched data"+data);
                return data;
            },
            error: function() {
                alert('Error occured');
            }
        });
    });
</script>
</body>
</html>
a