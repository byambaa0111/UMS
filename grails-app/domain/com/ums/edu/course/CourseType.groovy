package com.ums.edu.course

class CourseType {

    String courseType;
    static hasMany = []
/*coursesInPlan:CoursesInPlan*/
    static constraints = {
        courseType(nullable:false)
    }

    String toString(){
        return this.courseType;
    }
}
