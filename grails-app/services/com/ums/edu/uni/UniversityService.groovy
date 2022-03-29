package com.ums.edu.uni

import grails.gorm.services.Service

@Service(University)
interface UniversityService {

    University get(Serializable id)

    List<University> list(Map args)

    Long count()

    void delete(Serializable id)

    University save(University university)

}