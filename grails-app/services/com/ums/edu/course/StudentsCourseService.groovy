package com.ums.edu.course

import grails.gorm.services.Service

@Service(StudentsCourse)
interface StudentsCourseService {

    StudentsCourse get(Serializable id)

    List<StudentsCourse> list(Map args)

    Long count()

    void delete(Serializable id)

    StudentsCourse save(StudentsCourse studentsCourse)

}