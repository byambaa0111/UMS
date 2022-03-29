package com.ums.user

class LoginCommand {
    
   String userId
   String password
   static constraints = {
           userId(blank:false)
           password(blank:false)
   }
    LoginCommand(String userId,String password){
        this.userId = userId
        this.password = password
    }


}