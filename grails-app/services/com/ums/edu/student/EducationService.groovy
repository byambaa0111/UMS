package com.ums.edu.student

import grails.gorm.services.Service

@Service(Education)
interface EducationService {

    Education get(Serializable id)

    List<Education> list(Map args)

    Long count()

    void delete(Serializable id)

    Education save(Education education)

}