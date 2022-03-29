package com.ums.edu.exams

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ExamTypeServiceSpec extends Specification {

    ExamTypeService examTypeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ExamType(...).save(flush: true, failOnError: true)
        //new ExamType(...).save(flush: true, failOnError: true)
        //ExamType examType = new ExamType(...).save(flush: true, failOnError: true)
        //new ExamType(...).save(flush: true, failOnError: true)
        //new ExamType(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //examType.id
    }

    void "test get"() {
        setupData()

        expect:
        examTypeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ExamType> examTypeList = examTypeService.list(max: 2, offset: 2)

        then:
        examTypeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        examTypeService.count() == 5
    }

    void "test delete"() {
        Long examTypeId = setupData()

        expect:
        examTypeService.count() == 5

        when:
        examTypeService.delete(examTypeId)
        sessionFactory.currentSession.flush()

        then:
        examTypeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ExamType examType = new ExamType()
        examTypeService.save(examType)

        then:
        examType.id != null
    }
}
