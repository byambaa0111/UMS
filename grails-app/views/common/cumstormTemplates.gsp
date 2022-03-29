<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 11/14/2020
  Time: 3:24 PM
--%>

%{--empty card template--}%
    <div class="card">
        <div class="card-header">
            <h4>Card header:</h4>
        </div>
        <div class="card-body">

        </div>
    </div>

%{--empty filter left --}%
<div class="col-md-12 col-lg-4 col-xl-3">
    <div class="card">
        <div class="card-header">
            <h4>Шүүлт хайлт:</h4>
        </div>
        <div class="card-body">

        </div>
    </div>
</div>

%{--empty form field--}%
<div class="col-md-2 col-sm-12 col-xs-12 form-group">
    <label class="form-label"  placeholder=".col-md-3" for="${property}">${label} ${required ? '*' : ''}</label>
</div>

<div class="col-md-4 col-sm-12 col-xs-12 form-group">

    <g:widget placeholder="" bean="${domainClass}" property="${property}" class="form-control"/>

    <g:if test="${errors}">
        <g:each in="${errors}" var="error">
            <span class="help-block"><g:message error="${error}"/></span>
        </g:each>
    </g:if>

</div>
%{--empty page--}%
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
                    %{--page body--}%
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
%{--===================================--}%
<label class="form-label">Салбар сургууль</label>
<select multiple="multiple" name="departments" class="optmulti-select   w-100" >
<g:each in="${departments}" var="department" status="i">
    <optgroup label="${department?.departmentNameMN}">
        <g:each in="${departments.curriculums}" var="curriculum" status="j">
            <option value="${curriculum.id}">${curriculum.curriculumEN}</option>
        </g:each>
    </optgroup>
</g:each>
%{--============================================--}%
<div class="form-group overflow-hidden">
    <label>Салбар Сургууль</label>
    <select class="form-control select2 w-100" id="faculties" name="faculties"
            multiple="multiple" data-placeholder="Select a faculty">
        <g:each in="${com.ums.edu.uni.Faculty.list()}" var="faculty" status="i">
            <option value="${faculty?.id}">${faculty?.facultyName}</option>
        </g:each>
    </select>
</div>