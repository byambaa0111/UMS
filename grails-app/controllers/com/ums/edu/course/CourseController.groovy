package com.ums.edu.course

import com.ums.edu.uni.Department
import com.ums.hr.Teacher
import grails.converters.JSON
import grails.gorm.PagedResultList
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CourseController {

    CourseService courseService

    static allowedMethods = [save: "POST",coursesBy:"POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond courseService.list(params), model:[courseCount: courseService.count()]
    }
    def chart() {

        log.info("[Course CHART PARAMS]"+params)

        def criteria = Course.createCriteria()
        def results = criteria.list {
            projections{
                groupProperty('type')
                count("type")
            }
        }

        render results as JSON
    }
    def coursesBy(){
        log.info("[DATA COURSE BY FOR DATATABLES ]"+params)
        def departments =params.list(["departments"]);
        def queryString =params["search"]

        int length = params.int("length")
        int start  = params.int("start")
        def departmentList= [];

        departments.each{obj->
            departmentList.add(Department.get(obj))
        }

        PagedResultList criteriaResult = Course.createCriteria().list([max: length, offset:start]) {
            readOnly true
            or {
                ilike('courseCode', '%' + queryString + '%')
                ilike('courseNameMN', '%' + queryString + '%')
                ilike('courseNameEN', '%' + queryString + '%')

            }
            and{

                if (departments != 'all' && departments != null) {
                    'in'("department", departmentList)

                }
            }
            order "courseCode", "asc"
        }
        log.info("[criteriaResult]"+criteriaResult);
        Map dataTableResults = [
                draw: draw,
                recordsTotal: criteriaResult.totalCount,
                recordsFiltered: criteriaResult.totalCount,
                data: criteriaResult
        ]
        render dataTableResults as JSON
        /*render( template:"/common/courseTable",model: [courseList:criteriaResult]);
*/
    }
    def data_for_datatable(){

        println("[DATA COURSE FOR DATATABLES ]"+params)
        int draw   = params.int("draw")
        int length = params.int("length")
        int start  = params.int("start")

        def semesterType =  params["semesterType"]
        def departments =params.list(["departments[]"]);

        def departmentList= [];

        departments.each{obj->
            departmentList.add(Department.get(obj))
        }
        String dataTableOrderColumnIdx = params["order[0][column]"]
        String dataTableOrderColumnName = "columns[" + dataTableOrderColumnIdx + "][data]"

        String sortName = params[dataTableOrderColumnName] ?: "courseCode"
        String sortDir = params["order[0][dir]"] ?: "asc"

        String queryString = params["search[value]"]

        PagedResultList criteriaResult = Course.createCriteria().list([max: length, offset:start]) {
            readOnly true
            or {
                ilike('courseCode', '%' + queryString + '%')
                ilike('courseNameMN', '%' + queryString + '%')
                ilike('courseNameEN', '%' + queryString + '%')
            }
            and{

                if (departments != 'all' && departments != null) {
                    'in'("department", departmentList)
                }
                if (semesterType != 'all' && semesterType != null) {
                    eq("semesterType", semesterType)
                }

            }
            order sortName, sortDir
        }

        Map dataTableResults = [
                draw: draw,
                recordsTotal: criteriaResult.totalCount,
                recordsFiltered: criteriaResult.totalCount,
                data: criteriaResult
        ]
       log.info("[returnd]"+dataTableResults);
        render dataTableResults as JSON

    }

    def show(Long id) {
        respond courseService.get(id)
    }

    def showCourseAjax(Long id){
        log.info("[showCourseajax]"+id)

        render( template:"courseContent", model: ["course": Course.get(id)])
    }
    def create() {
        respond new Course(params)
    }

    def save(Course course) {
        if (course == null) {
            notFound()
            return
        }

        try {
            courseService.save(course)
        } catch (ValidationException e) {
            respond course.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'course.label', default: 'Course'), course.id])
                redirect course
            }
            '*' { respond course, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond courseService.get(id)
    }

    def update(Course course) {
        if (course == null) {
            notFound()
            return
        }

        try {
            courseService.save(course)
        } catch (ValidationException e) {
            respond course.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'course.label', default: 'Course'), course.id])
                redirect course
            }
            '*'{ respond course, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        courseService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'course.label', default: 'Course'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'course.label', default: 'Course'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
