<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'teacher.label', default: 'Student')}"/>
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
                                    <h4>Суралцагсад (курсээр)</h4>
                                </div>
                                <div class="card-body">
                                    <canvas id="pchByYearOf" class="h-400 overflow-hidden"></canvas>
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


                </div>
            </div>
        </div>
    </div>
</section>
<script type="application/javascript">
    $(document).ready(function loadData()
    {
        $.ajax({
            url:"<g:createLink url="[action:'chart',controller:'student']" />",
            dataType: "json",
            type:"post",
            data: [],
            success: function(data) {
                console.log("name value fair1"+JSON.stringify(data));
                var dataByYearOf  =  data['byYearOf'];
/***************************************************** PIE CHART КУРС *********************/
                var ctx = document.getElementById("pchByYearOf").getContext('2d');
                var lYearOf = []
                var sYearOf = []
                dataByYearOf.forEach(element =>{

                        lYearOf.push(element[0]);
                        sYearOf.push(element[1])
                    }
                );
                var myChart = new Chart(ctx, {
                    type: 'pie',
                    data: {
                        datasets: [{
                            data: sYearOf,
                            backgroundColor: ['#f47b25', '#7673e6', '#b03cd5', '#31c92e'],
                            label: 'Year of'
                        }],
                        labels: lYearOf,
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