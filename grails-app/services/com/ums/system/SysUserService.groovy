package com.ums.system

import grails.gorm.services.Service

@Service(SysUser)
interface SysUserService {

    SysUser get(Serializable id)

    List<SysUser> list(Map args)

    Long count()

    void delete(Serializable id)

    SysUser save(SysUser sysUser)

}