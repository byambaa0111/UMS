package com.ums.system

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SysGroupMenuPermissionServiceSpec extends Specification {

    SysGroupMenuPermissionService sysGroupMenuPermissionService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SysGroupMenuPermission(...).save(flush: true, failOnError: true)
        //new SysGroupMenuPermission(...).save(flush: true, failOnError: true)
        //SysGroupMenuPermission sysGroupMenuPermission = new SysGroupMenuPermission(...).save(flush: true, failOnError: true)
        //new SysGroupMenuPermission(...).save(flush: true, failOnError: true)
        //new SysGroupMenuPermission(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //sysGroupMenuPermission.id
    }

    void "test get"() {
        setupData()

        expect:
        sysGroupMenuPermissionService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SysGroupMenuPermission> sysGroupMenuPermissionList = sysGroupMenuPermissionService.list(max: 2, offset: 2)

        then:
        sysGroupMenuPermissionList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        sysGroupMenuPermissionService.count() == 5
    }

    void "test delete"() {
        Long sysGroupMenuPermissionId = setupData()

        expect:
        sysGroupMenuPermissionService.count() == 5

        when:
        sysGroupMenuPermissionService.delete(sysGroupMenuPermissionId)
        sessionFactory.currentSession.flush()

        then:
        sysGroupMenuPermissionService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SysGroupMenuPermission sysGroupMenuPermission = new SysGroupMenuPermission()
        sysGroupMenuPermissionService.save(sysGroupMenuPermission)

        then:
        sysGroupMenuPermission.id != null
    }
}
