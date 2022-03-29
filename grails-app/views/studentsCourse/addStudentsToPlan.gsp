<%@ page import="com.ums.edu.course.ProgrammePlan; com.ums.edu.course.Programme; com.ums.edu.uni.Faculty" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'student.label', default: 'Student')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
%{--
    <link rel="stylesheet" href="https://cdn.datatables.net/autofill/2.3.5/css/autoFill.dataTables.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/keytable/2.5.3/css/keyTable.dataTables.min.css">
    <script src="https://cdn.datatables.net/autofill/2.3.5/js/dataTables.autoFill.min.js"></script>
    <script src="https://cdn.datatables.net/keytable/2.5.3/js/dataTables.keyTable.min.js"></script>--}%

    <link rel="stylesheet" href="https://cdn.datatables.net/select/1.3.1/css/select.dataTables.min.css">
    <script src="https://cdn.datatables.net/select/1.3.1/js/dataTables.select.min.js"></script>
    <link type="text/css" href="//gyrocode.github.io/jquery-datatables-checkboxes/1.2.12/css/dataTables.checkboxes.css" rel="stylesheet" />
    <script type="text/javascript" src="//gyrocode.github.io/jquery-datatables-checkboxes/1.2.12/js/dataTables.checkboxes.min.js"></script>

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
                <div class="card-header">
                    <div class="float-right">
                        <a data-collapse="#mycard-collapse" class="btn btn-icon"><i class="ion ion-minus"></i></a>
                    </div>
                    <h4>Шүүлт хайлт:</h4>
                </div>
                <div class="collapse show" id="mycard-collapse">
                    <div class="card-body">
                        <g:form name="filterForm" id="filterForm" method="POST">

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
                            <div class="form-group">
                                <label class="form-label">Хүйс</label>
                                <g:each in="${grails.util.Holders.config.person.gender.list}" var="gender" status="i">
                                    <label>
                                        <input type="radio" name="gender" class="flat-blue" value="${gender}" >
                                        ${gender}
                                    </label>
                                </g:each>
                                <label>
                                    <input type="radio" name="gender" class="flat-blue" value="all" checked="true" >
                                    Бүгд
                                </label>
                            </div>
                            <div class="form-group">
                                <label class="form-label">Group</label>
                                <div class="selectgroup selectgroup-pills">
                                    <g:each in="${grails.util.Holders.config.student.groupOf.list}" var="groupOf" status="i">
                                        <label class="selectgroup-item">
                                            <input type="checkbox" name="groupOf"  value="${groupOf}" class="selectgroup-input" >
                                            <span class="selectgroup-button">${groupOf}</span>
                                        </label>
                                    </g:each>
                                    <label class="selectgroup-item">
                                        <input type="checkbox" name="groupOf"  value="all" class="selectgroup-input"checked="true" >
                                        <span class="selectgroup-button" >All</span>
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="form-label">Year</label>
                                <div class="selectgroup selectgroup-pills">
                                    <g:each in="${grails.util.Holders.config.student.yearOf.list}" var="yearOf" status="i">
                                        <label class="selectgroup-item">
                                            <input type="checkbox" name="yearOf"  value="${yearOf}" class="selectgroup-input" >
                                            <span class="selectgroup-button">${yearOf}</span>
                                        </label>
                                    </g:each>
                                    <label class="selectgroup-item">
                                        <input type="checkbox" name="yearOf" id="yearOf" value="all" class="selectgroup-input" checked>
                                        <span class="selectgroup-button" >All</span>
                                    </label>
                                </div>
                            </div>
                            <a class="btn btn-info" href="#" onclick="reloadDataTable()">Search</a>
                        </g:form>
                    </div>
                </div>
            </div>

            <div class="card">
                <div class="card-header">
                    <div class="float-right">
                        <a data-collapse="#myStudentsCourse" class="btn btn-icon"><i class="ion ion-minus"></i></a>
                    </div>
                    <h4>Students Course</h4>
                </div>

                <div class="collapse show" id="myStudentsCourse">
                    <div class="card-body">
                    <g:form class="form-horizontal" name="formCourseInPlan" id="formCourseInPlan" controller="studentsCourse" action="saveStudentsToPlan" method="POST">

                        <div class="form-group">
                            <label class="form-label">Сургалтын төлөвлөгөө:</label>
                            <g:select name="programmePlan" class="form-control select2 w-100" from="${com.ums.edu.course.ProgrammePlan.list()?.sort{it:planName}}" optionKey="id" ></g:select>
                        </div>
                        <div class="form-group" id="coursesDiv">
                        </div>
                        <div class="form-group">
                            <label class="form-label">Сурагчидын элсэн орсон жил:</label>
                            <g:select class="form-control select2 w-100" from="${grails.util.Holders.config.getProperty("programmePlan.schoolYear.map",List)}" name="year"></g:select>
                        </div>
                        <div class="form-group">
                            <label class="form-label">Сурагчидын элсэн орсон жил:</label>
                            <g:select class="form-control select2 w-100" from="${grails.util.Holders.config.getProperty("courseInPlan.semester.list",List)}" name="semester"></g:select>
                        </div>
                    </div>
                        <fieldset class="buttons">
                            <g:submitButton name="create" class="btn btn-primary m-b-5 m-t-5" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                        </fieldset>
                    </g:form>
                    <div class="card-footer">
                        Card Footer
                    </div>
                </div>
            </div>

        </div>
        <div class="col-md-12 col-lg-7 col-xl-8">
            <div class="card">
                <div class="card-header">
                    <h4><g:message code="default.list.label" args="[entityName]" /></h4>
                </div>
                <div class="card-body">
                    <g:if test="${flash.message}">
                        <div class="message" role="status">${flash.message}</div>
                    </g:if>
                    <div class="table-responsive">
                        <table class="table table-bordered border-t0 key-buttons text-nowrap w-100">
                        <thead>
                        <tr>
                            <th><input type="checkbox"/> </th>
                            <td>studentCode</td>
                            <td>firstName</td>
                            <td>lastName</td>
                            <td>programme</td>
                            <td>yearOf</td>
                            <td>groupOf</td>
                            <td>enrollmentDate</td>
                        </tr>
                    </table>
                    </div>
                    %{--<f:table  maxProperties="7" properties="['studentCode','firstName','lastName','gender','programme','yearOf','groupOf','enrollmentDate']" collection="${studentList}" except="['id','isActive']" template="tableStudent" />--}%
                </div>

            </div>
        </div>
    </div>
