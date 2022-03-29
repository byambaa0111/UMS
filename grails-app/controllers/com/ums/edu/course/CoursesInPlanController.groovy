package com.ums.edu.course

import com.ums.edu.uni.Department
import com.ums.hr.Teacher
import grails.converters.JSON
import grails.gorm.PagedResultList
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CoursesInPlanController {

    CoursesInPlanService coursesInPlanService

    static allowedMethods = [save: "POST",courseBy: "POST",saveCourses: "POST",getProPlanByDepId: "POST", update: "PUT", delete: "DELETE",deleteAjax: "POST"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond coursesInPlanService.list(params), model:[coursesInPlanCount: coursesInPlanService.count()]
    }

    /*studentsCourse zoriulsan tree view zoriulsan course duusaj baina*/
    def courseBySC(){
        log.info("[ COURSE BY PARAMS]"+ params)
        def coursesInPlanList = CoursesInPlan.findAllByProgrammePlan(ProgrammePlan.get(params["programmePlan"]))

        render template: "courseByTable",model: [coursesInPlanList:coursesInPlanList.sort{it.semester}]
    }

    def courseBy(){
        log.info("[ COURSE BY PARAMS]"+ params)
        def coursesInPlanList = CoursesInPlan.findAllByProgrammePlan(ProgrammePlan.get(params["programmePlan"]))

        render template: "courseByTable",model: [coursesInPlanList:coursesInPlanList.sort{it.semester}]
    }
    def getProPlanByDepId(){
        log.info("[PARAMS GET getProPlanByDepId]"+params)
        def depId = Department.get(params["depId"])

        render g.select(name: "programmePlan",id: "programmePlan",from: ProgrammePlan.findAllByDepartment(depId),class:"form-control select2 w-100",optionKey: "id" )
    }

    def chart() {

        log.info("[CoursesInPlan CHART PARAMS]"+params)

        def criteria = CoursesInPlan.createCriteria()
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

        PagedResultList criteriaResult = CoursesInPlan.createCriteria().list([max: length, offset:start]) {
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

        render dataTableResults as JSON

    }
    def show(Long id) {
        respond coursesInPlanService.get(id)
    }

    def create() {
        //params.max = Math.min(max ?: 10, 100)
        //respond courseService.list(params), model:[courseCount: courseService.count()]
        respond new CoursesInPlan(params)
    }
    def createFromPlan() {
        log.info("[CRATE FROM PLAN PARAMS]"+params);
        //params.max = Math.min(max ?: 10, 100)
        //respond courseService.list(params), model:[courseCount: courseService.count()]
        render view:"createFromPlan",  model: [coursesInPlan :new CoursesInPlan(params),programmePlan:ProgrammePlan.get(params['id']) ]
    }

    def saveCourses(){

        log.info("[SAVE COURSE IN PLAN PARAMS ]"+params)

        def courseIds = params.list(["courseIds"])
        def programmePlan = ProgrammePlan.get(params["programmePlan"])

        courseIds.each { it ->
            def course = Course.get(it);
            boolean duplicateCourse = true
            programmePlan.coursesInPlans.each { obj ->
                if (!obj.course.equals(course)) {
                  log.info("==================bhgui")
                }else{
                    log.info("==================b------------na"+params["creditHour"])
                    duplicateCourse = false;
                }

            }
            if (duplicateCourse) {
                def coursesInPlan = new CoursesInPlan()
                coursesInPlan.course = course;
                coursesInPlan.programmePlan = programmePlan
                coursesInPlan.courseType = CourseType.get(params["courseType.id"])
                coursesInPlan.creditHour = params.float(["creditHour"])
                coursesInPlan.semester = params.semester

                log.info("hevleed uzeye" + coursesInPlan)

                try {
                    coursesInPlanService.save(coursesInPlan)
                } catch (ValidationException e) {
                    println("================" + coursesInPlan.errors)
                    return
                }
            }
        }
     render template: "/common/treeViewCoursesInPlan" , model: [courseInPlanlist:programmePlan.coursesInPlans]
    }
    def save(CoursesInPlan coursesInPlan) {

        log.info("[SAVE COURSE IN PLAN PARAMS ]"+params)

        if (coursesInPlan == null) {
            notFound()
            return
        }

        try {
            coursesInPlanService.save(coursesInPlan)
        } catch (ValidationException e) {
            respond coursesInPlan.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'coursesInPlan.label', default: 'CoursesInPlan'), coursesInPlan.id])
                redirect coursesInPlan
            }
            '*' { respond coursesInPlan, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond coursesInPlanService.get(id)
    }

    def update(CoursesInPlan coursesInPlan) {
        if (coursesInPlan == null) {
            notFound()
            return
        }

        try {
            coursesInPlanService.save(coursesInPlan)
        } catch (ValidationException e) {
            respond coursesInPlan.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'coursesInPlan.label', default: 'CoursesInPlan'), coursesInPlan.id])
                redirect coursesInPlan
            }
            '*'{ respond coursesInPlan, [status: OK] }
        }
    }
    def deleteAjax(){
        log.info("[DELETE COURSEINPLAN PARAMS]"+params)
        def id = params["id"]
        def programmePlanId = params["programmePlanId"]

        if (id == null) {
            render "null id"
            return
        }

        coursesInPlanService.delete(id)
        render template:"/common/treeViewCoursesInPlan", model: [courseInPlanlist:CoursesInPlan.findAllByProgrammePlan(ProgrammePlan.get(programmePlanId)) ]
    }
    def delete(Long id) {

        if (id == null) {
            notFound()
            return
        }

        coursesInPlanService.delete(id)


        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'coursesInPlan.label', default: 'CoursesInPlan'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'coursesInPlan.label', default: 'CoursesInPlan'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
