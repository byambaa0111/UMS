package com.ums.edu.uni

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class UniversityServiceSpec extends Specification {

    UniversityService universityService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new University(...).save(flush: true, failOnError: true)
        //new University(...).save(flush: true, failOnError: true)
        //University university = new University(...).save(flush: true, failOnError: true)
        //new University(...).save(flush: true, failOnError: true)
        //new University(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //university.id
    }

    void "test get"() {
        setupData()

        expect:
        universityService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<University> universityList = universityService.list(max: 2, offset: 2)

        then:
        universityList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        universityService.count() == 5
    }

    void "test delete"() {
        Long universityId = setupData()

        expect:
        universityService.count() == 5

        when:
        universityService.delete(universityId)
        sessionFactory.currentSession.flush()

        then:
        universityService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        University university = new University()
        universityService.save(university)

        then:
        university.id != null
    }
}
