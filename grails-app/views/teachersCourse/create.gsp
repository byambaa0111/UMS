<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'teachersCourse.label', default: 'TeachersCourse')}" />
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
           %{-- <div class="col-12">
                <div class="card">
                        <div class="card-header">
                            <h4><g:message code="default.create.label" args="[entityName]" /></h4>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <div id="create-teachersCourse" >
                                        <g:if test="${flash.message}">
                                            <div class="message" role="status">${flash.message}</div>
                                        </g:if>
                                        <g:hasErrors bean="${this.teachersCourse}">
                                            <ul class="errors" role="alert">
                                                <g:eachError bean="${this.teachersCourse}" var="error">
                                                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                                                </g:eachError>
                                            </ul>
                                        </g:hasErrors>
                                        <g:form class="form-horizontal" resource="${this.teachersCourse}" method="POST">

                                            <div class="form-group row" >
                                                <f:all bean="teachersCourse"/>
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
            </div>--}%
        <div class="col-md-4 col-lg-4 col-xl-4">
            <div class="card">
                <div class="card-header">
                    <h4>Шүүлт хайлт:</h4>
                </div>

                <div class="card-body" >
                    <g:form name="filterForm" id="filterForm" method="POST">
                        <div class="form-group">
                            <g:schoolYear></g:schoolYear>
                        </div>
                        <div class="form-group">
                            <g:selectCheckFaculty name="department" id="department" multiple="single"></g:selectCheckFaculty>
                        </div>
                        <div class="form-group" >
                            <label class="form-label">Сургалтын төлөвлөгөө:</label>
                            <div id="result">
                                %{--Тухайн хөтөлбөрийн багт байгаа нийт сургалтын төлөвлөгөнүүдийг гаргаж байна--}%
                            </div>
                        </div>

                        <div class="form-group">

                            <label class="form-label">Group</label>
                            <div class="selectgroup selectgroup-pills">
                                <g:each in="${grails.util.Holders.config.student.groupOf.list}" var="groupOf" status="i">
                                    <label class="selectgroup-item">
                                        <input type="checkbox" name="groupOf"  value="${groupOf}" class="selectgroup-input" >
                                        <span class="selectgroup-button">${groupOf}</span>
                                    </label>
                                </g:each>
                                <label class="selectgroup-item">
                                    <input type="checkbox" name="groupOf"  value="all" class="selectgroup-input"checked="true" >
                                    <span class="selectgroup-button" >All</span>
                                </label>
                            </div>
                        </div>
                        <button class="btn btn-info" type="button"  value="Filter" onclick="loadCourseClick()" >Хайх</button>

                    </g:form>

                </div>
            </div>

            <div class="card">
                <div class="card-header">
                    <h4>Багш</h4>
                </div>
                <div class="card-body">
                    <g:select name="teacher" class="form-control select2"  from="${com.ums.hr.Teacher.list()}"> </g:select>
                </div>
            </div>

        </div>
        <div class="col-md-8 col-lg-8">
            <div class="card">
                <div class="card-body">
                    <div id="resultCourse">

                    </div>
                </div>
            </div>
        </div>
        </div>
    </section>
    <script type="application/javascript">
        $("#department").on('change',function (e){
            var url ="/studentsCourse/getProPlanByDepId"
            // alert(this.value)
            $.ajax({
                type: "POST",
                url: url,
                data:  { 'depId': this.value,'isTable':"table" }, // serializes the form's elements.
                success: function(data)
                {
                    // alert(data); // show response from the php script.
                    if(data == 'all'){
                        $( "#result" ).empty() ;
                    }else{
                        $( "#result" ).html(data) ;
                        $("#programmePlan").addClass('form-control select2 w-100')
                        toastr.success("success","ok")
                    }
                },
                error: function(xhr, status, error) {
                    $( "#result" ).addClass( 'alert alert-info' ).append(xhr.responseText);
                    toastr.error("fdsafds","fdsafdsa")
                }
            });
        });

        /*Багш сонгох эсвэл save дарсан үед teacherscourse хадгалаж байна.*/

        var frm = $("#filterForm")
        function submitFrm(course,teacher,semester) {
            var url  = '/teachersCourse/saveTeachersCourse/'+course
            $.ajax({
                type: "POST",
                url: url,
                data: $("#filterForm").serialize()+"&course="+course+"&teacher="+teacher+"&semester="+semester,
                success: function (response) {
                    $("#resultCourseList").html(response)
                    toastr.success("success","Багш амжиллтай хадгаладлаа ")
                    loadCourseClick()

                },
                error: function (xhr, status, error) {
                    // $("#resultCourse").addClass('alert alert-info').append(xhr.responseText);
                }

            });
            //e.preventDefault();
        };

        /* хайх гэсэн товчин дээр дарсан үед зүүн талын 8 улиралын хүснэгт гарч ирнэ */

        function loadCourseClick(){
            var url ="/studentsCourse/getCoursesByPlan"
            $.ajax({
                type: "POST",
                url: url,
                data:  { 'programmePlanId': $( "select[name='programmePlans']" ).val(),'schoolYear': $("select[name='year']").val(),'isTable':"table" }, // serializes the form's elements.
                success: function(data)
                {
                    // alert(data); // show response from the php script.
                    $( "#resultCourse" ).html(data) ;
                    toastr.success("success","Төлөвлөгөөнд байгаа хичээлүүд амжилттай ачаалагдлаа")

                },
                error: function(xhr, status, error) {
                    toastr.error("not success","Хичээлүүд байгүй")
                }
            });
        };
        $(document).ready(function () {
            /*left menu hid hiij baina*/

            $('.select2').select2()
        });
    </script>
    </body>
</html>
