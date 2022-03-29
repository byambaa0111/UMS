package com.ums.system

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SysGroupMenuServiceSpec extends Specification {

    SysGroupMenuService sysGroupMenuService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SysGroupMenu(...).save(flush: true, failOnError: true)
        //new SysGroupMenu(...).save(flush: true, failOnError: true)
        //SysGroupMenu sysGroupMenu = new SysGroupMenu(...).save(flush: true, failOnError: true)
        //new SysGroupMenu(...).save(flush: true, failOnError: true)
        //new SysGroupMenu(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //sysGroupMenu.id
    }

    void "test get"() {
        setupData()

        expect:
        sysGroupMenuService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SysGroupMenu> sysGroupMenuList = sysGroupMenuService.list(max: 2, offset: 2)

        then:
        sysGroupMenuList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        sysGroupMenuService.count() == 5
    }

    void "test delete"() {
        Long sysGroupMenuId = setupData()

        expect:
        sysGroupMenuService.count() == 5

        when:
        sysGroupMenuService.delete(sysGroupMenuId)
        sessionFactory.currentSession.flush()

        then:
        sysGroupMenuService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SysGroupMenu sysGroupMenu = new SysGroupMenu()
        sysGroupMenuService.save(sysGroupMenu)

        then:
        sysGroupMenu.id != null
    }
}
