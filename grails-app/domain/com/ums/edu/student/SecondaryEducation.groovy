package com.ums.edu.student

class SecondaryEducation {

    String nameOfSubject
    float     grade
    String numberOfCerficate
    String nameOfAimag
    String nameOfSum
    String nameOfSchool
    
    
    static belongsTo = [student:Student]
    
    static constraints = {
        
        nameOfSubject(nullable:false)
        grade(nullable:false)
        numberOfCerficate(nullable:false)
        nameOfAimag  (nullable:false)
        nameOfSum(nullable:false)
        nameOfSchool(nullable:false)
        
    }
}
