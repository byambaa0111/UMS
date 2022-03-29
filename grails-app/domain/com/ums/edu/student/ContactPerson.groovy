package com.ums.edu.student

class ContactPerson {

    String lastName
    String firstName
    String email
    String phoneNumber
    
    
    static belongsTo = [student:Student]
    
    static constraints = {
        
        lastName(nullable :false)
        firstName(nullable :false)
        email( email: true, blank: true)
        phoneNumber(nullable :false)
        
    }

    String toString(){

        return  this.lastName +" "+this.firstName;
    }
}
