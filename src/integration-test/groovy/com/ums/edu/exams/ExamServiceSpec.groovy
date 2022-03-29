package com.ums.edu.exams

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ExamServiceSpec extends Specification {

    ExamService examService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Exam(...).save(flush: true, failOnError: true)
        //new Exam(...).save(flush: true, failOnError: true)
        //Exam exam = new Exam(...).save(flush: true, failOnError: true)
        //new Exam(...).save(flush: true, failOnError: true)
        //new Exam(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //exam.id
    }

    void "test get"() {
        setupData()

        expect:
        examService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Exam> examList = examService.list(max: 2, offset: 2)

        then:
        examList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        examService.count() == 5
    }

    void "test delete"() {
        Long examId = setupData()

        expect:
        examService.count() == 5

        when:
        examService.delete(examId)
        sessionFactory.currentSession.flush()

        then:
        examService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Exam exam = new Exam()
        examService.save(exam)

        then:
        exam.id != null
    }
}
