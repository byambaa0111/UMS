package com.ums.user

class Khoroo {

    String khoroo

    static belongsTo = [district:Districts]
    static constraints = {
        khoroo (nullable: false)
    }

    String toString() {

        return this.khoroo
    }
}
