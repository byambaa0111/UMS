<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'teachersCourse.label', default: 'TeachersCourse')}" />
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
                             <g:form  id="teachersCourseForm" name="teachersCourseForm" method="GET">
                                 <div class="form-group">
                                     <g:schoolYear  methodName="currentSemester()"></g:schoolYear>
                                 </div>
                                 <div class="form-group">
                                     <g:radioHorizontal name="semester" title="Семестр" toggleList="${grails.util.Holders.config.getProperty("courseInPlan.semester.list",List)}"></g:radioHorizontal>
                                 </div>

                                 <div class="form-group">
                                     <g:selectCheckFaculty name="department" id="department" multiple="single"></g:selectCheckFaculty>
                                 </div>
                                 <div class="form-group custom-switches-stacked">
                                     <label class="form-label">Хүйс</label>
                                     <g:each in="${grails.util.Holders.config.person.gender.list}" var="gender" status="i">
                                         <label class="custom-switch">
                                             <input type="radio" name="gender" id ="gender" value="${gender}" class="custom-switch-input"  onchange="reloadDataTable()">
                                             <span class="custom-switch-indicator"></span>
                                             <span class="custom-switch-description">${gender}</span>
                                         </label>
                                     </g:each>
                                     <label class="custom-switch">
                                         <input type="radio" name="gender" value="all" checked="true" onclick="reloadDataTable()" class="custom-switch-input">
                                         <span class="custom-switch-indicator"></span>
                                         <span class="custom-switch-description">Бүгд</span>
                                     </label>
                                 </div>
                                 <div class="form-group custom-switches-stacked">
                                     <label class="form-label">Ажиллах хэлбэр</label>
                                     <g:each in="${grails.util.Holders.config.teacher.jobType.list}" var="jobType" status="i">
                                         <label class="custom-switch">
                                             <input type="radio" name="jobType" id ="jobType" value="${jobType}" class="custom-switch-input"  onchange="reloadDataTable()">
                                             <span class="custom-switch-indicator"></span>
                                             <span class="custom-switch-description">${jobType}</span>
                                         </label>
                                     </g:each>
                                     <label class="custom-switch">
                                         <input type="radio" name="jobType" value="all" checked="true" onclick="reloadDataTable()" class="custom-switch-input">
                                         <span class="custom-switch-indicator"></span>
                                         <span class="custom-switch-description">Бүгд</span>
                                     </label>
                                 </div>
                                 <div class="form-group custom-switches-stacked">
                                     <g:each in="${grails.util.Holders.config.teacher.teacherType.list}" var="teacherType" status="i">
                                         <div class="form-group">
                                             <input type="checkbox" id="teacherType" name="teacherType" onchange="reloadDataTable()" value="${teacherType}" class="flat-blue" checked>
                                             <label for="teacherType">${teacherType}</label>
                                         </div>
                                     </g:each>
                                 </div>
                                 <button class="btn btn-info" type="button"   value="Filter" onclick="submitFrm()" >Хайх</button>
                             </g:form>
                         </div>
                     </div>

                 </div>
                 <div id="teachers" class="col-md-12 col-lg-8 col-xl-9">

                 </div>
            </div>
            <!--row closed-->
         </section>
    <script type="application/javascript">
        function currentSemester(){
            let schoolYear = $("select[name='year']").val();
               /*$("input[name='semester']:checked").val(),
               $('input[name="semester"][value="1"]').prop("checked", "true");
               $('input[name="semester"][value="1"]').prop("checked", "checked");*/
        }
        let frm = $("#teachersCourseForm")
        function submitFrm() {
            let url  = `/teachersCourse/data_for_datatable`
            $.ajax({
                "type": "GET",
                "url": url,
                "data":  $('#teachersCourseForm').serialize() ,// { 'department': $( "select[name='department']" ).val(),'schoolYear': $("select[name='year']").val(),'semester': $("input[name='semester']:checked").val(),'isTable':"table" },
                "contentType": 'application/json; charset=utf-8',
                "success": function (response) {
                    $("#teachers").html(response)
                    toastr.success("success","Багш амжиллтай хадгаладлаа ")
                },
                "error": function (xhr, status, error) {
                    // $("#resultCourse").addClass('alert alert-info').append(xhr.responseText);
                }
            });
        };
    </script>
    </body>
</html>