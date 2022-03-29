package com.ums.system

import grails.gorm.services.Service

@Service(SysGroupMenu)
interface SysGroupMenuService {

    SysGroupMenu get(Serializable id)

    List<SysGroupMenu> list(Map args)

    Long count()

    void delete(Serializable id)

    SysGroupMenu save(SysGroupMenu sysGroupMenu)

}