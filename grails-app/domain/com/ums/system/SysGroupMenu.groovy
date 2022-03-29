package com.ums.system

import com.ums.base.BaseDomain

class SysGroupMenu extends BaseDomain{

    SysGroup group
    SysMenu  menu
    String   groupName;
    String   menuName ;
    List     sysMenus   ;
    String   uniquiKey;

    static transients = ['sysMenus']
    static belongsTo = [SysGroup,SysMenu]

    static hasMany = [sysPermissions :SysPermission, sysGroupPermissions:SysGroupPermission]

    public void setSysMenus(List ids) {

        System.out.println("[SET MENUS]"+ids);
        this.sysMenus = ids.collect { SysMenu.get(it) }

    }

    static constraints = {
            groupName blank: true
            menuName blank: true
           sysPermissions display: false
           uniquiKey(display: false,nullable: true,unique: true)
    }
    public void setUniquiKey(){

        uniquiKey = groupName.trim()+menu
    }

    public putMenu(List menus){

        this.sysMenus = menus;

    }
}
