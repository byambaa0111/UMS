package com.ums.hr

import grails.gorm.services.Service

@Service(FLanguages)
interface FLanguagesService {

    FLanguages get(Serializable id)

    List<FLanguages> list(Map args)

    Long count()

    void delete(Serializable id)

    FLanguages save(FLanguages FLanguages)

}