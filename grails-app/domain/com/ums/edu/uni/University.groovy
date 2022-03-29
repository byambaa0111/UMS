package com.ums.edu.uni

import com.fasterxml.jackson.databind.ser.Serializers
import com.ums.base.BaseDomain

class University extends BaseDomain{
    
    String universityName;
    String universityNameEn;
    String universityShortName;
    String type
    Date   startedDate;
    String address;
    String phoneNumber;
    String website;
    String email;
    String description

    static hasMany = [faculties:Faculty,buildings:Building]

    static mapping = {
        autoTimestamp true
    }

    static constraints = {
        
        universityName(nullable: false)
        universityNameEn(nullable: false)
        universityShortName(nullable: false)
        phoneNumber(nullable:false)
        website(nullable:false, url: true);
        email(nullable:false,email: true);
        address(nullable:false, widget: 'textarea' )
        startedDate(nullable:false)
        type inList: [' Их сургууль', 'Дээд сургууль']
        description widget:'textAria' ,nullable: true
    }
    
    String toString(){
     return this.universityName
    }
}
