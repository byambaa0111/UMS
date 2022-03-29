       %{--when diplay tag colled buyu table tag duudagdahad--}%
       %{--<label for="${property}">${label} ${required ? '*' : ''}</label>--}%
       %{-- <f:field property="${property}"  class="form-control"/>--}%
       %{-- <div class="col-md-3 form-label" aria-labelledby="${property}-label"> </div>--}%

          <input type="checkbox" value="${value}" class="flat-blue" checked="${value}">
            <g:if test="${errors}">
                <g:each in="${errors}" var="error">
                    <span class="help-block"><g:message error="${error}"/></span>
                </g:each>
            </g:if>

