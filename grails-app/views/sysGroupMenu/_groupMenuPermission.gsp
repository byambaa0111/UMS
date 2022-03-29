<%@ page import="helper.FunctionHelper; com.ums.system.SysGroupMenuPermission; com.ums.system.SysGroupMenuPermission; com.ums.system.SysPermission; com.ums.system.SysGroupMenu" %>
<%  helper.FunctionHelper helper = new FunctionHelper();     %>
    <table  class="table table-striped table-bordered mb-0 text-nowrap ">
        <thead>
        <tr class="w-100 " >
            <th><g:message code="sysGroupMenu.menu.label" default="Menu" /></th>
            <th><g:message code="sysGroupMenu.menu.label" default="Controller" />/<g:message code="sysGroupMenu.menu.label" default="Action" /></th>
            <th><g:message code="sysGroupMenu.menu.label" default="Actions" /></th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${sysGroupMenuInstanceList}" status="i" var="sysGroupMenuInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td><g:link action="show" id="${sysGroupMenuInstance.id}">${fieldValue(bean: sysGroupMenuInstance, field: "menu")}</g:link></td>
                <td>${fieldValue(bean: sysGroupMenuInstance, field: "menu.controlName")}/${fieldValue(bean: sysGroupMenuInstance, field: "menu.actionName")}</td>

                <td >
                    <g:hiddenField name="${fieldValue(bean: sysGroupMenuInstance, field: "menu.controlName") }" value="testAA" />
                    <g:hiddenField name="${fieldValue(bean: sysGroupMenuInstance, field: "menu.controlName") }" value="testBB" />
                    <ul>
                    <g:each in="${helper.getActionsByController().get(fieldValue(bean: sysGroupMenuInstance, field: "menu.controlName"))}" status="j" var="action">
                        <li>
                        <g:checkBox  name="${fieldValue(bean: sysGroupMenuInstance, field: "menu.controlName") }" value=" ${ action }"
                                    checked="${SysGroupMenuPermission.findByUniquiKey(sysGroupMenuInstance.groupName+sysGroupMenuInstance.menu.controlName+action)?.isTrue}"
                                    onchange="savePermistion([actionname=this.value,controllername=this.name,groupId=${fieldValue(bean: sysGroupMenuInstance, field: "group.id")},menuid=${fieldValue(bean: sysGroupMenuInstance, field: "menu.id")} ])"/>
                        ${action}
                        </li>
                        %{--${(j % 2) == 0 ? ' ' : ''}--}%
                    </g:each>
%{----}%

                    </ul>

                </td>
                %{--  onchange="${remoteFunction(action:'savePermission',update:[success:'greate',failure:'ohno'],params:'\'actionname=\' + this.value + \'&testid = \'+1+ \'&controllername=\'+this.name+ \'&groupId=\'+'+fieldValue(bean: sysGroupMenuInstance, field: "group.id")+'+\'&menuid=\'+'+fieldValue(bean: sysGroupMenuInstance, field: "menu.id")+ '' ) }"                --}%

            </tr>
        </g:each>
        </tbody>
    </table>

<g:javascript>
  function savePermistion(data){

    $.ajax({
      url:'${g.createLink( controller:'sysGroupMenu', action:'savePermission')}',
      data:{
          actionName:data[0],
          controllerName:data[1],
          groupId:data[2],
          menuId:data[3]
      },
      success:function(d){
          //$( "#permissionTable" ).addClass( 'lds-circle' ).append( 'Successfully saved event' )
        // Your code.<div class="lds-circle"><div>
      },
        beforeSend: function(){

            // Code to display spinner
            //  $( "#permissionTable" ).addClass( 'lds-circle' ).append( 'Successfully saved event' )
        },
        complete: function(){
            // Code to hide spinner.
             //$( "#permissionTable" ).removeClass( 'lds-circle' ).append( 'Successfully saved event' )
        }

    });
  }
</g:javascript>
