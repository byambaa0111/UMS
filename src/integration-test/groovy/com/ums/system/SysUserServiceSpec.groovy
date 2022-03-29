package com.ums.system

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SysUserServiceSpec extends Specification {

    SysUserService sysUserService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SysUser(...).save(flush: true, failOnError: true)
        //new SysUser(...).save(flush: true, failOnError: true)
        //SysUser sysUser = new SysUser(...).save(flush: true, failOnError: true)
        //new SysUser(...).save(flush: true, failOnError: true)
        //new SysUser(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //sysUser.id
    }

    void "test get"() {
        setupData()

        expect:
        sysUserService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SysUser> sysUserList = sysUserService.list(max: 2, offset: 2)

        then:
        sysUserList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        sysUserService.count() == 5
    }

    void "test delete"() {
        Long sysUserId = setupData()

        expect:
        sysUserService.count() == 5

        when:
        sysUserService.delete(sysUserId)
        sessionFactory.currentSession.flush()

        then:
        sysUserService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SysUser sysUser = new SysUser()
        sysUserService.save(sysUser)

        then:
        sysUser.id != null
    }
}
