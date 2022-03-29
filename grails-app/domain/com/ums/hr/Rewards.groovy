package com.ums.hr

import com.ums.base.BaseDomain

import java.sql.Date

class Rewards extends BaseDomain{

    String rewardName
    String rewardNumber
    Date rewardDate
    String description

    static  belongsTo = [teacher:Teacher]

    static constraints = {

         rewardName blank: false, nullable: false
         rewardNumber blank: false, nullable: false
         rewardDate blank: false, nullable: false
         description blank: true, nullable: true, widget:"textArea"

    }
}
