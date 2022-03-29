package com.ums.edu.uni

class Room {
   
    String roomNumber;
    int capacity
    String phoneNumber;
    
    static belongsTo = [building:Building,faculty:Faculty]
    
    static constraints = {
        
        roomNumber(nullable:false)
        capacity(nullbale:false)
        phoneNumber(nullbale:true)
        
    }
}
