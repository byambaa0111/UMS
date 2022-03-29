package com.ums.hr

import grails.gorm.services.Service

@Service(JobDescriptions)
interface JobDescriptionsService {

    JobDescriptions get(Serializable id)

    List<JobDescriptions> list(Map args)

    Long count()

    void delete(Serializable id)

    JobDescriptions save(JobDescriptions jobDescriptions)

}