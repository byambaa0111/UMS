<div class="card">
    <div class="card-header">
        <h4><g:message code="default.list.label" args="[]"  ></g:message></h4>
    </div>
    <div class="card-body">
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <div class="row">
            <g:each in="${teachersCourseList}" var="teacher" status="i">
                <%
                   def tcList =  teacherAndCourseMap.get(teacher).groupBy{ tc -> tc.semester}.collectEntries{ k,v->[(k):v.course.totalCredit.sum()]} //{// String.format("%s:'%s'", it.key, it.value.course.totalCredit.sum()) }
                   def tcList2 =  teacherAndCourseMap.get(teacher).groupBy{tc -> tc.semester}
                   def tcList3 =  teacherTotalGreditMap.get(teacher).sum {it.course.totalCredit}
                    Map map = [:].withDefault { key -> return [] }
                %>

                <g:boxColor title="${teacher}" objectName="${tcList}" value="${tcList3 ? tcList3 : 0 }"  objectValue="85"  ></g:boxColor>
            </g:each>
        </div>
    </div>
</div>
