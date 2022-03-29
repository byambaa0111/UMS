package com.ums.payment

import com.ums.base.BaseDomain
import com.ums.edu.student.Student
import com.ums.system.SysUser

class Payment extends BaseDomain{

    Student      student
    PaymentType  paymentType
    IncomeType   incomeType
    double       amount
    Date         payDate
    String       description
    String       createUserName
    String       checkNumber
    int          checkNumberInt


    static belongsTo = [student:Student,paymentType:PaymentType,incomeType:IncomeType,createdUser: SysUser]
    static hasMany = []

    static constraints = {

        student(nullable: false)
        paymentType(nullable: false)
        incomeType(nullable: false)
        amount(nullable: false)
        payDate(nullable: false)
        description (nullable: true)
        checkNumber(nullable:true)
        checkNumberInt (nullable:true)

    }
    String toString(){
        return this.paymentType.toString() +" = "+this.amount
    }
}
