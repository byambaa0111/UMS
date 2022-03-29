package com.ums.payment

import com.ums.base.BaseDomain

class IncomeType extends BaseDomain{

    String incomeType

    static hasMany = [payments:Payment]
    static transients = ['payments']

    static constraints = {
       incomeType(blank: false, nullable: false)
    }

    String toString(){
        this.incomeType;
    }
}
