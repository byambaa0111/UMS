package com.ums.edu.course

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CoursesInPlanServiceSpec extends Specification {

    CoursesInPlanService coursesInPlanService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CoursesInPlan(...).save(flush: true, failOnError: true)
        //new CoursesInPlan(...).save(flush: true, failOnError: true)
        //CoursesInPlan coursesInPlan = new CoursesInPlan(...).save(flush: true, failOnError: true)
        //new CoursesInPlan(...).save(flush: true, failOnError: true)
        //new CoursesInPlan(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //coursesInPlan.id
    }

    void "test get"() {
        setupData()

        expect:
        coursesInPlanService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CoursesInPlan> coursesInPlanList = coursesInPlanService.list(max: 2, offset: 2)

        then:
        coursesInPlanList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        coursesInPlanService.count() == 5
    }

    void "test delete"() {
        Long coursesInPlanId = setupData()

        expect:
        coursesInPlanService.count() == 5

        when:
        coursesInPlanService.delete(coursesInPlanId)
        sessionFactory.currentSession.flush()

        then:
        coursesInPlanService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CoursesInPlan coursesInPlan = new CoursesInPlan()
        coursesInPlanService.save(coursesInPlan)

        then:
        coursesInPlan.id != null
    }
}
