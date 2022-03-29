<div class="form-group mb-0">
    <label class="form-label">${title}</label>
        <g:each in="${toggleList}" var="varCol"  status="i">
            <label>
                <input type="radio"  name="${name}" value="${varCol}" onchange="${methodName}()" class="flat-blue" >
                ${varCol}
            </label>
        </g:each>
</div>
