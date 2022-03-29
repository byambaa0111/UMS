package ums

import com.ums.base.BaseDomain
import grails.persistence.Entity

class Person extends BaseDomain {
    String name
    int age

    static constraints = {
        name blank: false, nullable: false
        age min: 18, blank: false
    }
}
