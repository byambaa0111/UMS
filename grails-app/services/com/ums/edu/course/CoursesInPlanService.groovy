package com.ums.edu.course

import grails.gorm.services.Service

@Service(CoursesInPlan)
interface CoursesInPlanService {

    CoursesInPlan get(Serializable id)

    List<CoursesInPlan> list(Map args)

    Long count()

    void delete(Serializable id)

    CoursesInPlan save(CoursesInPlan coursesInPlan)

}