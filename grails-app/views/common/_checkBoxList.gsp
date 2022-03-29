<div class="form-group">
    <label class="form-label">${title}</label>
    <g:each in="${toggleList}" var="varCol"  status="i">
        <div class="form-group">
            <input type="checkbox"  name="${name}" onchange="${methodName}()" value="${varCol}" class="flat-blue" checked>
            <label >${varCol}</label>
        </div>
    </g:each>
</div>

