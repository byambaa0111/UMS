<div class="form-group overflow-hidden">
    <label>Салбар Сургууль</label>
    <select class="form-control select2 w-100" id="faculties" name="faculties"
            multiple="multiple" data-placeholder="Select a faculty">
        <g:each in="${com.ums.edu.uni.Faculty.list()}" var="faculty" status="i">
            <option value="${faculty?.id}">${faculty?.facultyName}</option>
        </g:each>
    </select>
</div>