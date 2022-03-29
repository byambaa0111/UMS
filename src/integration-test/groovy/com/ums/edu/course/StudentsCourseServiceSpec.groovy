package com.ums.edu.course

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class StudentsCourseServiceSpec extends Specification {

    StudentsCourseService studentsCourseService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new StudentsCourse(...).save(flush: true, failOnError: true)
        //new StudentsCourse(...).save(flush: true, failOnError: true)
        //StudentsCourse studentsCourse = new StudentsCourse(...).save(flush: true, failOnError: true)
        //new StudentsCourse(...).save(flush: true, failOnError: true)
        //new StudentsCourse(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //studentsCourse.id
    }

    void "test get"() {
        setupData()

        expect:
        studentsCourseService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<StudentsCourse> studentsCourseList = studentsCourseService.list(max: 2, offset: 2)

        then:
        studentsCourseList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        studentsCourseService.count() == 5
    }

    void "test delete"() {
        Long studentsCourseId = setupData()

        expect:
        studentsCourseService.count() == 5

        when:
        studentsCourseService.delete(studentsCourseId)
        sessionFactory.currentSession.flush()

        then:
        studentsCourseService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        StudentsCourse studentsCourse = new StudentsCourse()
        studentsCourseService.save(studentsCourse)

        then:
        studentsCourse.id != null
    }
}
