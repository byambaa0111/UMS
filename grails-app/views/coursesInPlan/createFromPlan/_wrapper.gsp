%{--засвар хийхэд гарч ирж байна--}%
%{--field tag duudagdah garch irj baina--}%
    <div class="form-group">

            <label class="form-label"   placeholder="fdsa" for="${property}">${label} ${required ? '*' : ''}</label>
            <f:widget placeholder="" bean="${domainClass}" property="${property}" class="form-control"/>

        <g:if test="${errors}">
            <g:each in="${errors}" var="error">
                <span class="help-block"><g:message error="${error}"/></span>
            </g:each>
        </g:if>

    </div>
