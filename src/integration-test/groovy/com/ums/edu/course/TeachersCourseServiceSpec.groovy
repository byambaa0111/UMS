package com.ums.edu.course

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TeachersCourseServiceSpec extends Specification {

    TeachersCourseService teachersCourseService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new TeachersCourse(...).save(flush: true, failOnError: true)
        //new TeachersCourse(...).save(flush: true, failOnError: true)
        //TeachersCourse teachersCourse = new TeachersCourse(...).save(flush: true, failOnError: true)
        //new TeachersCourse(...).save(flush: true, failOnError: true)
        //new TeachersCourse(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //teachersCourse.id
    }

    void "test get"() {
        setupData()

        expect:
        teachersCourseService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<TeachersCourse> teachersCourseList = teachersCourseService.list(max: 2, offset: 2)

        then:
        teachersCourseList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        teachersCourseService.count() == 5
    }

    void "test delete"() {
        Long teachersCourseId = setupData()

        expect:
        teachersCourseService.count() == 5

        when:
        teachersCourseService.delete(teachersCourseId)
        sessionFactory.currentSession.flush()

        then:
        teachersCourseService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        TeachersCourse teachersCourse = new TeachersCourse()
        teachersCourseService.save(teachersCourse)

        then:
        teachersCourse.id != null
    }
}
