package com.ums.hr

import grails.gorm.services.Service

@Service(Projects)
interface ProjectsService {

    Projects get(Serializable id)

    List<Projects> list(Map args)

    Long count()

    void delete(Serializable id)

    Projects save(Projects projects)

}