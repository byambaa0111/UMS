package com.ums.system

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SysMenuServiceSpec extends Specification {

    SysMenuService sysMenuService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SysMenu(...).save(flush: true, failOnError: true)
        //new SysMenu(...).save(flush: true, failOnError: true)
        //SysMenu sysMenu = new SysMenu(...).save(flush: true, failOnError: true)
        //new SysMenu(...).save(flush: true, failOnError: true)
        //new SysMenu(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //sysMenu.id
    }

    void "test get"() {
        setupData()

        expect:
        sysMenuService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SysMenu> sysMenuList = sysMenuService.list(max: 2, offset: 2)

        then:
        sysMenuList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        sysMenuService.count() == 5
    }

    void "test delete"() {
        Long sysMenuId = setupData()

        expect:
        sysMenuService.count() == 5

        when:
        sysMenuService.delete(sysMenuId)
        sessionFactory.currentSession.flush()

        then:
        sysMenuService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SysMenu sysMenu = new SysMenu()
        sysMenuService.save(sysMenu)

        then:
        sysMenu.id != null
    }
}
