package com.ums.hr

import com.ums.base.BaseDomain
import grails.util.Holders

class JobDescriptions extends BaseDomain{

    String teacherType
    String jobType
    String jobDescription

    static constraints = {
        teacherType  inList:  Holders.config.teacher.teacherType.list
        jobType      inList:  Holders.config.teacher.jobType.list
        jobDescription(nullable: false,widget:'textarea')
    }
}
