package com.ums.edu.course

import com.ums.base.BaseDomain
import com.ums.edu.student.Student
import com.ums.edu.uni.Department
import com.ums.edu.uni.Faculty
import grails.util.Holders



class Programme extends BaseDomain{

    String programmeCode
    String programmeMN
    String programmeEN
    String degree
    String programmeIndex
    String educationType
    String specialisation /*мэргэжилийн чиглэл боловсролын яамнаас гаргадаг*/
    String totalCredit
    int    duration

    static belongsTo = [faculty: Faculty, department: Department]

    /*static hasMany = [students: Student,studentsCourse:StudentsCourse]*/

    static constraints = {

        programmeCode(nullable:false)
        programmeMN(nullable:false)
        programmeEN(nullable:false)
        programmeIndex(nullable:false)
        degree inList: Holders.config.student.degree.list;
        educationType inList: Holders.config.student.educationType.list;
        specialisation nullable: false;
    }

    @Override
    public String toString(){
        return this.programmeMN
    }

    static mapping = {

        sort programmeIndex: "asc" // or "asc"

    }
}
