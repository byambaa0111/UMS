package com.ums.hr

import grails.gorm.services.Service

@Service(Experiences)
interface ExperiencesService {

    Experiences get(Serializable id)

    List<Experiences> list(Map args)

    Long count()

    void delete(Serializable id)

    Experiences save(Experiences experiences)

}