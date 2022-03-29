package com.ums.edu.course

import grails.gorm.services.Service

@Service(Programme)
interface ProgrammeService {

    Programme get(Serializable id)

    List<Programme> list(Map args)

    Long count()

    void delete(Serializable id)

    Programme save(Programme programme)

}