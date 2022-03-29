<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'home.label', default: 'Home')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<g:render template="${session['homepage']}"></g:render>
</body>
</html>
