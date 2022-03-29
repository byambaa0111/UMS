%{--8 urlilaar ni tuhain tuluvlugiind baigaa hicheeluudiig jagsaaj baina--}%
<%

    def creditBySem = coursesInPlanList.groupBy({ it -> it.semester }).collect{k,v->[(k):v.creditHour.sum()]}
    def creditByTotal = coursesInPlanList?.sum{it.creditHour}
    def courseInPlanGrouped = coursesInPlanList.groupBy({ coursesInPlan -> coursesInPlan.semester })

%>

<div class="table-responsive">
    <table class="table table-bordered border-t0 key-buttons text-nowrap w-100">
    <thead>
    <tr>
        <th>#</th>
        <th>Course </th>
        <th>Semester</th>
        <th>Credit</th>
        <th>Type</th>
    </tr>
    </thead>
        <g:each in="${grails.util.Holders.config.getProperty("courseInPlan.semester.list",List)}" var="sem" status="j">
           <g:if test = "${courseInPlanGrouped[sem] != null}" >
               <tr>
                   <td colspan="5">Semester:${sem}</td>
               </tr>
                <g:each in="${courseInPlanGrouped[sem]}" var="course" status="i">
                    <tr>
                        <td>${i+1}</td>
                        <td>${course.course}</td>
                        <td>${course.semester}</td>
                        <td>${course.creditHour}</td>
                        <td>${course.courseType}</td>
                    </tr>
                </g:each>
                <tr>
                    <td colspan="3" align="center">Нийт :${courseInPlanGrouped[sem]?.size()}</td>
                    
                    <td >${ courseInPlanGrouped[sem]?.collect{it.creditHour}?.value?.sum() }</td>
                    <td ></td>
                </tr>

           </g:if>
        </g:each>
        <tr>
            <td colspan="3">Нийт хичээл: ${coursesInPlanList?.size()}</td>
            <td colspan="3">Нийт кредит: ${creditByTotal}</td>
        </tr>
</table>
</div>