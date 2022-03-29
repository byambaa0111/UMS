package com.ums.payment

import com.ums.base.BaseDomain
import com.ums.edu.course.Programme
import com.ums.edu.uni.Department
import com.ums.edu.uni.Faculty
import grails.util.Holders

class PaymentType extends BaseDomain{

    String         paymentType
    Programme      programme
    Double         amount
    Date           deadline
    String         year         /*heddugeer angiin tolbor*/
    String         startDate;   // hicheeliin jiliin ehlel
    String         endDate;     // hicheeliin jiliin togosgol
    boolean        isPayment;
    String         programmeName;

    static belongsTo = [faculty: Faculty, department: Department]

    static hasMany = [payments:Payment]

    static constraints = {
        paymentType inList: Holders.config.getProperty("paymentType.paymentType.list",List)
        year inList: Holders.config.getProperty("student.yearOf.list",List)
        amount (nullable: false)
        deadline (nullable: false)
        programme (nullable: false)
        isPayment(nullable:false)
        startDate inList: Holders.config.getProperty("programmePlan.schoolYear.list",List)
        endDate   inList: Holders.config.getProperty("programmePlan.schoolYear.list",List)
    }


    String getProgrammeName(){
        return this.programme?.programmeMN
    }
    String toString(){
        return this.programme?.programmeCode +"-"+ this.paymentType +"-"+this.amount +"-"+this.startDate
    }
}