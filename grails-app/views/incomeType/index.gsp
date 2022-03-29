<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'incomeType.label', default: 'IncomeType')}" />
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
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-header">
                        <h4><g:message code="default.list.label" args="[entityName]" /></h4>
                    </div>
                    <div class="card-body">
                                    <g:if test="${flash.message}">
                                        <div class="message" role="status">${flash.message}</div>
                                    </g:if>
                                    <f:table id="incomeTypeTable"  collection="${incomeTypeList}" except="['isActive']"/>
                        %{--<div class="pagination">
                            <g:paginate total="${incomeTypeCount ?: 0}" />
                        </div>--}%


                    </div>
                </div>
            </div>
        </div>
        <!--row closed-->
    </section>
    <script type="application/javascript">
        var url = '/incomeType/data_for_datatable';
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

                "processing": true,
                "serverSide": true,
                "columns": [
                    {
                        "data": "id",
                        "render": function (data, type, row, meta) {
                            return '<a href="/incomeType/show/' + row.id + '">' + data + '</a>';
                        }
                    },
                    {"data": "incomeType"},


                ]
            });


        });
    </script>
    </body>
</html>