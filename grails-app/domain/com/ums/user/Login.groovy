package com.ums.user

class Login {
   String userId
   String password
   
   static constraints = {
           userId(blank:false)
           password(blank:false)
   }

}
