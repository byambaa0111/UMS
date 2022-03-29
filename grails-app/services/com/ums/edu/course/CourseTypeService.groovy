package com.ums.edu.course

import grails.gorm.services.Service

@Service(CourseType)
interface CourseTypeService {

    CourseType get(Serializable id)

    List<CourseType> list(Map args)

    Long count()

    void delete(Serializable id)

    CourseType save(CourseType courseType)

}