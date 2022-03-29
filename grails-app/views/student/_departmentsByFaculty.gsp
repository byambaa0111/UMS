<div class="form-group" >
<label class="form-label">Хөтөлбөрийн баг</label>
<g:select class="form-control select2 w-100" style="font-size: medium" noSelection="['all':'Бүгд']" name="department" id="department"
          from="${departments}"
          optionKey="id"
          optionValue="departmentNameMN"
/>
</div>
<script>
    $("#department").on('change',function (e){
        var url ="/student/getProgrammes"
        $.ajax({
            type: "POST",
            url: url,
            data:  { 'departmentId': this.value  }, // serializes the form's elements.
            success: function(data)
            {
                // alert(data); // show response from the php script.
                if(data=='all'){
                    $( "#programmeDiv" ).empty();
                }else{
                    $( "#programmeDiv" ).html(data);
                }

            },
            error: function(xhr, status, error) {
                $( "#showList" ).addClass( 'alert alert-info' ).append(xhr.responseText);
            }
        });
    });

</script>
%{--
<label class="form-label">Салбар сургууль</label>
<select multiple="multiple" name="departments" class="optmulti-select   w-100" >
    <g:each in="${departments}" var="department" status="i">
        <optgroup label="${department?.departmentNameMN}">
            <g:each in="${departments.curriculums}" var="curriculum" status="j">
                <option value="${curriculum.id}">${curriculum.curriculumEN}</option>
            </g:each>
        </optgroup>
    </g:each>
</select>--}%
