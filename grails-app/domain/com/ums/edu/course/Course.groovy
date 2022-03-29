package com.ums.edu.course

import com.ums.base.BaseDomain
import com.ums.edu.uni.Department
import com.ums.edu.uni.Faculty
import grails.util.Holders


class Course extends BaseDomain{
    
    String courseCode;
    String courseNameMN;
    String courseNameEN;
    float  totalCredit;
    int    hourForLecture
    int    hourForSeminar
    String courseType
    String semesterType

    static belongsTo = [department: Department, faculty: Faculty]
    /*
    static hasMany = [studentscourses:StudentsCourse,teacherscourses:TeachersCourse,contents:CourseContent,lectures:Lecture,lecturecontent:LectureContent,examtype:ExamType,coursesinplan:CoursesInPlan]
    */
    static constraints = {

        courseCode(nullable:false,unigue:true)
        courseNameMN(nullable:false);
        courseNameEN(nullable:false);
        semesterType inList: Holders.config.getProperty('course.semesterType.list',java.util.List)
        courseType  inList: Holders.config.getProperty('course.courseType.list',java.util.List)
    }
    @Override
    String  toString(){
        return this.courseCode +" - "+this.courseNameMN;
    }
    static mapping = {
        sort courseNameMN: "asc" // or "asc"
    }

}
