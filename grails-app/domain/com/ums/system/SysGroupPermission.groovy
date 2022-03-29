package com.ums.system
class SysGroupPermission {
    
    SysPermission permission
    SysGroupMenu  groupMenu

    static belongsTo = [SysPermission,SysGroupMenu]
    
}
