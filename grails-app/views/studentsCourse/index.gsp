<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'studentsCourse.label', default: 'StudentsCourse')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>

    <div id="print-me"></div>
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
             <div class="col-md-12 col-lg-4 col-xl-4">
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
                             <div class="form-group" >
                                 <label class="form-label">Сургалтын төлөвлөгөөнд байгаа хичээлүүд:</label>
                                 <div id="resultCourse">
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

                             <button class="btn btn-info" type="button"  value="Filter" onclick="submitFrm('renderCourseList','coursesTable')" >Хайх</button>
                             <button id="print" type="button" class="btn btn-info"  >Хэвлэх</button>

                             <div class="btn-group dropright">
                                 <button type="button" class="btn btn-primary m-b-5 m-t-5 dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                      Үйлдэлүүд
                                 </button>
                                 <div class="dropdown-menu">
                                     <a class="dropdown-item" onclick="submitFrm('renderCourseList','examPage')" href="#">Шалгалтын хуудас</a>
                                     <a class="dropdown-item" onclick="submitFrm('renderCourseList','studentsAllCourses')"  href="#">Дүнгийн хуудас</a>
                                     <a class="dropdown-item" onclick="submitFrm('renderCourseList','statistic')" href="#">Статистик хуудас</a>
                                     <div class="dropdown-divider"></div>
                                     <a class="dropdown-item" href="#">Separated link</a>
                                 </div>
                             </div>

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

             <div class="col-md-12 col-lg-8 col-xl-8">
                <div class="card">
                    <div class="card-header">
                        <h4><g:message code="default.list.label" args="[entityName]" /></h4>
                    </div>
                    <div class="card-body"  >
                        <div id="resultCourseList" class=" uk-overflow-auto table-responsive">

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--row closed-->
    </section>
    <script type="application/javascript">
        $("#print").click(function () {
          $("#resultCourseList").printThis();
        });

        $("#department").on('change',function (e){
            var url ="/studentsCourse/getProPlanByDepId"
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

        var frm = $("#filterForm")
        function submitFrm(action,command) {
            var url  = '/studentsCourse/'+action +'/'+command
            $.ajax({
                type: "POST",
                url: url,
                data: $("#filterForm").serialize(),
                success: function (response) {

                    $("#resultCourseList").html(response)
                },
                error: function (xhr, status, error) {
                   // $("#resultCourse").addClass('alert alert-info').append(xhr.responseText);
                }

            });
            //e.preventDefault();
        };

        $(document).ready(function () {
            /*left menu hid hiij baina*/
            $("body").addClass("sidenav-toggled");
            $('.select2').select2()
        });
    </script>
    </body>
</html>