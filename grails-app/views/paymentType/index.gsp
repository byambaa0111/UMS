<%@ page import="java.time.LocalDateTime; com.ums.payment.PaymentType" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'paymentType.label', default: 'PaymentType')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <script src="https://cdn.datatables.net/plug-ins/1.10.22/dataRender/datetime.js"></script>
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
             <div class="col-md-12 col-lg-8 col-xl-8">
                <div class="card">
                    <div class="card-header">
                        <h4><g:message code="default.list.label" args="[entityName]" /></h4>
                    </div>
                    <div class="card-body">
                                    <g:if test="${flash.message}">
                                        <div class="message" role="status">${flash.message}</div>
                                    </g:if>
                                    <f:table id="paymentTypeTable" properties="['programmeName','year','paymentType','amount','startDate','endDate','deadline',]" collection="${paymentTypeList}" except="['id','isActive']"/>
                    </div>
            </div>
                </div>
             <div class="col-md-12 col-lg-4 col-xl-4">
                 <div class="card">
                     <div class="card-header">
                         <h4>Шүүлт хайлт:</h4>
                     </div>
                     <div class="card-body">
                         <g:form name="filterForm" id="filterForm" method="POST">
                             <div class="form-group">
                                 <g:schoolYear></g:schoolYear>
                             </div>
                             <div class="form-group">
                                 <g:selectCheckFaculty name="department" id="department" multiple="single"></g:selectCheckFaculty>
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
        </div>
        <!--row closed-->
    </section>
    <script type="application/javascript">
        var url = '/paymentType/data_for_datatable';
        let table
        $(document).ready(function () {
            table = $('table').DataTable({
                "ajax": {
                    "url": url,
                    "type": "POST",
                    "data": function(d) {
                        var frm_data = $('#filterForm').serializeArray();
                        var yearOfList =[] ;
                        var departments =[] ;
                        $.each(frm_data, function(key, val) {
                            if(val.name == "departments"){
                                departments.push(val.value)
                            }else{
                                d[val.name] = val.value;
                            }
                            if(val.name == "yearOf"){
                                yearOfList.push(val.value)
                            }else{
                                d[val.name] = val.value;
                            }
                        });
                        d["departments"] = departments;
                        d["yearOf"] = yearOfList;
                    }
                },
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
                        /*targets: 5,
                        render: $.fn.dataTable.render.moment( 'YYYY MM DD' )*/
                    },
                ],
                "processing": true,
                "serverSide": true,
                "columns": [
                    {
                        "data": "programmeName",
                        "render": function (data, type, row, meta) {
                            return '<a href="/paymentType/show/' + row.id + '">' + data + '</a>';
                        }
                    },
                    {"data": "year"},
                    {"data": "paymentType"},
                    {"data": "amount"},
                    {"data": "startDate"},
                    {"data": "endDate"},
                    {"data": "deadline"},
                ]
            });

        });
        function reloadDataTable(){
            table.ajax.reload();
        }
    </script>
    </body>
</html>