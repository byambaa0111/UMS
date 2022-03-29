package com.ums.edu.course

import com.ums.base.BaseDomain
import com.ums.hr.Teacher
import grails.util.Holders

class TeachersCourse extends BaseDomain{

    String schoolYear;
    String semester
    //def groupOf
    //def yearOf

  /*  static mapping = {
        myf defaultValue: schoolYear+semester
    }*/

    static belongsTo = [programme:Programme, programmePlan:ProgrammePlan, course:Course,teacher: Teacher ]

    static constraints = {

        schoolYear inList: Holders.config.getProperty("programmePlan.schoolYear.list",List)
        //groupOf    inList: Holders.config.getProperty("student.groupOf.list",List);
        //yearOf     inList: Holders.config.getProperty("student.yearOf.list",List);
        semester   inList: Holders.config.getProperty("courseInPlan.semester.list",List)

    }

}
