package com.ums.edu.course

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ProgrammePlanServiceSpec extends Specification {

    ProgrammePlanService programmePlanService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ProgrammePlan(...).save(flush: true, failOnError: true)
        //new ProgrammePlan(...).save(flush: true, failOnError: true)
        //ProgrammePlan programmePlan = new ProgrammePlan(...).save(flush: true, failOnError: true)
        //new ProgrammePlan(...).save(flush: true, failOnError: true)
        //new ProgrammePlan(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //programmePlan.id
    }

    void "test get"() {
        setupData()

        expect:
        programmePlanService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ProgrammePlan> programmePlanList = programmePlanService.list(max: 2, offset: 2)

        then:
        programmePlanList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        programmePlanService.count() == 5
    }

    void "test delete"() {
        Long programmePlanId = setupData()

        expect:
        programmePlanService.count() == 5

        when:
        programmePlanService.delete(programmePlanId)
        sessionFactory.currentSession.flush()

        then:
        programmePlanService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ProgrammePlan programmePlan = new ProgrammePlan()
        programmePlanService.save(programmePlan)

        then:
        programmePlan.id != null
    }
}
