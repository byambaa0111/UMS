<div class="table-responsive">
<table  class="table table-bordered border-t0 key-buttons flex-sm-wrap"  >

  <thead>
      <tr class="text-nowrap" style="white-space: pre-wrap">
                <g:each in="${domainProperties}" var="p" status="i">
                    %{--<g:sortableColumn property="${p.property}" title="${p.label}" />--}%
                    <th> ${p.label} </th>
                </g:each>
            </tr>
        </thead>
        <tbody>
        <g:each in="${collection}" var="bean" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}" >
                <g:each in="${domainProperties}" var="p" status="j">
                    <g:if test="${j==0}">
                        <td >
                            <g:link method="GET" resource="${bean}">
                                <f:display bean="${bean}" property="${p.property}" displayStyle="${displayStyle?:'table'}" theme="${theme}"/>
                            </g:link>
                        </td>
                    </g:if>
                    <g:else>
                        <td><f:display bean="${bean}" property="${p.property}"  displayStyle="${displayStyle?:'table'}" theme="${theme}"/></td>
                    </g:else>
                </g:each>

            </tr>
        </g:each>

        </tbody>
    </table>
</div>