package com.ums.payment

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class IncomeTypeServiceSpec extends Specification {

    IncomeTypeService incomeTypeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new IncomeType(...).save(flush: true, failOnError: true)
        //new IncomeType(...).save(flush: true, failOnError: true)
        //IncomeType incomeType = new IncomeType(...).save(flush: true, failOnError: true)
        //new IncomeType(...).save(flush: true, failOnError: true)
        //new IncomeType(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //incomeType.id
    }

    void "test get"() {
        setupData()

        expect:
        incomeTypeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<IncomeType> incomeTypeList = incomeTypeService.list(max: 2, offset: 2)

        then:
        incomeTypeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        incomeTypeService.count() == 5
    }

    void "test delete"() {
        Long incomeTypeId = setupData()

        expect:
        incomeTypeService.count() == 5

        when:
        incomeTypeService.delete(incomeTypeId)
        sessionFactory.currentSession.flush()

        then:
        incomeTypeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        IncomeType incomeType = new IncomeType()
        incomeTypeService.save(incomeType)

        then:
        incomeType.id != null
    }
}
