package com.ums.edu.student

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class EducationServiceSpec extends Specification {

    EducationService educationService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Education(...).save(flush: true, failOnError: true)
        //new Education(...).save(flush: true, failOnError: true)
        //Education education = new Education(...).save(flush: true, failOnError: true)
        //new Education(...).save(flush: true, failOnError: true)
        //new Education(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //education.id
    }

    void "test get"() {
        setupData()

        expect:
        educationService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Education> educationList = educationService.list(max: 2, offset: 2)

        then:
        educationList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        educationService.count() == 5
    }

    void "test delete"() {
        Long educationId = setupData()

        expect:
        educationService.count() == 5

        when:
        educationService.delete(educationId)
        sessionFactory.currentSession.flush()

        then:
        educationService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Education education = new Education()
        educationService.save(education)

        then:
        education.id != null
    }
}
