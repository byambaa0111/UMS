package com.ums.user

class Provinces {

    String provinces

    static hasMany = [districts:Districts]
    static constraints = {
        provinces(nullable: false)
    }
    String toString() {
       return this.provinces
    }
}
