package com.ums.edu.student

import com.ums.base.BaseDomain
import com.ums.hr.Teacher
import grails.util.Holders

class Education extends BaseDomain{

    String degree
    Date   started
    Date   finished
    String specialization
    String country
    
    static belongsTo = [teacher: Teacher]
    
    static constraints = {
        
        degree inList: Holders.config.getProperty("education.degree.list",List);
        started(black:false,nullable:false)
        finished(black:false,nullable:false)
        specialization(black:false,nullable:false)
        country  inList: Holders.config.getProperty("flanguages.country.list",List);

    }
}
