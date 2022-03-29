package com.ums.system

import com.ums.base.BaseDomain

class SysUser extends BaseDomain{
    
    String userId
    String password
    String fullName
    String email
    String emailMnu


    Date lastLogin

    int numberOfPagePrint
    int numberOfPagePrinted

    static belongsTo = [sysGroup:SysGroup]



    static constraints = {

        userId (nullable: true, maxSize: 20)
        password (nullable: false)
        fullName (nullable: true)
        email (nullable: true, email: true)
        isActive(nullable:false)
        lastLogin(nullable: true)
        emailMnu(nullable: true, email: true)
        numberOfPagePrint(nullable: true)
        numberOfPagePrinted(nullable: true)

    }

    String  toString(){

            return this.fullName;

    }
}
