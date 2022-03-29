package com.ums.system

class SessionLooker {

    String userId
    String sessionId
    Date   data = new Date();

    static constraints = {

        userId(nullable: false)
        userId(sessionId: false,unique: true)

    }

}
