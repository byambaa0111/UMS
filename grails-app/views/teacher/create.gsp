<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'teacher.label', default: 'Teacher')}" />
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
                                    <div id="create-teacher" >
                                        <g:if test="${flash.message}">
                                            <div class="message" role="status">${flash.message}</div>
                                        </g:if>
                                        <g:hasErrors bean="${this.teacher}">
                                            <ul class="errors" role="alert">
                                                <g:eachError bean="${this.teacher}" var="error">
                                                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                                                </g:eachError>
                                            </ul>
                                        </g:hasErrors>
                                        <g:form class="form-horizontal" resource="${this.teacher}" method="POST">

                                            <div class="form-group row ${invalid ? 'has-error' : ''}">
                                                <div class="col-md-2 col-sm-12 col-xs-12 form-group">
                                                    <label  class="form-label" style="text-align: right"  placeholder=".col-md-3" >
                                                        <g:message code="student.department.label" default="Department" />
                                                    </label>
                                                </div>
                                                <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                    <g:select id="department" class="form-control" name="department" from="${new com.ums.edu.uni.Department().list()}" optionKey="id" required=""  value="id"  noSelection="['':'-Choose Department-']" />
                                                    <g:if test="${errors}">
                                                        <g:each in="${errors}" var="error">
                                                            <span class="help-block"><g:message error="${error}"/></span>
                                                        </g:each>
                                                    </g:if>
                                                </div>
                                                <div class="col-md-2 col-sm-12 col-xs-12 form-group">
                                                    <label  class="form-label" style="text-align: right"  placeholder=".col-md-3" >
                                                        <g:message code="student.teacherCode.label" default="TeacherCode" />
                                                    </label>
                                                </div>
                                                <div class="col-md-4 col-sm-12 col-xs-12 form-group">

                                                    <g:field class="form-control" type="text" name="teacherCode" readonly="true" value="${this.teacher.teacherCode}" />
                                                </div>
                                                <f:all bean="teacher" except="['faculty','department','teacherCode']"/>
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
    <script type="application/javascript">

        $('select').on('change',function () {

            var id =  $(this).find(":selected").val() ;
            $.ajax({
                   url: "<g:createLink url="[action:'getTeacherCode',controller:'teacher']" />",
                   type:"POST",
                   data:{id:$(this).find(":selected").val()},
                   success: function(result){

                    $("#teacherCode").val(result);
                       $("#teacherCode").css('color:red')
                }});

        })
    </script>
    </body>
</html>
