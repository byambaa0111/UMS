package com.ums.edu.course

import grails.gorm.services.Service

@Service(ProgrammePlan)
interface ProgrammePlanService {

    ProgrammePlan get(Serializable id)

    List<ProgrammePlan> list(Map args)

    Long count()

    void delete(Serializable id)

    ProgrammePlan save(ProgrammePlan programmePlan)

}