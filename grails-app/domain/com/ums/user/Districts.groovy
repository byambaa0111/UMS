package com.ums.user

class Districts {

    String district
    String zipcode

    static belongsTo = [province:Provinces]

    static hasMany = [khoroos:Khoroo]

    static constraints = {
        district(nullable: false)
        zipcode(nullable: true)
    }
    String toString() {
       return this.district + " " +this.zipcode;
    }
}
