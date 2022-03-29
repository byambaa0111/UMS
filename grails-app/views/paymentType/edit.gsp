<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'paymentType.label', default: 'PaymentType')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
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
        <div class="col-12">
                <div class="card">
                    <div class="card-header">
                        <h4><g:message code="default.create.label" args="[entityName]" /></h4>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-12">
                            <div id="edit-paymentType" >
                                <g:if test="${flash.message}">
                                <div class="message" role="status">${flash.message}</div>
                                </g:if>
                                <g:hasErrors bean="${this.paymentType}">
                                <ul class="errors" role="alert">
                                    <g:eachError bean="${this.paymentType}" var="error">
                                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                                    </g:eachError>
                                </ul>
                                </g:hasErrors>
                                <g:form resource="${this.paymentType}" method="PUT">
                                    <g:hiddenField name="version" value="${this.paymentType?.version}" />
                                    <div class="form-group row">
                                        <f:all bean="paymentType"/>
                                    </div>
                                    <fieldset class="buttons">
                                        <input class="btn btn-primary m-b-5 m-t-5" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                                    </fieldset>
                                </g:form>
                            </div>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
    </div>
    </body>
</html>
