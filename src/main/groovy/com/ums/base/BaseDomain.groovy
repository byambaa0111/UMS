package com.ums.base
import com.ums.system.SysUser
import org.springframework.web.context.request.RequestContextHolder
abstract  class BaseDomain {
    Date dateCreated
    Date lastUpdated
    Boolean isActive =Boolean.TRUE


    static mapping  = {
        /*createDate defaultValue : new Date();
        updateDate defaultValue : new Date();*/

    }
    static constraints = {
  /*      createUser  display: false;*/
        dateCreated display :false
        lastUpdated display :false

    }
    def beforeInsert = {

    /*    def session = RequestContextHolder.currentRequestAttributes().getSession()
        createUser = session["sysUser"];
*/
    }

    def beforeUpdate = {

    }
}
