package com.ums.hr

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class FLanguagesServiceSpec extends Specification {

    FLanguagesService FLanguagesService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new FLanguages(...).save(flush: true, failOnError: true)
        //new FLanguages(...).save(flush: true, failOnError: true)
        //FLanguages FLanguages = new FLanguages(...).save(flush: true, failOnError: true)
        //new FLanguages(...).save(flush: true, failOnError: true)
        //new FLanguages(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //FLanguages.id
    }

    void "test get"() {
        setupData()

        expect:
        FLanguagesService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<FLanguages> FLanguagesList = FLanguagesService.list(max: 2, offset: 2)

        then:
        FLanguagesList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        FLanguagesService.count() == 5
    }

    void "test delete"() {
        Long FLanguagesId = setupData()

        expect:
        FLanguagesService.count() == 5

        when:
        FLanguagesService.delete(FLanguagesId)
        sessionFactory.currentSession.flush()

        then:
        FLanguagesService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        FLanguages FLanguages = new FLanguages()
        FLanguagesService.save(FLanguages)

        then:
        FLanguages.id != null
    }
}
