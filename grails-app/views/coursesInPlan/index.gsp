<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'coursesInPlan.label', default: 'CoursesInPlan')}" />
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
                         <g:form name="filterForm" id="filterForm" method="POST">
                             <div class="form-group">
                                 <g:selectCheckFaculty name="department" id="department" multiple="single"></g:selectCheckFaculty>
                             </div>
                             <div class="form-group" id="result">
                                 <label class="form-label">Сургалтын төлөвлөгөө:</label>
                                 <g:select name="programmePlan" class="form-control select2 w-100" from="${com.ums.edu.course.ProgrammePlan.list()?.sort{it:planName}}" optionKey="id" ></g:select>
                             </div>
                             <button class="btn btn-info"> Filter</button>
                             <button id="print" type="button" class="btn btn-info"  >Хэвлэх</button>
                         </g:form>


                     </div>
                 </div>

             </div>
            <div class="col-md-12 col-lg-8 col-xl-9">
                <div class="card">
                    <div class="card-header">
                        <h4><g:message code="default.list.label" args="[entityName]" /></h4>
                    </div>
                    <div class="card-body" id="resultCourse">
                                    <g:if test="${flash.message}">
                                        <div class="message" role="status">${flash.message}</div>
                                    </g:if>


                    </div>
                </div>
            </div>
        </div>
        <!--row closed-->
    </section>
    <script type="application/javascript">
        $("#print").click(function () {
            $("#resultCourse").printThis();
        });

        $("#department").on('change',function (e){
            var url ="/coursesInPlan/getProPlanByDepId"
            // alert(this.value)
            $.ajax({
                type: "POST",
                url: url,
                data:  { 'depId': this.value  }, // serializes the form's elements.
                success: function(data)
                {
                    // alert(data); // show response from the php script.
                    if(data == 'all'){
                        $( "#result" ).empty() ;
                    }else{
                        $( "#result" ).html(data) ;
                        toastr.success("fdsafds","fdsafdsa")
                    }
                },
                error: function(xhr, status, error) {
                    $( "#result" ).addClass( 'alert alert-info' ).append(xhr.responseText);
                    toastr.error("fdsafds","fdsafdsa")
                }
            });
        });

        var frm = $("#filterForm")
        frm.on('submit', function(e) {
             var url  = '/coursesInPlan/courseBy'
            $.ajax({
                type: "POST",
                url: url,
                data: $("#filterForm").serialize(),
                success: function (response) {
                    $("#resultCourse").html(response)
                },
                error: function (xhr, status, error) {
                    $("#resultCourse").addClass('alert alert-info').append(xhr.responseText);
                }

            });
            e.preventDefault();

    });
    </script>
    </body>
</html>