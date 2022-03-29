package com.ums.edu.course

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ProgramePlanServiceSpec extends Specification {

    ProgramePlanService programePlanService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ProgrammePlan(...).save(flush: true, failOnError: true)
        //new ProgrammePlan(...).save(flush: true, failOnError: true)
        //ProgrammePlan programePlan = new ProgrammePlan(...).save(flush: true, failOnError: true)
        //new ProgrammePlan(...).save(flush: true, failOnError: true)
        //new ProgrammePlan(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //programePlan.id
    }

    void "test get"() {
        setupData()

        expect:
        programePlanService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ProgrammePlan> programePlanList = programePlanService.list(max: 2, offset: 2)

        then:
        programePlanList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        programePlanService.count() == 5
    }

    void "test delete"() {
        Long programePlanId = setupData()

        expect:
        programePlanService.count() == 5

        when:
        programePlanService.delete(programePlanId)
        sessionFactory.currentSession.flush()

        then:
        programePlanService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ProgrammePlan programePlan = new ProgrammePlan()
        programePlanService.save(programePlan)

        then:
        programePlan.id != null
    }
}
