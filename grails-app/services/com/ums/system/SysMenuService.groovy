package com.ums.system

import grails.gorm.services.Service

@Service(SysMenu)
interface SysMenuService {

    SysMenu get(Serializable id)

    List<SysMenu> list(Map args)

    Long count()

    void delete(Serializable id)

    SysMenu save(SysMenu sysMenu)

}