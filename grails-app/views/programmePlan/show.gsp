<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'programmePlan.label', default: 'ProgrammePlan')}" />
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
        <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h4><g:message code="default.show.label" args="[entityName]" /></h4>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div id="show-programmePlan" >
                                    <g:if test="${flash.message}">
                                    <div class="message" role="status">${flash.message}</div>
                                    </g:if>
                                    <f:display bean="programmePlan"  except="['studentsCourse','coursesInPlans']" />
                                    <g:form class="form-horizontal" resource="${this.programmePlan}" method="DELETE">
                                        <fieldset class="buttons">
                                            <g:link class="btn btn-primary m-b-5 m-t-5" action="edit" resource="${this.programmePlan}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                                            <g:link class="btn btn-primary m-b-5 m-t-5"  controller="coursesInPlan" action="createFromPlan" params="[id:this.programmePlan.id]"><g:message code="default.button.add.label" default="Add Course" /></g:link>
                                            <input class="btn btn-primary m-b-5 m-t-5" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                                            <input class="btn btn-primary m-b-5 m-t-5" type="submit" value="${message(code: 'default.button.show.label', default: 'Show course')}" onclick="treeVIewCoursesInPlan(${this.programmePlan.id})" />
                                        </fieldset>
                                    </g:form>


                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
        <div class="col-md-12">
            <div class="row">

                   <g:treeViewCoursesInPlan courseInPlanlist="${this.programmePlan.coursesInPlans}"></g:treeViewCoursesInPlan>
                    <div class="col-xl-6 col-lg-6 col-md-6">
                          <div class="card">
                    <div class="card-header">
                        <h4>Card header:</h4>
                    </div>

                    <div class="card-body" id="showCourseDiv">

                    </div>
                </div>
                    </div>
            </div>
        </div>
    </div>

    </body>
</html>
