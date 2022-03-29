package com.ums.edu.student

class StudentsStatus {

    String status;
    Date createDate;

    static hasMany = [studentprogress:StudentProgress]

    static constraints = {
        status(nullable: true)

    }
    String toString(){

        return this.status;
    }
}
