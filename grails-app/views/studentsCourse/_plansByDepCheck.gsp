<div class="form-group custom-switches-stacked">
    <label class="form-label">${title}</label>
    <g:if test="${programmePlans.size() < 5}">
      <g:each in="${programmePlans}" var="varVal"  status="i">
          <label class="custom-switch">
              <input type="radio" name="programmePlans" id="programmePlans" value="${varVal.id}"
                     class="custom-switch-input" onchange="loadCourse(this.value)">
              <span class="custom-switch-indicator"></span>
              <span class="custom-switch-description">${varVal}</span>
          </label>
      </g:each>
  </g:if>
    <g:elseif test="${programmePlans.size() >= 5}">
    <g:select  from="${programmePlans}"  optionKey="id" name="programmePlans" data-placeholder="Select a faculty" class="select2 form-control w-100" onchange="loadCourse(this.value)"  ></g:select>
    </g:elseif>


</div>
%{--<g:toggleRadio toggleList="${programmePlans}" name="programmePlans" title="Эфлөбый" methodName="loadCourse()"></g:toggleRadio>--}%
<script>
    $(document).ready(function () {

        //filter-multiple
        $('.filter-multi').multipleSelect({
            filter: true
        })

        $('.select2').select2()
    });
    function loadCourse(id){
        var url ="/studentsCourse/getCoursesByPlan"
        $.ajax({
            type: "POST",
            url: url,
            data:  { 'programmePlanId': id}, // serializes the form's elements.
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
</script>