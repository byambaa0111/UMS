package com.ums.edu.course

import grails.gorm.services.Service

@Service(TeachersCourse)
interface TeachersCourseService {

    TeachersCourse get(Serializable id)

    List<TeachersCourse> list(Map args)

    Long count()

    void delete(Serializable id)

    TeachersCourse save(TeachersCourse teachersCourse)

}