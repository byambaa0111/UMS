<div class="form-group ">
    <label class="form-label">Салбар сургууль</label>
    %{--optmulti-select--}%
    <select  ${multiple == "multiple" ? "multiple='multiple'" : "" } id="${name}" name="${name}"  class=" form-control select2 w-100">
        <g:each in="${com.ums.edu.uni.Faculty.list()}" var="varCol" status="i">
            <optgroup label="${varCol.facultyName}">
                <g:each in="${varCol.departments}" var="department" status="j">
                    <option value="${department.id}">${department.departmentCode}</option>
                </g:each>
            </optgroup>
        </g:each>
    </select>
</div>
<script>
 /*   $("#faculty").on('change',function (e){
        var url ="/student/getDepartments"
        // alert(this.value)
        $.ajax({
            type: "POST",
            url: url,
            data:  { 'facultyId': this.value  }, // serializes the form's elements.
            success: function(data)
            {
                // alert(data); // show response from the php script.

                if(data == 'all'){
                    $( "#departmentDiv" ).empty() ;
                }else{
                    $( "#departmentDiv" ).html(data) ;
                }
            },
            error: function(xhr, status, error) {
                $( "#showList" ).addClass( 'alert alert-info' ).append(xhr.responseText);
            }
        });
    });
    */
</script>
