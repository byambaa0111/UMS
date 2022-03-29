package com.ums.system

import grails.gorm.services.Service

@Service(SysGroup)
interface SysGroupService {

    SysGroup get(Serializable id)

    List<SysGroup> list(Map args)

    Long count()

    void delete(Serializable id)

    SysGroup save(SysGroup sysGroup)

}