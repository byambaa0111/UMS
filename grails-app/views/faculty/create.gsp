<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'faculty.label', default: 'Faculty')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
    <section class="section">
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
            </ol>
        </div>
        <!--page-header closed-->
        <div class="row">
            <div class="col-12">
                <div class="card">
                        <div class="card-header">
                            <h4><g:message code="default.create.label" args="[entityName]" /></h4>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <div id="create-faculty" >
                                        <g:if test="${flash.message}">
                                            <div class="message" role="status">${flash.message}</div>
                                        </g:if>
                                        <g:hasErrors bean="${this.faculty}">
                                            <ul class="errors" role="alert">
                                                <g:eachError bean="${this.faculty}" var="error">
                                                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                                                </g:eachError>
                                            </ul>
                                        </g:hasErrors>
                                        <g:form class="form-horizontal" resource="${this.faculty}" method="POST">

                                            <div class="form-group row" >
                                                <f:all bean="faculty"/>
                                            </div>

                                            <fieldset class="buttons">
                                                <g:submitButton name="create" class="btn btn-primary m-b-5 m-t-5" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                                            </fieldset>
                                        </g:form>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
            </div>
        </div>
    </section>
    </body>
</html>
