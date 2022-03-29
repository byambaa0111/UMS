package com.ums.edu.course

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CurriculumServiceSpec extends Specification {

    CurriculumService curriculumService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Programme(...).save(flush: true, failOnError: true)
        //new Programme(...).save(flush: true, failOnError: true)
        //Programme curriculum = new Programme(...).save(flush: true, failOnError: true)
        //new Programme(...).save(flush: true, failOnError: true)
        //new Programme(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //curriculum.id
    }

    void "test get"() {
        setupData()

        expect:
        curriculumService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Programme> curriculumList = curriculumService.list(max: 2, offset: 2)

        then:
        curriculumList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        curriculumService.count() == 5
    }

    void "test delete"() {
        Long curriculumId = setupData()

        expect:
        curriculumService.count() == 5

        when:
        curriculumService.delete(curriculumId)
        sessionFactory.currentSession.flush()

        then:
        curriculumService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Programme curriculum = new Programme()
        curriculumService.save(curriculum)

        then:
        curriculum.id != null
    }
}
