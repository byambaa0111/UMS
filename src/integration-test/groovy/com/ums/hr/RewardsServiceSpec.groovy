package com.ums.hr

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class RewardsServiceSpec extends Specification {

    RewardsService rewardsService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Rewards(...).save(flush: true, failOnError: true)
        //new Rewards(...).save(flush: true, failOnError: true)
        //Rewards rewards = new Rewards(...).save(flush: true, failOnError: true)
        //new Rewards(...).save(flush: true, failOnError: true)
        //new Rewards(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //rewards.id
    }

    void "test get"() {
        setupData()

        expect:
        rewardsService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Rewards> rewardsList = rewardsService.list(max: 2, offset: 2)

        then:
        rewardsList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        rewardsService.count() == 5
    }

    void "test delete"() {
        Long rewardsId = setupData()

        expect:
        rewardsService.count() == 5

        when:
        rewardsService.delete(rewardsId)
        sessionFactory.currentSession.flush()

        then:
        rewardsService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Rewards rewards = new Rewards()
        rewardsService.save(rewards)

        then:
        rewards.id != null
    }
}
