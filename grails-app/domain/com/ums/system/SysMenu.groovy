package com.ums.system

import com.ums.base.BaseDomain

class SysMenu extends BaseDomain{

    String menuName
    String controlName
    String actionName
    String menuDesc

    SysMenu topMenu
    List subMenuList

    boolean isHidden;
    static transients = ['subMenuList']

    static belongsTo = [SysMenu,SysGroup]
    
    static hasMany = [sysGroupMenus:SysGroupMenu ,sysMenus: SysMenu]

    static constraints = {
        
        menuName(blank: false, nullable:false)
        controlName(blank: false,nullable:false)
        actionName(blank: false,nullable:false)
        menuDesc(blank: false,nullable:false,widget:'textArea')
        topMenu(blank: true,nullable: true)
        isHidden(nullable:false)
        
    }
    
    String toString() {
		return this.menuName+ "-"+this.controlName
    }
   
  
}
