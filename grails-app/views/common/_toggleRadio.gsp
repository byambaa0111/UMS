<div class="form-group custom-switches-stacked">
    <label class="form-label">${title}</label>
    <g:each in="${toggleList}" var="gender"  status="i">
        <label class="custom-switch">
            <input type="radio" name="${name}" id="${name}" value="${gender}"
                   class="custom-switch-input" onchange="${methodName}()">
            <span class="custom-switch-indicator"></span>
            <span class="custom-switch-description">${gender}</span>
        </label>
    </g:each>
    <label class="custom-switch">
        <input type="radio" name="${name}" value="all" checked="true"
               onclick="${methodName}()" class="custom-switch-input">
        <span class="custom-switch-indicator"></span>
        <span class="custom-switch-description">Бүгд</span>
    </label>
</div>