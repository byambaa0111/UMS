package com.ums.edu.exams

import grails.gorm.services.Service

@Service(Exam)
interface ExamService {

    Exam get(Serializable id)

    List<Exam> list(Map args)

    Long count()

    void delete(Serializable id)

    Exam save(Exam exam)

}