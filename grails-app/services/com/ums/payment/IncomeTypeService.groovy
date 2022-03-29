package com.ums.payment

import grails.gorm.services.Service

@Service(IncomeType)
interface IncomeTypeService {

    IncomeType get(Serializable id)

    List<IncomeType> list(Map args)

    Long count()

    void delete(Serializable id)

    IncomeType save(IncomeType incomeType)

}