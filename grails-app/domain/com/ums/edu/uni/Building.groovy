package com.ums.edu.uni

    
class Building {
    
    String address;
    String phoneNumber;
    
    static belongsTo = [university:University]
    static hasMany = [rooms:Room]
    
    static constraints = {
        
        address(nullable:false,widget: 'textarea')
        phoneNumber(nullable:false)
        
    }

    String toString(){

        this.address;

    }
}