</div>
    <!--row closed-->
</section>
<script type="application/javascript">

    $("#programmePlan").on('change',function (e){
        var url ="/studentsCourse/getCoursesByPlan"

        $.ajax({
            type: "POST",
            url: url,
            data:  { 'programmePlanId': this.value  }, // serializes the form's elements.
            success: function(data)
            {
                if(data == 'all'){
                    $( "#coursesDiv" ).empty() ;
                }else{
                    $( "#coursesDiv" ).html(data) ;
                }
            },
            error: function(xhr, status, error) {
                $( "#coursesDiv" ).addClass( 'alert alert-info' ).append(xhr.responseText);

            }
        });
    });
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

    $(document).ready(function () {
        /*left menu hid hiij baina*/
        $("body").addClass("sidenav-toggled");
    });
    /*---------------------------------------------*/
    var url = '/student/data_for_datatable';
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
            "responsive": false,
            "paging":         true,
            dom: '<"top_panel"Bf>rt<"bottom_panel"lip><"info_panel"><"clear">',
            buttons: ['copy', 'excel', 'pdf',  'print',
                {
                    extend: 'colvis',
                    postfixButtons: [ 'colvisRestore' ]
                }
            ],
            'columnDefs': [
                {
                    'targets': 0,
                    'checkboxes': {
                        'selectRow': true
                    }
                }
            ],
            'select': {
                'style': 'multi'
            },
            order: [
                [1, 'asc']
            ],

            'fnCreatedRow': function(nRow, aData, iDataIndex) {
                $(nRow).attr('data-id', aData.DT_RowId); // or whatever you choose to set as the id
                $(nRow).attr('id', 'id_' + aData.DT_RowId); // or whatever you choose to set as the id
            },

            "lengthMenu": [[10, 25, 50, 100], [10, 25, 50, 100]],
            "serverSide": true,
            "columns": [
                {"data":"id"},
                {
                    "data": "studentCode",
                    "render": function (data, type, row, meta) {
                        return '<a href="/student/show/' + row.id + '">' + data + '</a>';
                    }
                },
                {"data": "firstName"},
                {"data": "lastName"},
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
      /*  table.on("click", "th.select-checkbox", function() {
            if ($("th.select-checkbox").hasClass("selected")) {
                table.rows().deselect();
                $("th.select-checkbox").removeClass("selected");
            } else {
                table.rows().select();
                $("th.select-checkbox").addClass("selected");
            }
        }).on("select deselect", function() {
            ("Some selection or deselection going on")
            if (table.rows({
                selected: true
            }).count() !== table.rows().count()) {
                $("th.select-checkbox").removeClass("selected");
            } else {
                $("th.select-checkbox").addClass("selected");
            }
        });*/
        // Create the chart with initial data

    });
    function reloadDataTable(){
        table.ajax.reload();
        console.log("ajax reload function")
    }

    var frm = $('#formCourseInPlan');
    // Handle form submission event
    $('#formCourseInPlan').on('submit', function(e) {
        var form = this;
        var rows_selected = table.column(0).checkboxes.selected();
        // Iterate over all selected checkboxes
        $.each(rows_selected, function (index, rowId) {
            frm.append(
                $('<input>')
                    .attr('type', 'hidden')
                    .attr('name', 'studentsId')
                    .val(rowId)
            );
        });

        $.ajax({
            type: frm.attr('method'),
            url: frm.attr('action'),
            data: frm.serialize(),
            success: function (data) {
                toastr.success(data, 'successful');
                console.log('Submission was successful.');
            },
            error: function (data) {
                toastr.success(data, 'Error');
                console.log('An error occurred.');
                $("#treeCourses").html(data);
            },
        });
        // Remove added elements
        $('input[name="id\[\]"]', form).remove();
        e.preventDefault();
        // Prevent actual form submission

    });


</script>
</body>
</html>