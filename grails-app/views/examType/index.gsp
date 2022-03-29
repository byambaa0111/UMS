<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'examType.label', default: 'ExamType')}" />
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
             <div class="col-md-12 col-lg-4 col-xl-3">
                 <div class="card">
                     <div class="card-header">
                         <h4>Шүүлт хайлт:</h4>
                     </div>
                     <div class="card-body">
                         <g:form name="filterForm" id="filterForm" method="POST">
                             <div class="form-group overflow-hidden">
                                 <label>Салбар Сургууль</label>
                                 <select class="form-control select2 w-100" id="faculties" name="faculties"
                                         multiple="multiple" data-placeholder="Select a faculty">
                                     <g:each in="${com.ums.edu.uni.Faculty.list()}" var="faculty" status="i">
                                         <option value="${faculty?.id}">${faculty?.facultyName}</option>
                                     </g:each>
                                 </select>
                             </div>
                             <a class="btn btn-info" href="#" onclick="reloadDataTable()">Search</a>
                         </g:form>
                     </div>
                 </div>

             </div>
            <div class="col-md-12 col-lg-8 col-xl-9">
                <div class="card">
                    <div class="card-header">
                        <h4><g:message code="default.list.label" args="[entityName]" /></h4>
                    </div>
                    <div class="card-body">
                                    <g:if test="${flash.message}">
                                        <div class="message" role="status">${flash.message}</div>
                                    </g:if>
                                    <f:table id="examTypeTable"  collection="${examTypeList}" except="['id','isActive']"/>
                        <div class="pagination">
                            <g:paginate total="${examTypeCount ?: 0}" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--row closed-->
    </section>
    <script type="application/javascript">
        var url = '/examType/data_for_datatable';
        $(document).ready(function () {
            var table = $('table').DataTable({
                "ajax": url,
                "lengthChange": true,
                "info":     true,
                "paging":         true,
                "responsive": true,
                "language": {
                    "decimal": ",",
                    "thousands": "."
                },
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
                        "data": "id",
                        "render": function (data, type, row, meta) {
                            return '<a href="/examType/show/' + row.id + '">' + data + '</a>';
                        }
                    },
                    {"data": "version"},


                ]
            });


        });
    </script>
    </body>
</html>