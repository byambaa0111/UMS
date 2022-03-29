<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'programme.label', default: 'Programme')}" />
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
             <div class="col-lg-12 col-lg-9 col-xl-9">
                <div class="card">
                    <div class="card-header">
                        <h4><g:message code="default.list.label" args="[entityName]" /></h4>
                    </div>
                    <div class="card-body">
                         <f:table id="programmeTable" maxProperties="3"  collection="${programmeList}" except="['id','isActive']"/>
                    </div>
                </div>
            </div>
             <div class="col-md-12 col-lg-3 col-xl-3">
                 <div class="card">
                     <div class="card-header">
                         <h4>Шүүлт хайлт:</h4>
                     </div>

                     <div class="card-body">
                         <g:form name="filterForm" id="filterForm" method="POST">

                             <div class="form-group overflow-hidden">
                                 <g:select2Faculty multiple="multiple"></g:select2Faculty>

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
        let url = '/programme/data_for_datatable';
        let table
        $(document).ready(function () {
             table = $('table').DataTable({
                "ajax": {
                    "url": url,
                    "type": "POST",
                    "data": function(d) {
                        var frm_data = $('#filterForm').serializeArray();
                        var faculties =[] ;
                        $.each(frm_data, function(key, val) {
                            if(val.name == "faculties"){
                                faculties.push(val.value)
                            }else{
                                d[val.name] = val.value;
                            }
                            d["faculties"] = faculties;
                            console.log('Server response', val);
                        });
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
                        targets: -1,
                        visible: false
                    },
                ],
                "processing": true,
                "serverSide": true,
                "columns": [
                    {
                        "data": "programmeCode",
                        "render": function (data, type, row, meta) {
                            return '<a href="/programme/show/' + row.id + '">' + data + '</a>';
                        }
                    },
                    {"data": "programmeMN"},
                    {"data": "programmeEN"}


                ]
            });


        });
        function reloadDataTable(){
            table.ajax.reload();
        }
    </script>
    </body>
</html>