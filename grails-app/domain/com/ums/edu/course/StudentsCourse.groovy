package com.ums.edu.course

import com.ums.base.BaseDomain
import com.ums.edu.exams.Exam
import com.ums.edu.student.Student
import com.ums.hr.Teacher
import grails.util.Holders

class StudentsCourse extends BaseDomain{

    int   year
    float gradeFromTeacher
    float examGrade
    float totalGrade
    String  mark;
    String teacherName
    String createUserName;
    String studentCode= student?.studentCode;
    String courseName = course?.courseNameMN;
    String courseCode = course?.courseCode;
    String semester


    boolean isLooked = false;

    static belongsTo = [programme:Programme,programmePlan:ProgrammePlan, student: Student, course:Course ]

    static hasMany = [exams: Exam]


    static constraints = {

        mark(nullable: true,blank:true)
        createUserName(nullable: true,blank:false)
        teacherName(nullable: true,blank:false)
        studentCode(nullable: false,blank:false)
        semester inList: Holders.config.getProperty("courseInPlan.semester.list",List)
        /*dkye unique: true*/
    }
    static mapping = {
         student sort: 'lastName',order: 'asc'
    }
    String toString(){
        this.course?.courseCode+ "-"+ this.course?.courseNameMN ;
    }
    String toStringWithGrades(){
        this.course?.courseCode+ "-"+ this.course?.courseNameMN + " - " + this.totalGrade;
    }
}