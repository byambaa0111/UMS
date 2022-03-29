package com.ums.edu.student

import com.ums.edu.course.Programme
import com.ums.edu.course.StudentsCourse
import com.ums.edu.uni.Department
import com.ums.edu.uni.Faculty
import com.ums.payment.Payment
import com.ums.system.SysUser
import grails.util.Holders


class Student extends com.ums.base.Person{

    String studentCode
    String enrollmentDate;

    /*Specialization  specialization*/

    String groupOf ;
    String nameOfAimag ; //Provinces
    String nameOfSum  ;   // districts.
    String nameOfKhoroo  ;
    String nameOfBag ;
    String yearOf   ;            //
    String emailMnu;
    String wifiPassword;
    String degree;
    String educationType;
    String programmeName;

    static belongsTo =[sysUser:SysUser,department: Department, faculty: Faculty,programme: Programme ]

    static hasMany = [studentscourses: StudentsCourse, payments: Payment]

  /*  static transients = ['programmeName']
*/
    static constraints = {

        studentCode(blank: false, nullable:false,unique: true)
        enrollmentDate(blank: false,nullable:false)
        nameOfAimag(blank: true,nullable:true)
        nameOfSum(blank: true,nullable:true)
        nameOfKhoroo(blank: true,nullable:true)
        nameOfBag(blank: true,nullable:true)
        groupOf inList: Holders.config.getProperty("student.groupOf.list",List);
        yearOf inList: Holders.config.getProperty("student.yearOf.list",List);
        degree inList: Holders.config.getProperty("student.degree.list",List);
        educationType inList: Holders.config.getProperty("student.educationType.list",List);
        emailMnu(blank: true,nullable: true, email: true)
        wifiPassword(blank: true,nullable: true)

    }
    String getProgrammeName(){
        return this.programme.programmeMN
    }
    String toString(){
        return this.studentCode +"-"+this.lastName +"-"+ this.firstName
    }
   /* @Override
    public getProgramme(){
        this.programme.programmeMN
    }*/
    static mapping = {
        sort lastName: "asc" // or "asc"
        studentscourses sort:'semester'
        photo (type:'blob')
        programme lazy: false
    }




}
