<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'sysMenu.label', default: 'SysMenu')}" />
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
                            <div class="row">
                                <div id="top_panel" class="col-lg-auto"></div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <g:if test="${flash.message}">
                                        <div class="message" role="status">${flash.message}</div>
                                    </g:if>
                                    <f:table id="sysMenuTable"  collection="${sysMenuList}" maxProperties="6" except="['id','isActive']"/>
                                </div>
                            </div>
                            <div class="row">
                                <div id="bottom_panel"  class="col-lg-12" ></div>
                                <div id="info_panel"  class="col-lg-12" ></div>
                            </div>
                    </div>
                </div>
            </div>
        </div>
        <!--row closed-->
    </section>
    <script type="application/javascript">
        var url = '/sysMenu/data_for_datatable';
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
                dom: '<"top_panel"Bf>rt<"bottom_panel"p><"info_panel"li><"clear">',
                buttons : [ 'copy', 'excel', 'pdf', 'colvis' ],
                "processing": true,
                "serverSide": true,
                "columns": [
                    {
                        "data": "menuName",
                        "render": function (data, type, row, meta) {
                            return '<a href="/sysMenu/show/' + row.id + '">' + data + '</a>';
                        }
                    },
                    {"data": "controlName"},
                    {"data": "actionName"},
                    {"data": "menuDesc"},
                    {"data": "topMenu"},
                    {"data": "isHidden"}


                ]
            });


        });
    </script>
    </body>
</html>