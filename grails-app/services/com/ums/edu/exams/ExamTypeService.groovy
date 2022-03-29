package com.ums.edu.exams

import grails.gorm.services.Service

@Service(ExamType)
interface ExamTypeService {

    ExamType get(Serializable id)

    List<ExamType> list(Map args)

    Long count()

    void delete(Serializable id)

    ExamType save(ExamType examType)

}