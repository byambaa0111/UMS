package com.ums.edu.course

import com.ums.base.BaseDomain
import com.ums.edu.uni.Department
import com.ums.edu.uni.Faculty
import grails.util.Holders

class ProgrammePlan extends BaseDomain{
    /* Сургалтын хөтөлбөр */

    String          specialization /*мэргэжилийн чиглэл*/
    String          startSchoolYear
    String          endSchoolYear
    String          planIndex;
    String          planName;
    String          totalCredit
    int             duration

    static belongsTo = [department: Department,faculty: Faculty,programme:Programme]

    static hasMany = [coursesInPlans:CoursesInPlan,studentsCourse:StudentsCourse]

    /*,studentscourse:StudentsCourse*/
    static constraints = {

        planIndex blank: false , nullable: false
        planName blank: false , nullable: false
        startSchoolYear inList: Holders.config.getProperty("programmePlan.schoolYear.map",List)
        endSchoolYear inList: Holders.config.getProperty("programmePlan.schoolYear.map",List)
        totalCredit blank: false , nullable: false
    }

    static mapping = {
        sort planName : "asc"
    }
    @Override
    String toString(){
        this.planName +" - "+ this.startSchoolYear+" - "+this.endSchoolYear
    }
}
