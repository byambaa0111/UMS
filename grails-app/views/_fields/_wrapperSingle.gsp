%{--засвар хийхэд гарч ирж байна--}%
%{--field tag duudagdah garch irj baina--}%
    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
            <label class="form-label" style="text-align: right"  placeholder=".col-md-3" for="${property}">${label} ${required ? '*' : ''}</label>
    </div>
    <div class="col-md-3 col-sm-12 col-xs-12 form-group">
        <f:widget placeholder="" bean="${domainClass}" property="${property}" class="form-control"/>
        <g:if test="${errors}">
            <g:each in="${errors}" var="error">
                <span class="help-block"><g:message error="${error}"/></span>
            </g:each>
        </g:if>

    </div>
