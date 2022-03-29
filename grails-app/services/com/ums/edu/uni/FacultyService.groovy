package com.ums.edu.uni

import grails.gorm.services.Service

@Service(Faculty)
interface FacultyService {

    Faculty get(Serializable id)

    List<Faculty> list(Map args)

    Long count()

    void delete(Serializable id)

    Faculty save(Faculty faculty)

}