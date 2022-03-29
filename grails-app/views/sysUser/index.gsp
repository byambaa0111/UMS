<%@ page import="com.ums.system.SysGroup" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'sysUser.label', default: 'SysUser')}" />
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
                        <g:link class="text-light-color" action="create"><g:message code="default.new.label" args="[entityName]" />
                        </g:link>
                    </li>

                </ol>
             </div>
        <!--page-header closed-->
        <!--row open-->
        <div class="row">
            <div class="col-md-12 col-lg-4 col-xl-3" >
                <div class="card">
                    <div class="card-header">
                        <h4>Шүүлт хайлт:</h4>
                    </div>
                    <div class="card-body">
                    <g:form name="my_awesome_form" id="my_awesome_form" method="POST">
                        <div class="form-group custom-switches-stacked">
                            <label class="form-label">Системийн групп</label>
                            <g:each in="${ com.ums.system.SysGroup.list()}" var="sysGroupName" status="i">
                                <label class="custom-switch">
                                    <input type="radio" name="sysGroupName" id ="sysGroupName" value="${sysGroupName?.id}" class="custom-switch-input"  onchange="reloadDataTable()">
                                    <span class="custom-switch-indicator"></span>
                                    <span class="custom-switch-description">${sysGroupName}</span>
                                </label>
                            </g:each>
                            <label class="custom-switch">
                                <input type="radio" name="sysGroupName" value="all" checked="true" onclick="reloadDataTable()" class="custom-switch-input">
                                <span class="custom-switch-indicator"></span>
                                <span class="custom-switch-description">Бүгд</span>
                            </label>
                        </div>
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

                            <f:table id="sysUserTable" collection="${sysUserList}" displayStyle="" except="['id','password','passwordMnu','created','numberOfPagePrint','numberOfPagePrinted','isActive','lastLogin']"/>

                        </div>

                    </div>
                </div>


            </div>
        </div>
        <!--row closed-->

    </section>

    <script type="application/javascript">
        var url = '/sysUser/data_for_datatable';
        var table
        $(document).ready(function () {
            table= $('table').DataTable({
                "ajax": {url,
                    "type": "POST",
                    "data": function(d) {
                        var frm_data = $('#my_awesome_form').serializeArray();
                        var teacherType = [] ;
                        $.each(frm_data, function(key, val) {
                            if(val.name == "sysGroupName"){
                                teacherType.push(val.value)
                            }else{
                                d[val.name] = val.value;
                            }
                            d["sysGroupName"] = teacherType;
                            console.log('Server response', val);
                        });
                    },},
                "info":     true,
                "paging":         true,
                "responsive": true,
                "lengthChange": true,
                dom: '<"top_panel"Bf>rt<"bottom_panel"p><"info_panel"li><"clear">',
                buttons : [ 'copy', 'excel', 'pdf', 'colvis' ],
                "processing": true,
                "serverSide": true,
                "columns": [
                    {
                        "data": "userId",
                        "render": function (data, type, row, meta) {
                            return '<a href="/sysUser/edit/' + row.id + '">' + data + '</a>';
                        }
                    },
                    {
                        "data": "fullName",
                        "render": function (data, type, row, meta) {
                            return '<a href="/sysUser/edit/' + row.id + '">' + data + '</a>';
                        }
                    },
                    {"data": "email"},
                    {"data": "emailMnu"},
                    {"data": "sysGroup"},
                ]
            });


        });
        function reloadDataTable(){

            table.ajax.reload();
        }
        /*===================left menu hide hiij baina================*/
        $(document).ready(function () {
            /*left menu hid hiij baina*/
            $("body").addClass("sidenav-toggled");
        });

        /*=============================*/
    </script>
    </body>
</html>