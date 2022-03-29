<%@ page import="com.ums.system.SysGroup" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'sysGroupMenu.label', default: 'SysGroupMenu')}" />
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
                         <g:form name="filterForm" id="filterForm" action="showList" method="POST">

                             <div class="form-group overflow-hidden">
                                 <label>Салбар Сургууль</label>


                                 <g:select class="form-control select2 w-100" name="groupId"
                                           from="${SysGroup.list()}"
                                             optionKey="id"
                                                optionValue="groupName"
                                            />
                             </div>


                             <input class="btn btn-info"  type="submit" value="Search"/>

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
                            <div class="row">
                                <div id="top_panel" class="col-lg-auto"></div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <g:if test="${flash.message}">
                                        <div class="message" role="status">${flash.message}</div>
                                    </g:if>
                                    %{--<f:table id="sysGroupMenuTable" maxProperties="2" collection="${sysGroupMenuList}" except="['id','isActive']"/>--}%
                                    <div id="showList">

                                    </div>
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
        // this is the id of the form
        $("#filterForm").submit(function(e) {

            e.preventDefault(); // avoid to execute the actual submit of the form.

            var form = $(this);
            var url = form.attr('action');

            $.ajax({
                type: "POST",
                url: url,
                data: form.serialize(), // serializes the form's elements.
                success: function(data)
                {
                   // alert(data); // show response from the php script.
                    $( "#showList" ).html(data) ;
                },
                error: function(xhr, status, error) {
                    $( "#showList" ).addClass( 'alert alert-info' ).append(xhr.responseText);
                }
            });


        });

        function changeName()
        {
            event.preventDefault();
            $.ajax({
                url:"<g:createLink url="[action:'index',controller:'teacher']" />",
                dataType: "json",
                type:"post",
                data:  function(d) {
                    var frm_data = $('#my_awesome_form').serializeArray();
                    $.each(frm_data, function(key, val) {
                        d[val.name] = val.value;
                    });
                },
                success: function() {
                    $( "#resultDiv" ).addClass( 'alert alert-info' ).append( 'Successfully saved event' )
                },
                error: function(xhr, status, error) {
                    $( "#resultDiv" ).addClass( 'alert alert-info' ).append(xhr.responseText);
                }
            });
        }
    </script>
    </body>
</html>