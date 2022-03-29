package com.ums.edu.course

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ProgrammeServiceSpec extends Specification {

    ProgrammeService programmeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Programme(...).save(flush: true, failOnError: true)
        //new Programme(...).save(flush: true, failOnError: true)
        //Programme programme = new Programme(...).save(flush: true, failOnError: true)
        //new Programme(...).save(flush: true, failOnError: true)
        //new Programme(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //programme.id
    }

    void "test get"() {
        setupData()

        expect:
        programmeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Programme> programmeList = programmeService.list(max: 2, offset: 2)

        then:
        programmeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        programmeService.count() == 5
    }

    void "test delete"() {
        Long programmeId = setupData()

        expect:
        programmeService.count() == 5

        when:
        programmeService.delete(programmeId)
        sessionFactory.currentSession.flush()

        then:
        programmeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Programme programme = new Programme()
        programmeService.save(programme)

        then:
        programme.id != null
    }
}
