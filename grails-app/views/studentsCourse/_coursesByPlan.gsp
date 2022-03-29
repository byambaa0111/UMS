%{--<g:select  from="${collection}" optionValue="course" optionKey="id" name="coursesInPlan" data-placeholder="Select a faculty" class=" form-control w-100"  >
</g:select>--}%
<%
    def courseInPlanGrouped = courseInPlanList.groupBy({ coursesInPlan -> coursesInPlan.semester })

%>
<select  id="course" multiple="multiple" name="course"  class=" w-100 filter-multi ">
    <g:each in="${grails.util.Holders.config.getProperty("courseInPlan.semester.list",List)}" var="varCol" status="i">
        <optgroup label="${varCol} - Семистр ">
            <g:each in="${courseInPlanGrouped[varCol]}" var="course" status="j">
                <option value="${course?.course.id}">${course?.course}</option>
            </g:each>
        </optgroup>
    </g:each>
</select>

<script>
    $(document).ready(function () {

        //filter-multiple
        $('.filter-multi').multipleSelect({
            filter: true
        })

        $('.select2').select2()
    });
</script>
