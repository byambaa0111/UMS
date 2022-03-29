%{--8 urlilaar ni tuhain tuluvlugiind baigaa hicheeluudiig jagsaaj baina--}%
<%

    def creditBySem = coursesInPlanList.groupBy({ it -> it.semester }).collect{k,v->[(k):v.creditHour.sum()]}
    def creditByTotal = coursesInPlanList?.sum{it.creditHour}
    def courseInPlanGrouped = coursesInPlanList.groupBy({ coursesInPlan -> coursesInPlan.semester })
    def tcourse = teachersCourseList.groupBy({tCourse -> tCourse.course})

%>
<div class="table-responsive">
    <table class="table table-bordered border-t0 key-buttons text-wrap w-100">
    <thead>
    <tr>
        <th>#</th>
        <th>Course </th>
        <th>Semester</th>
        <th>Credit</th>
        <th>Type</th>
        <th colspan="2">Teacher</th>
    </tr>
    </thead>
        <g:each in="${grails.util.Holders.config.getProperty("courseInPlan.semester.list",List)}" var="sem" status="j">
           <g:if test = "${courseInPlanGrouped[sem] != null}" >
               <tr>
                   <td colspan="5">Semester:${sem}</td>
                   <td colspan="2"></td>
               </tr>
                <g:each in="${courseInPlanGrouped[sem]}" var="course" status="i">
                    <tr>
                        <td>${i+1}</td>
                        <td>${course.course}</td>
                        <td>${course.semester}</td>
                        <td>${course.creditHour}</td>
                        <td>${course.courseType}</td>
                        <td>
                            <g:if test="${tcourse[course.course]}">
                                <g:badge value="${tcourse[course.course][0].teacher?.firstName+"-"+tcourse[course.course][0].teacher?.teacherCode}" type="info"></g:badge>
                            </g:if>
                            <g:else>
                                <g:badge value="Багш сонгоогүй" type="danger"></g:badge>
                            </g:else>
                        </td>
                        <td>
                            <g:select name="teacher"
                                      class="form-control select2"  optionKey="id"
                                      value="${tcourse[course.course] ? tcourse[course.course][0].teacher?.id : ""}"
                                      from="${com.ums.hr.Teacher.list()}" onchange="submitFrm('${course.course?.id}',this.value,${sem})">
                            </g:select>
                        </td>
                    </tr>
                </g:each>
                <tr>
                    <td colspan="3" align="center">Нийт :${courseInPlanGrouped[sem]?.size()}</td>
                    <td>${ courseInPlanGrouped[sem]?.collect{it.creditHour}?.value?.sum() }</td>
                    <td colspan="3"></td>
                </tr>

           </g:if>
        </g:each>
        <tr>
            <td colspan="3">Нийт хичээл: ${coursesInPlanList?.size()}</td>
            <td colspan="3">Нийт кредит: ${creditByTotal}</td>
        </tr>
</table>
</div>
<script>
    $(document).ready(function () {
        /*left menu hid hiij baina*/
        $('.select2').select2()
    });
</script>