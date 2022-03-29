<%@ page import="com.ums.edu.course.Programme; com.ums.edu.uni.Faculty" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'student.label', default: 'Student')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
    <link rel="stylesheet" href="https://cdn.datatables.net/autofill/2.3.5/css/autoFill.dataTables.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/keytable/2.5.3/css/keyTable.dataTables.min.css">
    <script src="https://cdn.datatables.net/autofill/2.3.5/js/dataTables.autoFill.min.js"></script>
    <script src="https://cdn.datatables.net/keytable/2.5.3/js/dataTables.keyTable.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>
<body>
<section class="section">
    <!--page-header open-->
    <div class="page-header">
        <h4 class="page-title"></h4>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a class="text-light-color" href="${createLink(uri: '/')}">
                <g:message code="default.home.label"/></a>
            </li>
            <li class="breadcrumb-item active" aria-current="page">
                <g:link class="text-light-color" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
            </li>
        </ol>
    </div>
    <!--page-header closed-->
    <!--row open-->
    <div class="row">
        <div class="col-md-12 col-lg-5 col-xl-4">
            <div class="card">
                <div class="card-body">
                    <g:textField id="question" name="question" placeholder="оюутны код,овог нэр, регистр "  class="form-control" value="${paymentInstance?.student?.id}"  />
                </div>
            </div>
            <div class="card">
                <div class="card-header">
                    <div class="float-right">
                        <a data-collapse="#mycard-collapse" class="btn btn-icon"><i class="ion ion-minus"></i></a>
                    </div>
                    <h4>Шүүлт хайлт:</h4>
                </div>
                <div class="collapse show" id="mycard-collapse">
                    <div class="card-body">
                        <g:form name="filterForm" id="filterForm" method="POST">

                            <g:schoolYear></g:schoolYear>
                            <div class="form-group">
                                <label class="form-label">Салбар сургууль</label>
                                <g:select class="form-control select2 w-150" name="faculty" id="faculty" noSelection="['all':'Бүгд']"
                                          from="${com.ums.edu.uni.Faculty.list()}"
                                          optionKey="id"
                                          optionValue="facultyName"
                                />
                            </div>
                            <div id="departmentDiv">
                            </div>
                            <div class="form-group" id="programmeDiv">
                            </div>
                            <div class="form-group custom-switches-stacked">
                                <label class="form-label">Сонголт</label>
                                <g:each in="${grails.util.Holders.config.getProperty("paymentType.paymentby.list",List)}" var="gender"  status="i">
                                    <label class="custom-switch">
                                        <input type="radio" name="paymentBy" id="paymentBy" value="${i}" class="custom-switch-input" onchange="" ${i == 0 ? "checked":""} >
                                        <span class="custom-switch-indicator"></span>
                                        <span class="custom-switch-description">${gender}</span>
                                    </label>
                                </g:each>
                            </div>
                            <g:toggleCheck toggleList="${grails.util.Holders.config.getProperty("student.groupOf.list",List)}" name="groupOf" title="Бүлэг"></g:toggleCheck>
                            <g:toggleCheck toggleList="${grails.util.Holders.config.getProperty("student.yearOf.list",List)}" name="yearOf" title="Курс"></g:toggleCheck>
                            <a class="btn btn-info" href="#" onclick="submitFrm('renderStudents','just fun')">Хайх</a>
                            <button id="print" type="button" class="btn btn-info"  >Хэвлэх</button>
                            <div class="btn-group dropright">
                                <button type="button" class="btn btn-primary m-b-5 m-t-5 dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Үйлдэлүүд
                                </button>
                                <div class="dropdown-menu">
                                    <a class="dropdown-item" onclick="submitFrm('renderCourseList','examPage')" href="#">Шалгалтын хуудас</a>
                                    <a class="dropdown-item" onclick="submitFrm('renderCourseList','studentsAllCourses')"  href="#">Дүнгийн хуудас</a>
                                    <a class="dropdown-item" onclick="submitFrm('renderCourseList','statistic')" href="#">Статистик хуудас</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="#">Separated link</a>
                                </div>
                            </div>
                        </g:form>
                    </div>
                </div>
            </div>

        </div>
        <div class="col-md-12 col-lg-7 col-xl-8">
            <div class="card">
                <div class="card-header">
                    <h4><g:message code="default.list.label" args="[entityName]" /></h4>
                </div>
                <div class="card-body" id="resultCourseList">

                    <div id="bchart" class="col-md-12">
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
    <!--row closed-->
</section>
<script type="application/javascript">
    $(document).ready(function() {
        $('#question').autocomplete({
            source: '<g:createLink controller='student' action='searchStudent'/>',
            select:function (event, ui) {
                let url  = '/payment/searchByStudent'
                $.ajax({
                    type: "POST",
                    url: url,
                    data: {question:this.value},
                    success: function (response) {
                        $("#resultCourseList").html(response)
                    },
                    error: function (xhr, status, error) {
                        // $("#resultCourse").addClass('alert alert-info').append(xhr.responseText);
                    }

                });
            },
        });
        $( "#question" ).autocomplete( "option", "minLength", 5 );

    });
    $("#student").keypress(function (e) {
        if (e.which == 13) {
            let url  = '/payment/searchByStudent'
            $.ajax({
                type: "POST",
                url: url,
                data: {student:this.value},
                success: function (response) {
                    $("#resultCourseList").html(response)
                },
                error: function (xhr, status, error) {
                    // $("#resultCourse").addClass('alert alert-info').append(xhr.responseText);
                }

            });

        }
    });
    $("#print").click(function () {
        $(".badge").removeClass();
        $(".fa").removeClass();

        $("#resultCourseList").printThis();
    });
    /*-------------------submit form---------------------------*/
    var frm = $("#filterForm")
    function submitFrm(action,command) {
        var url  = '/payment/'+action +'/'+command
        $.ajax({
            type: "POST",
            url: url,
            data: $("#filterForm").serialize(),
            success: function (response) {

                $("#resultCourseList").html(response)
            },
            error: function (xhr, status, error) {
                // $("#resultCourse").addClass('alert alert-info').append(xhr.responseText);
            }

        });
        //e.preventDefault();

    };
