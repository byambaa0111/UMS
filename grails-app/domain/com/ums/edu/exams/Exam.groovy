package com.ums.edu.exams

import com.ums.edu.course.Course
import com.ums.edu.course.StudentsCourse

class Exam {
    Date   examDate;
    Course course;
    float  percent

    static belongsTo = [examType:ExamType,studentCourse: StudentsCourse]

    static constraints = {

        examDate(nullable: false)
        course(nullable: false)
        percent(nullable: false)

    }
}
