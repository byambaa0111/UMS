package com.ums.system

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SysGroupServiceSpec extends Specification {

    SysGroupService sysGroupService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SysGroup(...).save(flush: true, failOnError: true)
        //new SysGroup(...).save(flush: true, failOnError: true)
        //SysGroup sysGroup = new SysGroup(...).save(flush: true, failOnError: true)
        //new SysGroup(...).save(flush: true, failOnError: true)
        //new SysGroup(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //sysGroup.id
    }

    void "test get"() {
        setupData()

        expect:
        sysGroupService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SysGroup> sysGroupList = sysGroupService.list(max: 2, offset: 2)

        then:
        sysGroupList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        sysGroupService.count() == 5
    }

    void "test delete"() {
        Long sysGroupId = setupData()

        expect:
        sysGroupService.count() == 5

        when:
        sysGroupService.delete(sysGroupId)
        sessionFactory.currentSession.flush()

        then:
        sysGroupService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SysGroup sysGroup = new SysGroup()
        sysGroupService.save(sysGroup)

        then:
        sysGroup.id != null
    }
}
