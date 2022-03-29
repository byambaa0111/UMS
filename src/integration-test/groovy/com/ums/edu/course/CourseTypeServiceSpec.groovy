package com.ums.edu.course

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CourseTypeServiceSpec extends Specification {

    CourseTypeService courseTypeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CourseType(...).save(flush: true, failOnError: true)
        //new CourseType(...).save(flush: true, failOnError: true)
        //CourseType courseType = new CourseType(...).save(flush: true, failOnError: true)
        //new CourseType(...).save(flush: true, failOnError: true)
        //new CourseType(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //courseType.id
    }

    void "test get"() {
        setupData()

        expect:
        courseTypeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CourseType> courseTypeList = courseTypeService.list(max: 2, offset: 2)

        then:
        courseTypeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        courseTypeService.count() == 5
    }

    void "test delete"() {
        Long courseTypeId = setupData()

        expect:
        courseTypeService.count() == 5

        when:
        courseTypeService.delete(courseTypeId)
        sessionFactory.currentSession.flush()

        then:
        courseTypeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CourseType courseType = new CourseType()
        courseTypeService.save(courseType)

        then:
        courseType.id != null
    }
}
