package com.ums.payment

import grails.gorm.services.Service

@Service(PaymentType)
interface PaymentTypeService {

    PaymentType get(Serializable id)

    List<PaymentType> list(Map args)

    Long count()

    void delete(Serializable id)

    PaymentType save(PaymentType paymentType)

}