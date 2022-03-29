

<g:checkBoxListTree name="sysMenus"  from="${session["allmenu"]}" value="${sysGroupMenuInstanceList != null ? sysGroupMenuInstanceList.sysMenus : null}" optionKey="id"/>

<g:each in="${sysGroupMenuInstanceList}" status="i" var="BB">
    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
        <td>${fieldValue(bean:BB, field:'groupName')}</td>
    </tr>
</g:each>
   