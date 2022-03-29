package com.ums.edu.uni

import com.ums.base.BaseDomain
import com.ums.edu.course.Programme
import com.ums.edu.course.ProgrammePlan
import com.ums.edu.student.Student
import com.ums.hr.Teacher

class Department extends BaseDomain{
    
    String departmentNameEN
    String departmentNameMN
    String departmentCode
    String headOfDepartment
    String type
    String description
    Date startedDate

    static belongsTo = [faculty:Faculty,university :University ]
    
    static hasMany = [students: Student, teachers: Teacher, programmes: Programme,programmePlans: ProgrammePlan]
    static constraints = {
        
        departmentCode(nullable:false)
        departmentNameMN(nullable:false)
        departmentNameEN(nullable:false)
        type inList: ['Хөтөлбөрийн баг','Зөвлөл','Хүрээлэн']
        description widget:'textAria' ,nullable: true
        startedDate(nullable:true)
        headOfDepartment(nullable:false)
    }
     String toString(){
                  return departmentNameMN + " - " + departmentCode
    }
    static mapping = {
        sort departmentNameMN: "asc" // or "asc"
    }
}
