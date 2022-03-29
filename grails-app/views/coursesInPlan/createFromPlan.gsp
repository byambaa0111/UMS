<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'coursesInPlan.label', default: 'CoursesInPlan')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <link rel="stylesheet" href="https://cdn.datatables.net/select/1.3.1/css/select.dataTables.min.css">
        <script src="https://cdn.datatables.net/select/1.3.1/js/dataTables.select.min.js"></script>
        <link type="text/css" href="//gyrocode.github.io/jquery-datatables-checkboxes/1.2.12/css/dataTables.checkboxes.css" rel="stylesheet" />
        <script type="text/javascript" src="//gyrocode.github.io/jquery-datatables-checkboxes/1.2.12/js/dataTables.checkboxes.min.js"></script>

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
                    <g:link class="text-light-color" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link>
                </li>
            </ol>
        </div>
        <!--page-header closed-->
        <div class="row">
                <div class="col-md-3 col-lg-3 col-xl-3">
                    <div class="card">
                        <div class="card-header">
                            <h4>Шүүлт хайлт:</h4>
                        </div>
                        <div class="card-body">
                            <g:form name="filterForm" id="filterForm" method="POST">
                            %{--<input class="form-control" name="search" type="text" value="" placeholder="Search"/>--}%
                                <g:selectCheckFaculty name="departments" multiple="multiple" collection=""></g:selectCheckFaculty>
                                <a class="btn btn-info" id="searchForm" href="#" onclick="reloadDataTable()">Search</a>

                            </g:form>
                        </div>
                    </div>
                    <div class="card">
                    <div class="card-header">
                        <h4><g:message code="default.create.label" args="[entityName]" /></h4>
                    </div>
                    <div class="card-body">
                         <div id="create-coursesInPlan" >
                                    <g:if test="${flash.message}">
                                        <div class="message" role="status">${flash.message}</div>
                                    </g:if>
                                    <g:hasErrors bean="${this.coursesInPlan}">
                                        <ul class="errors" role="alert">
                                            <g:eachError bean="${this.coursesInPlan}" var="error">
                                                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                                            </g:eachError>
                                        </ul>
                                    </g:hasErrors>
                                    <g:form class="form-horizontal" name="formCourseInPlan" id="formCourseInPlan" controller="coursesInPlan" action="saveCourses" method="POST">

                                        <div class="form-group row" >

                                            <input class="form-control" name="programmePlan" value="${programmePlan.id}"  />
                                            <f:all bean="${coursesInPlan}"  except="['course','programmePlan','department']"/>

                                        </div>

                                        <fieldset class="buttons">
                                            <g:submitButton name="create" class="btn btn-primary m-b-5 m-t-5" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                                        </fieldset>
                                    </g:form>
                                </div>
                    </div>
                </div>
                </div>
                <div class="col-md-4 col-lg-4  col-xl-4" id="resultCourses" >
                    <div class="card">
                        <div class="card-body">
                            <table>
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>CourseCode</th>
                                    <th>CourseName</th>
                                    <th>Creadit</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-lg-4  col-xl-4"  id="treeCourses">
                    <g:treeViewCoursesInPlan courseInPlanlist="${programmePlan.coursesInPlans}"></g:treeViewCoursesInPlan>
                </div>
        </div>
    </section>
    <script>
        var url = "<g:createLink url="[action:'coursesBy',controller:'course']" />"
        function reloadCourseDataTable() {
            $.ajax({
                type: "POST",
                dataType: "JSON",
                url: url,
                data:  (function(){
                    return $("#filterForm").serializeArray();
                })(),

                error: function (xhr, status, error) {
                    $("#resultCourses").html(xhr.responseText);
                },
                success: function(response) {
                    console.log("resporse"+response)
                    $( "#resultCourses").html( response)
                }

            });
        }
        $(document).ready(function () {
            /*left menu hid hiij baina*/
            $("body").addClass("sidenav-toggled");
        });
        // Attach a submit handler to the form
      /*  $( "#searchForm" ).on('click', function( event ) {

            // Stop form from submitting normally
            event.preventDefault();

            // Get some values from elements on the page:
            var $form = $( this ),
                term = $form.find( "input[name='s']" ).val(),
                url = url

            // Send the data using post
            var posting = $.post( url, { s: term } );

            // Put the results in a div
            posting.done(function( data ) {
                var content = $( data ).find( "#content" );
                $( "#result" ).empty().append( content );
            });
        });*/
        var url = '/course/data_for_datatable';
        var table;
        $(document).ready(function () {
            table = $('table').DataTable({
                "ajax": {
                    url:url,
                    "type": "POST",
                    "data":function (d){
                        var frm_data = $('#filterForm').serializeArray();
                        var departments =[] ;
                        $.each(frm_data, function(key, val) {
                            if(val.name == "departments"){
                                departments.push(val.value)
                            }else{
                                d[val.name] = val.value;
                            }
                            d["departments"] = departments;
                            console.log('Server response', val.name+"--"+val.value);
                        });
                    },
                },
                "paging"      :true,
                "responsive"  : true,
                searchDelay: 400,


                dom: '<"top_panel"f>rt<"bottom_panel"lip><"info_panel"><"clear">',
                "serverSide": true,
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
                'fnCreatedRow': function(nRow, aData, iDataIndex) {
                    $(nRow).attr('data-id', aData.DT_RowId); // or whatever you choose to set as the id
                    $(nRow).attr('id', 'id_' + aData.DT_RowId); // or whatever you choose to set as the id
                },
                'order': [[1, 'asc']],

                "lengthMenu": [[5, 25, 50, -1], [5, 25, 50, "All"]],
                "columns": [
                    {"data":"id"},
                    /*{
                        "data":null,
                        "defaultContent": 'df',},*/
                    {
                        "data": "courseCode",
                        "render": function (data, type, row, meta) {
                            return '<a  id="addLink" href="/course/show/' + row.id + '">' + data + '</a>';
                        }
                    },
                    {"data": "courseNameMN"},
                    {"data": "totalCredit"}
                ]

            });
            var frm = $('#formCourseInPlan');
            // Handle form submission event
            $('#formCourseInPlan').on('submit', function(e) {

                var form = this;

                var rows_selected = table.column(0).checkboxes.selected();

                $.each(rows_selected, function (index, rowId) {

                    frm.append(
                        $('<input>')
                            .attr('type', 'hidden')
                            .attr('name', 'courseIds')
                            .val(rowId)
                    );
                });

                $.ajax({
                    type: frm.attr('method'),
                    url: frm.attr('action'),
                    data: frm.serialize(),
                    success: function (data) {
                        $("#treeCourses").empty();
                        $("#treeCourses").html(data)
                        toastr.success('Submission was successful.', 'Hello, world!');
                    },
                    error: function (data) {
                        console.log('An error occurred.');
                        $("#treeCourses").html(data);
                    },
                });
                // Remove added elements
                $('input[name="id\[\]"]', form).remove();
                e.preventDefault();
                // Prevent actual form submission

            });
        });
        function reloadDataTable(){
            table.ajax.reload();
            console.log("ajax reload function")
        };
    </script>
    </body>
</html>
