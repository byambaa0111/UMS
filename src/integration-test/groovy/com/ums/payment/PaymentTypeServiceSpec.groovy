package com.ums.payment

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PaymentTypeServiceSpec extends Specification {

    PaymentTypeService paymentTypeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new PaymentType(...).save(flush: true, failOnError: true)
        //new PaymentType(...).save(flush: true, failOnError: true)
        //PaymentType paymentType = new PaymentType(...).save(flush: true, failOnError: true)
        //new PaymentType(...).save(flush: true, failOnError: true)
        //new PaymentType(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //paymentType.id
    }

    void "test get"() {
        setupData()

        expect:
        paymentTypeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<PaymentType> paymentTypeList = paymentTypeService.list(max: 2, offset: 2)

        then:
        paymentTypeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        paymentTypeService.count() == 5
    }

    void "test delete"() {
        Long paymentTypeId = setupData()

        expect:
        paymentTypeService.count() == 5

        when:
        paymentTypeService.delete(paymentTypeId)
        sessionFactory.currentSession.flush()

        then:
        paymentTypeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        PaymentType paymentType = new PaymentType()
        paymentTypeService.save(paymentType)

        then:
        paymentType.id != null
    }
}
