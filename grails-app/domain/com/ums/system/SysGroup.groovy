package com.ums.system

import com.ums.base.BaseDomain

class SysGroup extends BaseDomain{

    String  groupName;
    String  groupDesc;
    String  homePage;
    
    static hasMany = [sysUsers :SysUser,sysGroupMenus:SysGroupMenu,sysMenus:SysMenu]


    static constraints = {
        
        groupName(nullable:false)
        groupDesc(nullable:false)
        
    }
    String toString(){
        return this.groupName;
    }
}

