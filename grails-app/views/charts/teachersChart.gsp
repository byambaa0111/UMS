<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'teacher.label', default: 'Teacher')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
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
                <g:link class="text-light-color" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link>
            </li>
        </ol>
    </div>
    <!--page-header closed-->
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-header">
                    <h4><g:message code="default.create.label" args="[entityName]"/></h4>
                </div>

                <div class="card-body">
                    <!--row open-->
                    <div class="row">
                        <div class="col-12 col-md-6 col-lg-6">
                            <div class="card overflow-hidden">
                                <div class="card-header">
                                    <h4>Pie Chart</h4>
                                </div>
                                <div class="card-body">
                                    <div id="chartPie11" class="w-100 overflow-hidden"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-md-6 col-lg-6">
                            <div class="card overflow-hidden">
                                <div class="card-header">
                                    <h4>Pie Chart2</h4>
                                </div>
                                <div class="card-body">
                                    <div id="chartPie22" class="w-100 overflow-hidden"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--row closed-->
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
