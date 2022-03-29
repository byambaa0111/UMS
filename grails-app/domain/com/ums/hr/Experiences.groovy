package com.ums.hr

import com.ums.base.BaseDomain

class Experiences extends BaseDomain {

    String employerName
    String jobType
    String position

    Date startDate
    Date endDate

    String isFullTime

    static belongsTo = [teacher:Teacher]
    /*baingiin tur gesen field name ugmuur baina*/

    static constraints = {
         employerName (blank: false , nullable: false)
         position (blank: false , nullable: false)
         jobType (blank: false , nullable: false)
         startDate (blank: false , nullable: false)
         endDate (blank: false , nullable: false)
         isFullTime inList: ['Full time', 'Part time']

    }
}
