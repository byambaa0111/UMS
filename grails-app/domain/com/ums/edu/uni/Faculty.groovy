package com.ums.edu.uni

import com.ums.base.BaseDomain
import com.ums.edu.course.ProgrammePlan
import grails.util.Holders


class Faculty extends BaseDomain {
    
    String facultyName;
    String shortName;
    String description;
    String startedDate;
    String address;
    String phoneNumber;
    String website
    String email
    String headOfFaculty;
    String type;
    
    
    static belongsTo = [university:University]
    static hasMany = [departments:Department,rooms:Room,programmePlan: ProgrammePlan]
    
    
    static constraints = {

        facultyName(nullable:false)
        phoneNumber(nullable:false)
        type inList: Holders.config.faculty.type.list
        email email: true
        website url: true
        startedDate(nullable:false)
		description(nullable:true, widget: 'textarea')
        address(nullable:false, widget: 'textarea' )
        headOfFaculty(nullable:false)
        shortName(blank: true, nullable:false)
    }
    String toString(){
        return this.facultyName
    }
    static mapping = {
        sort facultyName: "asc" // or "asc"
    }
}
