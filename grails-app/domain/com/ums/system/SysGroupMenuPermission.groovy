package com.ums.system

import com.ums.base.BaseDomain

class SysGroupMenuPermission extends BaseDomain{

    SysGroupMenu sysGroupMenu

    SysMenu menu

    String groupName;

    String controllerName

    String actionName

    boolean isTrue

    String uniquiKey


    static belongsTo = []

    static hasMany = []

    static constraints = {

        uniquiKey(nullable: false,unique: true)

    }

    void setUniquiKey(){
        uniquiKey = groupName?.trim()+controllerName?.trim()+actionName?.trim()
    }

}
