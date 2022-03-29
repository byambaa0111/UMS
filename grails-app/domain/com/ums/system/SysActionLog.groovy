package com.ums.system


class SysActionLog {

    String  currentUserName;
    String  controllersName;
    String  actionsName;
    String  parametersName;
    Date    createdDate;
    String  ipAddress;
    String  macAddress;


    static belongsTo = [currentUser:SysUser]

    static constraints = {

        currentUser(nullable:false);
        controllersName(nullable:false);
        actionsName(nullable:false);
        parametersName(nullable:false);
        createdDate(nullable:false);
        ipAddress(nullable:true);
        macAddress(nullable:true);

    }
}
