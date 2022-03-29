%{--show content hiisen uyed duudagdna--}%

            <div class="form-group row" >
                <g:each in="${domainProperties}" var="p" status="i">


                                    <label id="${p.name}-label" class="form-label" style="text-align: right">
                                            <g:message code="${domainClass.decapitalizedName}.${p.name}.label" default="${p.defaultLabel}" />:
                                    </label>
                            <div class="col-md-4 col-sm-12 col-xs-12 ">
                                %{--${domainClass.decapitalizedName}/${p.name}--}%
                                <f:display bean="${domainClass.decapitalizedName}" property="${p.name}"/>
                                %{--<f:field bean="${domainClass.decapitalizedName}" property="${p.name}"/>--}%
%{--                                <f:field bean="${domainClass.decapitalizedName}" property="${p.name}"/>--}%
                                %{--<div placeholder=".col-md-3" class="form-label" aria-labelledby="${p.name}-label">${body(p)}</div>--}%
                            </div>

                </g:each>
            </div>



    %{--<ol class="property-list ${domainClass.decapitalizedName}">
        <g:each in="${domainProperties}" var="p">
            <div class="form-group row">
                <li class="fieldcontain">
                    <span id="${p.name}-label" class="col-md-3 form-label">

                        <g:message code="${domainClass.decapitalizedName}.${p.name}.label" default="${p.defaultLabel}" />:

                    </span>

                    <div class="property-value" aria-labelledby="${p.name}-label">${body(p)}</div>
                </li>
            </div>
        </g:each>
    </ol>--}%
  %{--  <g:each in="${domainClass.persistentProperties}" var="p">
        <div class="row">
            <div class="col-sm-5 property-label" id="${p.name}-label">
                <strong><g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}"/>:</strong>
            </div>
            <div class="col-sm-7 property-value" aria-labelledby="${p.name}-label">${body(p)}</div>
        </div>
    </g:each>--}%
