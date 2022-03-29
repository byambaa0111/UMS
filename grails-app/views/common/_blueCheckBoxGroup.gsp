<div class="form-group">
    <label class="form-label">${title}</label>
    <g:each in="${collection}" var="varCol" status="i">
        <div class="form-group">
            <input type="checkbox"  name="${name}"  value="${varCol}"  class="flat-blue" checked>
            <label for="${name}">${carCol}</label>
        </div>
    </g:each>
</div>