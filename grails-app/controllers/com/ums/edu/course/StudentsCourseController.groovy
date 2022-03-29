package com.ums.edu.course

import com.ums.edu.exams.Exam
import com.ums.edu.exams.ExamService
import com.ums.edu.exams.ExamType
import com.ums.edu.student.Student
import com.ums.edu.student.StudentService
import com.ums.edu.uni.Department

import grails.converters.JSON
import grails.gorm.PagedResultList
import grails.validation.ValidationException
import helper.FunctionHelper

import static org.springframework.http.HttpStatus.*

class StudentsCourseController {

    StudentsCourseService studentsCourseService
    StudentService studentService
    ExamService examService;

    FunctionHelper fh = new FunctionHelper()

    static allowedMethods = [getCoursesByPlan:"POST",insertExamGradeFromTeacher:"POST",saveStudentsToPlan: "POST",treeViewCoursesInPlan: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond studentsCourseService.list(params), model:[studentsCourseCount: studentsCourseService.count()]
    }
/*өөрийн эрхээр нэвтрэн орсон багш хун оютнууддаа дүнг оруулж байна */
    def insertExamGradeFromTeacher(){

        log.info("[insertExamGradeFromTeacher]"+params)
        def examType = ExamType.get(params.int("etId"))
        def stc = StudentsCourse.get(params.int("scId"))
        def gradeTeacher = params.float("gt")


        def exam = Exam.findByStudentCourseAndExamType(stc , examType)
        if (exam == null) {
            exam = new Exam();
        }
        exam.examType = examType
        exam.course = stc.course
        exam.studentCourse = stc
        exam.examDate = new Date()

        if (examType.percent >= gradeTeacher) {
            exam.percent = gradeTeacher
        } else {
            render "Та оруулах дүн хязгаараас хэтэрсэн байна"
            return
        }

        stc.gradeFromTeacher = exam.percent;
        //studentsCourseInstance.examGrade= Float.parseFloat(params.examGrade);
        stc.totalGrade = stc.gradeFromTeacher + stc.examGrade;

        stc.mark = fh.getStudentsMark(stc.totalGrade)
        stc.teacherName = session['sysUser'];
        if (!exam.validate()) {
            println "Admin account does not validate! Errors:"
            exam.errors.allErrors.each {
                println it
            }
        }
        if(session['sysUser'] != null){
            try {
                examService.save(exam)
            } catch (ValidationException e) {
                render exam.errors
                return
            }
            try {
                studentsCourseService.save(stc)
            } catch (ValidationException e) {
                render stc.errors
                return
            }
            render "Багшийн оноо амжилттай хадгалагдлаа"
        } else {
            render " Та өөрийн эрхээр нэвтэрч орно уу"
        }
        render stc.gradeFromTeacher
        return

    }
/*Тухай филтерээр studetnCouse ялгаж байна.*/
    def renderCourseList() {
        log.info("[renderCourseList]"+params)

        def planId = ProgrammePlan.get(params.int("programmePlans"))
        def courseId = params["course"]
        def year = params.int("year")
        def groupOfList = params["groupOf"]

        List courseList = new ArrayList<>()
        List courses = new ArrayList<>()

        courseList.addAll(courseId)
        courseList.each {it->
            courses.add(Course.get(it))
        }
        def examTypes = ExamType.findAllByProgrammePlanAndCourseAndYear(planId, Course.get(courseId),year) ;

        def criteriaResult = StudentsCourse.createCriteria().list() {
            createAlias('student','s')
            readOnly true
            and {
                eq("programmePlan", planId)
                eq("year",year)
                'in'("course",courses)
                if (groupOfList != null && !groupOfList.contains("all")) {
                    'in'("s.groupOf", groupOfList)
                }
            }
            order  "s.firstName", "asc"
        }
        /*log.info("[==========]"+criteriaResult)
        log.info("[==========examTypes]"+examTypes)*/

        render  template: params['id'], model: [studentsCourseList:criteriaResult,examTypes:examTypes,courses:courses.sort{it.id}];



    }
    def treeViewCoursesInPlan(){
        log.info("[treeViewCoursesInPlan]"+params)
        def id = params.int("programmePlan")

        render template: "tvCoursesInPlan", model:[courseInPlanlist:ProgrammePlan.get(id)?.coursesInPlans]
    }
    /* тухайн тэнхэмийн Id аар мэргэжлүүдийн сургалтын төллөвлөгөөнүүдийг гаргаж өгнө select helbereer */
    def getProPlanByDepId(){

        log.info("[PARAMS GET getProPlanByDepId]"+params)

        def depId = Department.get(params["depId"])
        def isTable = params["isTable"]

        /*render g.select(name: "programmePlan",id: "programmePlan",from: ProgrammePlan.findAllByDepartment(depId),class:"form-control select2 w-100",optionKey: "id" )*/

        render template: "plansByDepCheck2", model: [programmePlans: ProgrammePlan.findAllByDepartment(depId),isTable:isTable]
    }

    /*тухайн төлөвлөгөөд байгаа хичээлүүдийг хүснэт хэлбэрээр харуулж байна */

