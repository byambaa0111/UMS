package com.ums.system

import grails.gorm.services.Service

@Service(SysGroupMenuPermission)
interface SysGroupMenuPermissionService {

    SysGroupMenuPermission get(Serializable id)

    List<SysGroupMenuPermission> list(Map args)

    Long count()

    void delete(Serializable id)

    SysGroupMenuPermission save(SysGroupMenuPermission sysGroupMenuPermission)

}