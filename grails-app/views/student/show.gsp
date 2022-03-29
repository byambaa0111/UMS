<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'student.label', default: 'Student')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
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
            <li class="breadcrumb-item active" aria-current="page">
                <g:link class="text-light-color" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
            </li>
        </ol>
    </div>
    <!--page-header closed-->
    <div class="row">
        <div class="col-md-12 col-lg-9 col-xl-9 ">
                <div class="card ">
                    <div class="card-header">
                        <h4><g:message code="default.show.label" args="[entityName]" /></h4>
                    </div>
                    <div class="card-body jumbotron ">
                        <div class="row">
                            <div class="col-md-12 ">
                                <div id="show-student"  >
                                    <g:if test="${flash.message}">
                                    <div class="message" role="status">${flash.message}</div>
                                    </g:if>
                                    <f:display bean="student" />

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
        <div class="col-md-12 col-lg-3 col-xl-3">
            <div class="card">
                <div class="card-header">
                    <h4>Tools:</h4>
                </div>
                <div class="card-body">

                    <g:form class="form-horizontal" resource="${this.student}" method="DELETE">
                        <g:link class="btn btn-app mt-2" action="edit" resource="${this.student}" ><i class="fa fa-edit"></i> <g:message code="default.button.edit.label" default="Edit" /></g:link>
                        <g:link class="btn btn-app mt-2" action="printDefinition" target="_new"  resource="${this.student}" ><i class="fa fa-print"></i> <g:message code="default.button.print.label" default="Print Def" /></g:link>
                        <g:link class="btn btn-app mt-2" action="delete" resource="${this.student}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><i class="fa fa-remove"></i>  ${message(code: 'default.button.delete.label', default: 'Delete')}</g:link>
                        <input class="btn btn-app mt-2" type="submit" value=" ${message(code: 'default.button.delete.label', default: 'Delete')}"  />
                        <a class="btn btn-app mt-2"><i class="fa fa-edit"></i> Edit</a>
                        <a class="btn btn-app mt-2"><i class="fa fa-play"></i> Play</a>
                        <a class="btn btn-app mt-2"><i class="fa fa-repeat"></i> Repeat</a>
                        <a class="btn btn-app mt-2"><i class="fa fa-pause"></i> Pause</a>
                        <a class="btn btn-app mt-2"><i class="fa fa-save"></i> Save</a>
                    </g:form>
                </div>
            </div>
        </div>
    </div>
    </body>
</html>
