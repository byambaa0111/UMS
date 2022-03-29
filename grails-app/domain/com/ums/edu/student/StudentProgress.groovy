package com.ums.edu.student

class StudentProgress {

    String  groupOf
    int     yearOf
    Date    lastUpdate
    String  studentCode
    Student student;


    static hasMany = []

    static belongsTo = [student:Student,status:StudentsStatus]

    static constraints = {

        studentCode(nullable:false)
        student(nullable:false)
        groupOf(nullable:false)
        yearOf(nullable:false)
        lastUpdate(nullable:false)

    }

}
