<%@ page import="com.ums.hr.Experiences" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'teacher.label', default: 'Teacher')}" />
    <title><g:message code="default.profile.label" args="[entityName]" /></title>
</head>
<body>
<div id="resultCourseList">
    <section class="section">
        <!--page-header open-->
        <div class="page-header" id="headerRM">
            <h1 class="page-title"></h1>
            <ol class="breadcrumb">
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
            </ol>
        </div>
        <!--page-header closed-->
        <div class="row">
            <div class="col-12">
                <div class="card ">
                    <div class="card">
                        <div class="card-header">
                            <h4>
                                <g:message code="default.profile.label" args="[entityName]"  default="Профайл"/>
                            </h4>
                        </div>
                        <div class="card-body">
                            <!--row open-->
                            <div class="row">
                                <div class="col-lg-12 col-xl-4 col-md-12 col-sm-12">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="text-center">
                                                <div class="userprofile social">
                                                    <div class="userpic">
                                                        <asset:image type="image/x-ico" src="avatar/avatar-3.jpeg" alt="" class="userpicimg"/>
                                                    </div>
                                                    <h3 class="username">${this.teacher.lastName} ${this.teacher.firstName}</h3>
                                                    <p>${this.teacher.teacherType} : ${this.teacher.teacherCode}</p>
                                                    <div class="text-center mb-2">
                                                        <span><i class="fa fa-star text-warning"></i></span>
                                                        <span><i class="fa fa-star text-warning"></i></span>
                                                        <span><i class="fa fa-star text-warning"></i></span>
                                                        <span><i class="fa fa-star-half-o text-warning"></i></span>
                                                        <span><i class="fa fa-star-o text-warning"></i></span>
                                                    </div>
                                                    <div class="socials text-center"> <a href="" class="btn btn-circle btn-primary ">
                                                        <i class="fa fa-facebook"></i></a> <a href="" class="btn btn-circle btn-danger ">
                                                        <i class="fa fa-google-plus"></i></a> <a href="" class="btn btn-circle btn-info ">
                                                        <i class="fa fa-twitter"></i></a> <a href="" class="btn btn-circle btn-warning "><i class="fa fa-envelope"></i></a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12 col-xl-8 col-md-12 col-sm-12">
                                    <div class="card">
                                        <div class="card-body p-4">
                                            <div class="row d-flex">
                                                <div class="col-lg-6 col-md-12">
                                                    <ul class="nav nav-pills countlist mb-0 " role="tablist">
                                                        <li>
                                                            <h4>${this.teacher.experiences?.size() }<br>
                                                                <small class="text-muted">Ажлын туршлага</small> </h4>
                                                        </li>
                                                        <li>
                                                            <h4>${this.teacher.flanguages?.size() }<br>
                                                                <small class="text-muted">Гадаад хэл</small> </h4>
                                                        </li>
                                                        <li>
                                                            <h4>${this.teacher.projects?.size() }<br>
                                                                <small class="text-muted">Төсөл хөтөлбөр</small> </h4>
                                                        </li>
                                                        <li>
                                                            <h4>${this.teacher.rewards?.size() }<br>
                                                                <small class="text-muted">Гавьяа шагнал</small> </h4>
                                                        </li>
                                                        <li>
                                                            <h4>${this.teacher.educations?.size() }<br>
                                                                <small class="text-muted">Боловсрол</small> </h4>
                                                        </li>
                                                    </ul>
                                                </div>
                                                <div class="col-lg-6 col-md-12 follower">
                                                    <div class="float-right d-none d-lg-block">
                                                         <a href="#" id="print" class="btn btn-primary mt-1"><i class="fe fe-printer followbtn ml-1"></i> Хэвлэх</a>
                                                        <g:link class="btn btn-success mt-1" action="editProfile" id="${this.teacher.id}"><i class="fe fe-edit followbtn ml-1"></i><g:message code="default.edit.label" args="[entityName]" /></g:link>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card-body border-top p-4">
                                            <h5 class="media-heading mt-1"> Багшийн тухай</h5>
                                            <p>${this.teacher?.bio}</p>
                                            <p class="mb-0"> </p>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <!--row closed-->

                            <!--row open-->
                            <div class="row profile-card">
                                <div class="col-lg-7 col-md-12 col-sm-12">
                                    <div class="card">
                                        <div class="card-body">
                                            <form>
                                                <textarea class="form-control" placeholder="What are you doing right now?"></textarea>
                                                <br>
                                                <ul class="nav nav-pills pull-left">
                                                    <li><a href="#" title="" data-toggle="tooltip" data-placement="bottom" data-original-title="Audio"><i class="fa fa-volume-up"></i></a></li>
                                                    <li><a href="#" title="" data-toggle="tooltip" data-placement="bottom" data-original-title="Video"><i class="fa fa-video-camera"></i></a></li>
                                                    <li><a href="#" title="" data-toggle="tooltip" data-placement="bottom" data-original-title="Picture"><i class="fa fa-picture-o"></i></a></li>
                                                </ul>
                                                <button type="submit" class="btn btn-sm btn-success pull-right"><i class="fa fa-share ml-1"></i> Share</button>
                                            </form>
                                        </div>
                                    </div>


                                </div>

                                <div class="col-lg-5 col-md-12 col-sm-12">
                                    <div class="card">
                                        <div class="card-header">
                                            <h4>Personal Details</h4>
                                        </div>
                                        <div class="card-body">
                                            <p><b>User ID :</b> ${this.teacher.teacherCode}</p>
                                            <p><b>Name :</b> ${this.teacher.firstName}</p>
                                            <p><b>Emial :</b> ${this.teacher.email}</p>
                                            <p><b>Phone :</b> ${this.teacher.phoneNumber} </p>
                                            <p><b>Address : </b> ${this.teacher.address} </p>

                                        </div>
                                    </div>
                                    <div class="card">
                                        <div class="card-header">
                                            <h4>Skills</h4>
                                        </div>
                                        <div class="card-body">
                                            <div class="tags clearfix">
                                                <ul class="list-unstyled">
                                                    <li><a href="#">Wordpress</a></li>
                                                    <li><a href="#">Development </a></li>
                                                    <li><a href="#">Html5</a></li>
                                                    <li><a href="#">Jquery</a></li>
                                                    <li><a href="#">Bootstrap</a></li>
                                                    <li><a href="#">Photoshop</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>

                                </div>

                            </div>
                            <!--row closed-->


                        </div>
                    </div>
                </div>
            </div>
        <div class="col-lg-12 col-md-12 col-sm-12">
            <div class="card">
                <div class="card-header">
                    <h5>Ажлын туршлага </h5>
                </div>
                <div class="card-body overflow-hidden">
                    <div class="col-md-12">
                        <f:table id="experiencesTable" maxProperties="3" collection="${teacher.experiences}" except="['id','isActive']"></f:table>
                        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#experiencesModal">Input</button>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-header">
                    <h5>Гадаад хэлний мэдлэг</h5>
                </div>
                <div class="card-body overflow-hidden">
                    <div class="col-md-12">
                        <f:table id="flanguagesTable"  collection="${this.teacher.flanguages}" maxProperties="4" except="['id','isActive']"/>
                        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#flanguagesModal">Input</button>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-header">
                    <h5> Tөсөл хөтөлбөр / Эрдэм шинжилгээ</h5>
                </div>
                <div class="card-body overflow-hidden">
                    <div class="col-md-12">
                        <f:table id="projectsTable"  collection="${this.teacher.projects}" maxProperties="4" except="['id','isActive']"/>
                        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#projectsModal">Input</button>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-header">
                    <h5>Гавьяа шагнал</h5>
                </div>
                <div class="card-body overflow-hidden">
                    <div class="col-md-12">
                        <f:table id="rewardsTable"  collection="${this.teacher.rewards}" maxProperties="4" except="['id','isActive']"/>
                        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#rewardsModal">Input</button>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-header">
                    <h5>Боловсрол </h5>
                </div>
                <div class="card-body overflow-hidden">
                    <div class="col-md-12">
                        <f:table id="educationTable"  collection="${this.teacher.educations}" maxProperties="4" except="['id','isActive']"/>
                        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#educationModal">Input</button>
                    </div>
                </div>
            </div>
        </div>
        </div>

    </section>
    <div class="modal fade"  id="experiencesModal" tabindex="-1" role="dialog"  aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="experiencesModal1">New message</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" >
                    <g:form class="form-horizontal" resource="${this.experiences}" method="POST">
                        <div class="form-group row" >
                            <f:all  bean="experiences" except="['teacher']" />
                            <g:hiddenField name="isModal" value="true"></g:hiddenField>
                            <g:hiddenField name="teacher" value="${this.teacher?.id}"></g:hiddenField>
                        </div>
                        <fieldset class="buttons">
                            <g:submitButton name="create" class="btn btn-primary m-b-5 m-t-5" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                        </fieldset>
                    </g:form>

                </div>
                <div class="modal-footer">
                    %{--<button type="button" class="btn btn-success" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Send message</button>--}%
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade"  id="rewardsModal" tabindex="-1" role="dialog"  aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="rewardsModal2">New message</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" >
                            <g:form class="form-horizontal" resource="${this.rewards}" method="POST">
                                <div class="form-group row" >
                                    <f:all  bean="rewards" except="['teacher']" />
                                    <g:hiddenField name="isModal" value="true"></g:hiddenField>
                                    <g:hiddenField name="teacher" value="${this.teacher?.id}"></g:hiddenField>
                                </div>
                                <fieldset class="buttons">
                                    <g:submitButton name="create" class="btn btn-primary m-b-5 m-t-5" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                                </fieldset>
                            </g:form>

                </div>
                <div class="modal-footer">
                    %{--<button type="button" class="btn btn-success" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Send message</button>--}%
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade"  id="flanguagesModal" tabindex="-1" role="dialog"  aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="flanguagesModal3">New message</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" >
                    <g:form class="form-horizontal" resource="${this.flanguages}" method="POST">
                        <div class="form-group row" >
                            <f:all  bean="flanguages"  except="['teacher']"/>
                            <g:hiddenField name="isModal" value="true"></g:hiddenField>
                            <g:hiddenField name="teacher" value="${this.teacher?.id}"></g:hiddenField>
                        </div>
                        <fieldset class="buttons">
                            <g:submitButton name="create" class="btn btn-primary m-b-5 m-t-5" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                        </fieldset>
                    </g:form>

                </div>
                <div class="modal-footer">
                    %{--<button type="button" class="btn btn-success" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Send message</button>--}%
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade"  id="projectsModal" tabindex="-1" role="dialog"  aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="example-Modal3">New message</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" >
                    <g:form class="form-horizontal" resource="${this.projects}" method="POST">
                        <div class="form-group row" >
                            <f:all  bean="projects" except="['teacher']"  />
                            <g:hiddenField name="isModal" value="true"></g:hiddenField>
                            <g:hiddenField name="teacher" value="${this.teacher?.id}"></g:hiddenField>
                        </div>
                        <fieldset class="buttons">
                            <g:submitButton name="create" class="btn btn-primary m-b-5 m-t-5" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                        </fieldset>
                    </g:form>

                </div>
                <div class="modal-footer">
                    %{--<button type="button" class="btn btn-success" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Send message</button>--}%
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade"  id="educationModal" tabindex="-1" role="dialog"  aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="educationModal3">New message</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" >
                <g:form class="form-horizontal" resource="${this.education}" method="POST">
                    <div class="form-group row" >
                        <f:all  bean="education"  except="['teacher']"/>
                        <g:hiddenField name="isModal" value="true"></g:hiddenField>
                        <g:hiddenField name="teacher" value="${this.teacher?.id}"></g:hiddenField>
                    </div>
                    <fieldset class="buttons">
                        <g:submitButton name="create" class="btn btn-primary m-b-5 m-t-5" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                    </fieldset>
                </g:form>

            </div>
            <div class="modal-footer">
                %{--<button type="button" class="btn btn-success" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Send message</button>--}%
            </div>
        </div>
    </div>
</div>
</div>
<script type="application/javascript">

    $("#print").click(function () {
        $("#print").hide();
        $(":button").hide();
        $("#headerRM").hide();
        $("#resultCourseList").printThis();
        $("#print").show();
        $("#headerRM").show();
        $(":button").show();
    });

</script>

%{--<!-- Message Modal closed -->
<script type="text/javascript">
    $(function () {
    $("#btnSubmit").click(function () {
        $.ajax({
            type: "POST",
            url: "teacher/profile/228/",
            data: "{name: '" + $("#txtName").val() + "'}",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (r) {
                $("#dialog").html(r.d);
                $("#dialog").dialog("open");
            }
        });
    });
    });
</script>--}%
</body>
</html>