/*----------------------fuculty on change-----------------------------------------*/
    $("#faculty").on('change',function (e){
        var url ="/student/getDepartments"
        // alert(this.value)
        $.ajax({
            type: "POST",
            url: url,
            data:  { 'facultyId': this.value  }, // serializes the form's elements.
            success: function(data)
            {
                // alert(data); // show response from the php script.

                if(data == 'all'){
                    $( "#departmentDiv" ).empty() ;
                }else{
                    $( "#departmentDiv" ).html(data) ;
                }
            },
            error: function(xhr, status, error) {
                $( "#showList" ).addClass( 'alert alert-info' ).append(xhr.responseText);
            }
        });
    });
    var table
    var url = '/student/data_for_datatable';
    $(document).ready(function () {
        /*left menu hid hiij baina*/
        $("body").addClass("sidenav-toggled");
    });

    /*---------------------------------------------*/
    $(document).ready(function () {
        table = $('table').DataTable({
            "ajax": {
                "url": url,
                "type": "POST",
                "data": function(d) {
                    var frm_data = $('#filterForm').serializeArray();
                    var frm_dataAdd = $('#filterFormAdd').serializeArray();
                    var groupOfList =[] ;
                    var yearOfList =[] ;
                    var departments =[] ;
                    var educationType =[] ;
                    var degree =[] ;
                    $.each(frm_dataAdd, function(key, val) {
                        if(val.name == "departments"){
                            departments.push(val.value)
                        }else{
                            d[val.name] = val.value;
                        }
                        if(val.name == "educationType"){
                            educationType.push(val.value)
                        }else{
                            d[val.name] = val.value;
                        }
                        if(val.name == "degree"){
                            degree.push(val.value)
                        }else{
                            d[val.name] = val.value;
                        }
                    });
                    $.each(frm_data, function(key, val) {

                        if(val.name == "yearOf"){
                            yearOfList.push(val.value)
                        }else{
                            d[val.name] = val.value;
                        }

                        if(val.name == "groupOf"){
                            groupOfList.push(val.value)
                        }else{
                            d[val.name] = val.value;
                        }

                        d["departments"] = departments;
                        d["yearOf"] = yearOfList;
                        d["groupOf"] = groupOfList;

                        console.log('Server response', val.name+"--"+val.value);
                    });
                }

            },
            "lengthChange": true,
            "info":     true,
            "responsive": false,
            /*//"scrollY":        "300px",
            "scrollX":        true,
            "scrollCollapse": false*/
            "scrollCollapse":false,
            "paging":         true,
            keys: true,
            autoFill: true,
            dom: '<"top_panel"Bf>rt<"bottom_panel"lip><"info_panel"><"clear">',
            buttons: ['copy', 'excel', 'pdf',  'print',
                {
                    extend: 'colvis',
                    postfixButtons: [ 'colvisRestore' ]
                }
            ],
            columnDefs: [
                {
                    targets: -1,
                    visible: false
                }

            ],
            "processing": true,
            "serverSide": true,
            stateSave: true,
            "columns": [
                {
                    "data": "studentCode",
                    "render": function (data, type, row, meta) {
                        return '<a href="/student/show/' + row.id + '">' + data + '</a>';
                    }
                },
                {"data": "firstName"},
                {"data": "lastName"},
                {"data": "gender"},
                {
                    "data": "programmeName"
                    /*render:function (data, type, row, meta){
                        return JSON.stringify(data)
                    }*/
                },
                {
                    data: 'yearOf',
                    render: function(data, type, row, meta) {
                        return type === 'display' ?
                            '<span class="badge badge-info badge-pill">' + data + '</span>':data;
                    }
                },
                {"data": "groupOf",
                    render: function(data, type, row, meta) {
                        return type === 'display' ?
                            '<span class="badge badge-info badge-pill">' + data + '</span>':data;
                    }

                },
                {"data": "enrollmentDate"}
            ]
        });

        table.on( 'xhr', function () {
            var totalParams = table.ajax.params();
        } );

        // Create the chart with initial data
        var container = $('#bchart') //insertBefore(table.table().container());

        var chart = Highcharts.chart('bchart', {
            chart: {
                type: 'pie',
            },
            title: {
                text: 'Students visual',
            },
            series: [
                {
                    data: chartData(table),
                },
            ],
        });

        // On each draw, update the data in the chart
        table.on('draw', function () {
            chart.series[0].setData(chartData(table));

        });
    });
    function chartData(table) {
        var counts = {};

        // Count the number of entries for each position
        table
            .column(5, { search: 'applied' })
            .data()
            .each(function (val) {
                if (counts[val]) {
                    counts[val] += 1;
                } else {
                    counts[val] = 1;
                }
            });

        // And map it to the format highcharts uses
        return $.map(counts, function (val, key) {
            return {
                name: key,
                y: val,
            };
        });
    }
    function reloadDataTable(){
        table.ajax.reload();
        console.log("ajax reload function")
    }
</script>
</body>
</html>