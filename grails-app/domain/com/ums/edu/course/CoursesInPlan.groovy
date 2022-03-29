package com.ums.edu.course
import com.ums.base.BaseDomain
import com.ums.edu.uni.Department
import grails.util.Holders

class CoursesInPlan extends BaseDomain{

    float          creditHour
    String         semester


    static belongsTo = [course:Course,
                        programmePlan:ProgrammePlan,
                        courseType:CourseType]


    static hasMany = {
    }
    static constraints = {
        creditHour(nullable: false)
        semester inList: Holders.config.getProperty("courseInPlan.semester.list",List)
    }

    String toString(){
     this.course?.courseNameMN
    }
}
