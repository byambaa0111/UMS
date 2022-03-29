package com.ums.edu.course


import com.ums.hr.Teacher
import grails.converters.JSON
import grails.gorm.PagedResultList
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class TeachersCourseController {

    TeachersCourseService teachersCourseService

    static allowedMethods = [save: "POST",saveTeachersCourse: "POST",data_for_datatable: "GET", update: "PUT", delete: "DELETE"]

    def index(Integer max) {

        log.info("[TEACHERSCOURSE INDEX]"+params)

        params.max = Math.min(max ?: 10, 100)
        respond teachersCourseService.list(params), model:[teachersCourseCount: teachersCourseService.count()]
    }
    def chart() {

        log.info("[TeachersCourse CHART PARAMS]"+params)

        def criteria = TeachersCourse.createCriteria()
        def results = criteria.list {
            projections{
                groupProperty('type')
                count("type")
            }
        }

        render results as JSON
    }


    def saveTeachersCourse(){

        print "[SAVE TEACHER COURSE]"+params;
        //[year:2017, department:1, programmePlans:95, groupOf:all, controller:teachersCourse, format:null, action:saveTeachersCourse, id:coursesTable]

        def programmePlan = ProgrammePlan.get(params["programmePlans"])
        def programme = ProgrammePlan.get(params["programmePlans"])?.programme
        def course = Course.get(params['course'])
        def teacher = Teacher.get(params['teacher'])
        def teachersCourse = new TeachersCourse()
        def teacherCourseList = TeachersCourse.findAllByProgrammePlanAndProgrammeAndCourse(programmePlan,programme,course)

        if (teacherCourseList) {
            teachersCourse = teacherCourseList[0]
        }

        teachersCourse.schoolYear = params["year"]
        teachersCourse.semester = params["semester"]
        //teachersCourse.groupOf = params['groupOf'] == "all" ?
        teachersCourse.programmePlan = programmePlan
        teachersCourse.programme = programme
        teachersCourse.course = course
        teachersCourse.teacher = teacher

        if (teachersCourse == null) {
            notFound()
            return
        }

        try {
            teachersCourseService.save(teachersCourse)
            log.info("[SAVED]"+teachersCourse)
        } catch (ValidationException e) {
            log.info("[ERROR]"+teachersCourse.errors)
            render teachersCourse.errors as JSON
            return
        }

        render teachersCourse.teacher as JSON
    }

    def data_for_datatable(){

        println("[DATA FOR DATATABLES]"+params)
        def department = com.ums.edu.uni.Department.get(params.int("department"));
        def schoolYear =  params["year"];
        def semester =  params["semester"];
        def jobType =  params["jobType"];
        def teacherType =  params["teacherType"];
        def gender =  params["gender"];

        def teachers = Teacher.createCriteria().list() {
            readOnly true
            and {
                eq("department",department)

                if (gender != "all") {
                    eq("gender", gender)
                }
                if (jobType != "all") {
                    eq("jobType", jobType)
                }
                if (teacherType!= null) {
                    'in'("teacherType", teacherType)
                }

            }
        }

        def teacherAndCourseMap = [:]
        def teacherTotalGreditMap = [:]

        teachers.each {it->
            teacherAndCourseMap.put(it,TeachersCourse.findAllByTeacherAndSchoolYearAndSemester(it,schoolYear,semester))
            teacherTotalGreditMap.put(it,TeachersCourse.findAllByTeacherAndSchoolYear(it,schoolYear,semester))
        }

        render template: "teachersBox", model: [teacherAndCourseMap:teacherAndCourseMap,teachersCourseList:teachers,teacherTotalGreditMap:teacherTotalGreditMap]
    }

    def show(Long id) {
        respond teachersCourseService.get(id)
    }

    def create() {
        respond new TeachersCourse(params)
    }

    def save(TeachersCourse teachersCourse) {
        if (teachersCourse == null) {
            notFound()
            return
        }

        try {
            
            teachersCourseService.save(teachersCourse)
        } catch (ValidationException e) {
            respond teachersCourse.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'teachersCourse.label', default: 'TeachersCourse'), teachersCourse.id])
                redirect teachersCourse
            }
            '*' { respond teachersCourse, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond teachersCourseService.get(id)
    }

    def update(TeachersCourse teachersCourse) {
        if (teachersCourse == null) {
            notFound()
            return
        }

        try {
            teachersCourseService.save(teachersCourse)
        } catch (ValidationException e) {
            respond teachersCourse.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'teachersCourse.label', default: 'TeachersCourse'), teachersCourse.id])
                redirect teachersCourse
            }
            '*'{ respond teachersCourse, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        teachersCourseService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'teachersCourse.label', default: 'TeachersCourse'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'teachersCourse.label', default: 'TeachersCourse'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
