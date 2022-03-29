package com.ums.system
class SysPermission {

    String permission
    String permissionKey
    String description
    
    boolean active
    

    static hasMany = [groupPermissions:SysGroupPermission]
    
    static constraints = {
    
        permission(nullable:false)
        permissionKey(nullable:false)
        description(nullable:false)
        
    }

    String toString(){
        return this.permission
    }

}
