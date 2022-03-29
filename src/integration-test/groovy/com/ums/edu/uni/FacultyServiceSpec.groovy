package com.ums.edu.uni

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class FacultyServiceSpec extends Specification {

    FacultyService facultyService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Faculty(...).save(flush: true, failOnError: true)
        //new Faculty(...).save(flush: true, failOnError: true)
        //Faculty faculty = new Faculty(...).save(flush: true, failOnError: true)
        //new Faculty(...).save(flush: true, failOnError: true)
        //new Faculty(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //faculty.id
    }

    void "test get"() {
        setupData()

        expect:
        facultyService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Faculty> facultyList = facultyService.list(max: 2, offset: 2)

        then:
        facultyList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        facultyService.count() == 5
    }

    void "test delete"() {
        Long facultyId = setupData()

        expect:
        facultyService.count() == 5

        when:
        facultyService.delete(facultyId)
        sessionFactory.currentSession.flush()

        then:
        facultyService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Faculty faculty = new Faculty()
        facultyService.save(faculty)

        then:
        faculty.id != null
    }
}
