package com.ums.hr

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ProjectsServiceSpec extends Specification {

    ProjectsService projectsService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Projects(...).save(flush: true, failOnError: true)
        //new Projects(...).save(flush: true, failOnError: true)
        //Projects projects = new Projects(...).save(flush: true, failOnError: true)
        //new Projects(...).save(flush: true, failOnError: true)
        //new Projects(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //projects.id
    }

    void "test get"() {
        setupData()

        expect:
        projectsService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Projects> projectsList = projectsService.list(max: 2, offset: 2)

        then:
        projectsList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        projectsService.count() == 5
    }

    void "test delete"() {
        Long projectsId = setupData()

        expect:
        projectsService.count() == 5

        when:
        projectsService.delete(projectsId)
        sessionFactory.currentSession.flush()

        then:
        projectsService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Projects projects = new Projects()
        projectsService.save(projects)

        then:
        projects.id != null
    }
}
