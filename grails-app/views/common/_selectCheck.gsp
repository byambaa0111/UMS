<div class="form-group">
    <label>${title}</label>
    <select multiple="multiple" name="${name}" data-placeholder="Select a faculty" class="filter-multi" onselect="alert()">
        <g:each in="${toggleList}" var="valCol" status="i">
            <option value="${valCol?.id}">${valCol}</option>
        </g:each>
    </select>
</div>