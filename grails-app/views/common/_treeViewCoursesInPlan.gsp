
<%@ page import="com.ums.edu.course.CourseType" %>
<asset:javascript src="light/tree view.js"/>


    <div class="card">
        <div class="card-header">
            <h4>Мэргэжлийн хөтөлбөр:</h4>
        </div>
        <div class="card-body ">
            <div class="tree mb-0">
                <ul class="mb-0">
                    <%

                        def courseInPlanGrouped1 = courseInPlanlist.groupBy({ coursesInPlan -> coursesInPlan.courseType }).collect{k,v->[(k):v.creditHour.sum()]}
                        def courseInPlanGrouped = courseInPlanlist.groupBy({ coursesInPlan -> coursesInPlan.courseType })

                    %>
                    <g:each in="${com.ums.edu.course.CourseType.list()}" var="courseType">
                        <li >
                            <span ><i class="ti-calendar icon-plus-sign"></i> ${courseType}</span>
                            <span class="badge badge-dark" >Нийт: ${courseInPlanGrouped[courseType]?.collect{it.creditHour}?.value?.sum()}</span>
                            <ul>

                                <g:each in="${courseInPlanGrouped[courseType]?.sort{ it?.semester }  }" var="courseInPlan" status="i">
                                    <li style="display: none">
                                        <span class="badge badge-info" onclick="showCourse(${courseInPlan?.course.id}) " >${i}. ${courseInPlan}</span>
                                        <span class="badge badge-dark" > Credit:${courseInPlan.creditHour}</span>
                                        <span class="badge badge-cyan" > Season:${courseInPlan.semester}</span>
                                        <span class="badge badge-danger"  onclick="deleteCourse(${courseInPlan?.id},${courseInPlan?.programmePlan.id})"> Remove</span>
                                        <ul>
                                            %{--<g:each in="${department?.programmes}" var="programme">
                                                <li name="treeRoot">
                                                    <span><a href=""> ${programme.programmeMN}</a></span>
                                                </li>
                                            </g:each>--}%
                                        </ul>
                                    </li>
                                </g:each>
                            </ul>
                        </li>
                    </g:each>
                </ul>
            </div>
        </div>
    </div>

<script>

    function deleteCourse(courseId , programmePlanId ) {
        var url = "<g:createLink url="[action:'deleteAjax',controller:'coursesInPlan']" />"
        var r = confirm("Та энэ үйлдэлийг хийхдээ итгэлтэй байна уу!!!");
        if (r == true) {
            $.ajax({
                type: "POST",
                url: url,
                data: {"id": courseId, "programmePlanId": programmePlanId},
                success: function (data) {
                    $("#treeView").html(data)
                    toastr.info("Deletion was successful.")
                },
                error: function (data) {
                    console.log('An error occurred.');
                    console.log(data);
                },
            });
        }
    }

    function showCourse(courseId) {

        var url = "${createLink(controller: 'course',action:'showCourseAjax')}/"+courseId
        $.ajax({
            type: "GET",
            url: url,
            data: { id: courseId, field1: "hello" }, // serializes the form's elements.
            contentType: 'application/json; charset=utf-8',
            success: function  (response) {
                $( "#showCourseDiv" ).html( response)
            },
            error: function (xhr, status, error) {
                $("#showCourseDiv").addClass('alert alert-info').append(xhr.responseText);
            }

        });
    }

</script>