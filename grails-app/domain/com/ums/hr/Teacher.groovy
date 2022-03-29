package com.ums.hr
import com.ums.base.BaseDomain
import com.ums.base.Person
import com.ums.edu.student.Education
import com.ums.edu.uni.Department
import com.ums.edu.uni.Faculty
import com.ums.edu.uni.University
import com.ums.system.SysUser
import grails.util.Holders

class Teacher extends Person{

    String teacherCode
    String teacherType
    String jobType
    String emailMnu;
    String degree
    Float  rating;
    Date   enrollment_date;
    static hasMany   = [experiences:Experiences,flanguages:FLanguages,projects:Projects,rewards:Rewards,educations: Education]

    static belongsTo = [department: Department, faculty:Faculty,sysUser: SysUser]

    /*static fetchMode = [projects: 'join']*/

    static constraints = {

        teacherCode(nullable:false,unique:true)
        teacherType  inList:  Holders.config.teacher.teacherType.list
        jobType      inList:  Holders.config.teacher.jobType.list
        degree       inList: Holders.config.getProperty("education.degree.list",List);
        emailMnu       (nullable: true, email: true)
        enrollment_date (nullable:false)
        rating     (nullable:true,range: 1..10)

    }
    String toString(){
        return this.lastName +" "+this.firstName+" - "+this.teacherCode
    }
    static mapping = {
        sort lastName: "asc" // or "asc"
    }
}