    def getCoursesByPlanByTable(){

        log.info("[PARAMS GET getProPlanByDepId]"+params)

        def courseInPlanList  = CoursesInPlan.findAllByProgrammePlan(ProgrammePlan.get(params.int("programmePlanId")))

        render template: "coursesByPlanWithTeacher" , model:[coursesInPlanList:courseInPlanList];
    }

    /*тухайн төлөвлөгөөд байгаа хичээлүүдийг сонголтоос хамаарч хүснэлт эсвэл жагсаалтаар хэлбэрээр харуулж байна */

    def getCoursesByPlan(){

        log.info("[PARAMS GET getCoursesByPlanId]"+params)

        def isTable  = params['isTable']
        def pplan    = ProgrammePlan.get(params.int("programmePlanId"))
        def schYear  = params.int("schoolYear")

        def courseInPlanList  = CoursesInPlan.findAllByProgrammePlan(pplan)
        def teachersCourses   = TeachersCourse.findAllByProgrammePlanAndSchoolYear(pplan,schYear)

        if (isTable =="table") {
            render template: "coursesByPlanWithTeacher" , model:[coursesInPlanList:courseInPlanList,teachersCourseList:teachersCourses];
        }else{
            render template: "coursesByPlan" , model:[courseInPlanList:courseInPlanList];
        }


    }

    /*save choosen students to plan*/

    def saveStudentsToPlan(){
        log.info("[SAVE STUDENTS TO PLAN PARAMS]"+params);
        def programmePlan = ProgrammePlan.get(params["programmePlan"])
        def coursesInPlan = params["coursesInPlan"]
        int enrollment    = params.int("year")
        def semester      = params["semester"]
        def studentsP     = params['studentsId']

        List courses = new ArrayList<>()
        List students = new ArrayList<>()

        courses.addAll(coursesInPlan)
        students.addAll(studentsP)
        log.info("=========="+courses)
        courses.each {c->
            def course = CoursesInPlan.get(c)?.course
            log.info("==========c"+c)
            students.each { it->
                def sc = new StudentsCourse()
                def student = Student.get(it)
                sc.course        = course
                sc.programme     = programmePlan?.programme
                sc.semester      = semester
                sc.programmePlan = programmePlan
                sc.courseCode    = course?.courseCode
                sc.courseName    = course?.courseNameMN
                sc.student       = student
                sc.studentCode   = student?.studentCode
                sc.year          = enrollment

                try {
                    log.info("==========sc"+sc)
                    studentsCourseService.save(sc)
                } catch (ValidationException e) {
                    log.info("[ERROR]"+sc.errors)
                    render sc.errors
                    return
                }
             }
        }

        render "success resiterd "+students.size()
        return
4
    }
    def addStudentsToPlan(){

        params.max = 1;
        respond studentService.list(params), model:[studentCount: studentService.count()]
    }
    def chart() {

        log.info("[StudentsCourse CHART PARAMS]"+params)

        def criteria = StudentsCourse.createCriteria()
        def results = criteria.list {
            projections{
                groupProperty('type')
                count("type")
            }
        }

        render results as JSON
    }
    def data_for_datatable(){

        println("[DATA FOR DATATABLES]"+params)
        int draw   = params.int("draw")
        int length = params.int("length")
        int start  = params.int("start")

        String dataTableOrderColumnIdx = params["order[0][column]"]
        String dataTableOrderColumnName = "columns[" + dataTableOrderColumnIdx + "][data]"

        String sortName = params[dataTableOrderColumnName] ?: "id"
        String sortDir = params["order[0][dir]"] ?: "asc"

        String queryString = params["search[value]"]

        PagedResultList criteriaResult = StudentsCourse.createCriteria().list([max: length, offset:start]) {
            readOnly true
            or {
                ilike('id', '%' + queryString + '%')
            }
            order sortName, sortDir
        }

        Map dataTableResults = [
                draw: draw,
                recordsTotal: criteriaResult.totalCount,
                recordsFiltered: criteriaResult.totalCount,
                data: criteriaResult
        ]

        log.info("retund json"+criteriaResult as JSON)
        render dataTableResults as JSON

    }
    def show(Long id) {
        respond studentsCourseService.get(id)
    }

    def create() {
        respond new StudentsCourse(params)
    }

    def save(StudentsCourse studentsCourse) {
        if (studentsCourse == null) {
            notFound()
            return
        }

        try {
            studentsCourseService.save(studentsCourse)
        } catch (ValidationException e) {
            respond studentsCourse.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'studentsCourse.label', default: 'StudentsCourse'), studentsCourse.id])
                redirect studentsCourse
            }
            '*' { respond studentsCourse, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond studentsCourseService.get(id)
    }

    def update(StudentsCourse studentsCourse) {
        if (studentsCourse == null) {
            notFound()
            return
        }

        try {
            studentsCourseService.save(studentsCourse)
        } catch (ValidationException e) {
            respond studentsCourse.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'studentsCourse.label', default: 'StudentsCourse'), studentsCourse.id])
                redirect studentsCourse
            }
            '*'{ respond studentsCourse, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        studentsCourseService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'studentsCourse.label', default: 'StudentsCourse'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'studentsCourse.label', default: 'StudentsCourse'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
