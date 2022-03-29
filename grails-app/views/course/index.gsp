<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'course.label', default: 'Course')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>

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
            <div class="col-md-12 col-lg-7 col-xl-8">
                <div class="card">
                    <div class="card-header">
                        <h4><g:message code="default.list.label" args="[entityName]" /></h4>
                    </div>
                    <div class="card-body">
                                    <g:if test="${flash.message}">
                                        <div class="message" role="status">${flash.message}</div>
                                    </g:if>
                                    <f:table properties="['courseCode','courseNameMN','totalCredit','courseType','semesterType']" collection="${courseList}" except="['id','isActive']"/>
                    </div>
                </div>
            </div>
             <div class="col-md-12 col-lg-5 col-xl-4">
                     <div class="card">
                         <div class="card-header">
                             <h4>Шүүлт хайлт:</h4>
                         </div>

                         <div class="card-body">
                             <g:form name="filterForm" id="filterForm" method="POST">
                                 <div class="form-group ">
                                     <label class="form-label">Салбар сургууль</label>
                                     <select multiple="multiple" name="departments"  class="optmulti-select">
                                         <g:each in="${com.ums.edu.uni.Faculty.list()}" var="faculty" status="i">
                                             <optgroup label="${faculty?.facultyName}">
                                                 <g:each in="${faculty.departments}" var="department" status="j">
                                                     <option value="${department.id}">${department.departmentCode}</option>
                                                 </g:each>
                                             </optgroup>
                                         </g:each>
                                     </select>
                                 </div>
                                 <g:toggleRadio name="semesterType" methodName="reloadDataTable" toggleList="${grails.util.Holders.config.getProperty('course.semesterType.list',List)}" title="Семистрийн төрөл"></g:toggleRadio>
                                 <g:toggleCheck title="CourseType" toggleList="${grails.util.Holders.config.getProperty('course.courseType.list',List)}" methodName="reloadDataTable()" name="fdsa"></g:toggleCheck>

                                 <a class="btn btn-info" href="#" onclick="reloadDataTable()">Search</a>

                             </g:form>
                         </div>
                     </div>
             </div>
        </div>
        <!--row closed-->
    </section>
    <script type="application/javascript">
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

                "lengthChange": true,
                "info"        :  true,
                "paging"      :true,
                "responsive"  : true,
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
                    },
                ],
                "processing": true,
                "serverSide": true,
                "columns": [
                    {
                        "data": "courseCode",
                        "render": function (data, type, row, meta) {
                            return '<a href="/course/show/' + row.id + '">' + data + '</a>';
                        }
                    },
                    {"data": "courseNameMN"},
                    {"data": "totalCredit"},
                    {"data": "courseType"},
                    {"data": "semesterType"}
                ]

            });


        });
        function reloadDataTable(){
            table.ajax.reload();
            console.log("ajax reload function")
        }
    </script>
    </body>
</html>