<%@ page import="com.ums.system.SysGroupMenu"  %>
<%@ page import= "com.ums.system.SysGroup"  %>

<div class="col-md-1 col-sm-12 col-xs-12 form-group">
	<label class="form-label" placeholder=".col-md-3" for="group.id"><g:message code="sysGroupMenu.group.label" default="Group" /> ${required ? '*' : ''}</label>
</div>
<div class="col-md-3 col-sm-12 col-xs-12 form-group">
	<div class="selectgroup selectgroup-pills">
		<g:each in="${SysGroup.list()}" var="groupOf" status="i">
			<label class="selectgroup-item">
				<input type="checkbox" name="groupOf"  value="${groupOf.groupName}" class="selectgroup-input" >
				<span class="selectgroup-button">${groupOf.groupName}</span>
			</label>
		</g:each>
	</div>
	<g:select class="form-control select2 w-100" name="id"
			  from="${SysGroup.list()}"
			  optionKey="id"
			  optionValue="groupName"
		onchange="loadMenu([id=this.options[ this.selectedIndex ].value])"
	/>
	<g:if test="${errors}">
		<g:each in="${errors}" var="error">
			<span class="help-block"><g:message error="${error}"/></span>
		</g:each>
	</g:if>

</div>
%{--empty form field--}%
<div class="col-md-1 col-sm-12 col-xs-12 form-group">
	<label class="form-label" placeholder=".col-md-3" for="${property}"><g:message code="sysGroupMenu.menu.label" default="Menu" /> ${required ? '*' : ''}</label>
</div>

<div class="col-md-7 col-sm-12 col-xs-12 form-group">
	<div id="menuTree">
		<g:if test="${sysGroupMenuInstanceList}">
			<g:render template="sysMenus" model="[sysGroupMenuInstanceList:sysGroupMenuInstanceList]"/>
		</g:if>
	</div>
</div>
<g:javascript>
  function loadMenu(data){

    $.ajax({
      url:'${g.createLink( controller:'sysGroupMenu', action:'search')}',
      data:{
          id:data[0],
      },
      success:function(returnData){
          $( "#menuTree" ).html(returnData)
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


