<div class="form-group">
    <label class="form-label">${title}</label>
    <div class="selectgroup selectgroup-pills">
        <g:each in="${toggleList}" var="varCol" status="i">
            <label class="selectgroup-item">
                <input type="checkbox" name="${name}"  value="${varCol}" onselect="${methodName}()" class="selectgroup-input" >
                <span class="selectgroup-button">${varCol}</span>
            </label>
        </g:each>
        <label class="selectgroup-item">
            <input type="checkbox" name="${name}"  value="all" class="selectgroup-input" checked>
            <span class="selectgroup-button" >All</span>
        </label>
    </div>
</div>