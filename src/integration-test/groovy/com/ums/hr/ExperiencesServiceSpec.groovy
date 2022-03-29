package com.ums.hr

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ExperiencesServiceSpec extends Specification {

    ExperiencesService experiencesService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Experiences(...).save(flush: true, failOnError: true)
        //new Experiences(...).save(flush: true, failOnError: true)
        //Experiences experiences = new Experiences(...).save(flush: true, failOnError: true)
        //new Experiences(...).save(flush: true, failOnError: true)
        //new Experiences(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //experiences.id
    }

    void "test get"() {
        setupData()

        expect:
        experiencesService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Experiences> experiencesList = experiencesService.list(max: 2, offset: 2)

        then:
        experiencesList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        experiencesService.count() == 5
    }

    void "test delete"() {
        Long experiencesId = setupData()

        expect:
        experiencesService.count() == 5

        when:
        experiencesService.delete(experiencesId)
        sessionFactory.currentSession.flush()

        then:
        experiencesService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Experiences experiences = new Experiences()
        experiencesService.save(experiences)

        then:
        experiences.id != null
    }
}
