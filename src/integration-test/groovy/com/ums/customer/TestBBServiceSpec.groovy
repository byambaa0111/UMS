package com.ums.customer

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TestBBServiceSpec extends Specification {

    TestBBService testBBService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new TestBB(...).save(flush: true, failOnError: true)
        //new TestBB(...).save(flush: true, failOnError: true)
        //TestBB testBB = new TestBB(...).save(flush: true, failOnError: true)
        //new TestBB(...).save(flush: true, failOnError: true)
        //new TestBB(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //testBB.id
    }

    void "test get"() {
        setupData()

        expect:
        testBBService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<TestBB> testBBList = testBBService.list(max: 2, offset: 2)

        then:
        testBBList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        testBBService.count() == 5
    }

    void "test delete"() {
        Long testBBId = setupData()

        expect:
        testBBService.count() == 5

        when:
        testBBService.delete(testBBId)
        sessionFactory.currentSession.flush()

        then:
        testBBService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        TestBB testBB = new TestBB()
        testBBService.save(testBB)

        then:
        testBB.id != null
    }
}
