package com.ums.payment


import com.ums.edu.student.Student
import com.ums.system.SysUser


class Contracts {

    Student      student
    Integer      mounts
    double       amount
    Date         payDate
    String       description
    String       createUserName
    SysUser createUser;
    Date         createDate;
    Double       totalAmount;
    int          checkNumber;

    static constraints = {
                student(nullable: false)
                mounts (nullable: true)
                amount (nullable: false)
                payDate (nullable: false)
                description(nullable: true)
                createUserName (nullable: false)
                createUser   (nullable: false)
                createDate(nullable: false)
                totalAmount(nullable: true)
    }
}
