package com.ums.hr

import grails.gorm.services.Service

@Service(Rewards)
interface RewardsService {

    Rewards get(Serializable id)

    List<Rewards> list(Map args)

    Long count()

    void delete(Serializable id)

    Rewards save(Rewards rewards)

}