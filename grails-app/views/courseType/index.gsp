<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'courseType.label', default: 'CourseType')}" />
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
                                    <f:table id="courseTypeTable"  collection="${courseTypeList}" except="['id','isActive']"/>
                        <div class="pagination">
                            <g:paginate total="${courseTypeCount ?: 0}" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--row closed-->
    </section>

    </body>
</html>