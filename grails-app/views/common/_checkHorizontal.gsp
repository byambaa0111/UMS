<div class="form-group">
    <label class="form-label">${title}</label>
    <div class="form-group">
        <g:each in="${toggleList}" var="varCol"  status="i">
                <label >${varCol}</label>
                <input type="checkbox"  name="${name}" onchange="${methodName}()" value="${varCol}" class="flat-blue" checked>
        </g:each>
    </div>
</div>

