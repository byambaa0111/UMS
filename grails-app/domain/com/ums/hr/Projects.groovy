package com.ums.hr

import com.ums.base.BaseDomain

class Projects extends BaseDomain{

    String projectName
    String projectRole
    String projectCode
    String projectType
    Date   startDate
    Date   endDate
    String description

    static belongsTo = [teacher:Teacher]

    static constraints = {

        projectName blank: false , nullable: false
        projectRole blank: false , nullable: false
        projectCode blank: true , nullable: true
        projectType blank: false , nullable: false
        startDate blank: false , nullable: false
        endDate blank: false , nullable: false
        description blank: true , nullable: true,widget:'textArea'
    }
}
