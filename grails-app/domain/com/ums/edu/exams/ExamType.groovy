package com.ums.edu.exams

import com.ums.edu.course.Course
import com.ums.edu.course.ProgrammePlan


class ExamType {
    
    String  examType
    float   percent
    Date    examDate
    String  description
    int     numberOfWeek
    int     year

    ProgrammePlan programmePlan
   /* course:com.ums.edu.course.Course*/
    static belongsTo = [course: Course]

    static hasMany = [exams:Exam]

    static constraints = {

        examType(nullable:false)
        percent(nullable:false)
        examDate(nullable:false)
        description(nullable:true)
        numberOfWeek(nullable:true)

    }

    String toString(){
        return this.examType +" "+ this.examDate
    }

}
