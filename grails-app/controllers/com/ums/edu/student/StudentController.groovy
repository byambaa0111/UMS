package com.ums.edu.student

import com.ums.edu.course.Programme
import com.ums.edu.uni.Department
import com.ums.edu.uni.Faculty
import com.ums.hr.FLanguages
import com.ums.hr.Teacher
import grails.converters.JSON
import grails.gorm.PagedResultList
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class StudentController {

    StudentService studentService

    static allowedMethods = [save: "POST", getDepartments:"POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 1, 10)
        respond studentService.list(params), model:[studentCount: studentService.count()]
    }


    /*=============student chart ==========*/
    def studentChart() {


    }

    def chart() {
        log.info("[Student CHART PARAMS]"+params)
        def criteria = Student.createCriteria()

        def resultsByYearOf = criteria.list {
            projections{
                groupProperty('yearOf')
                count("id")
            }
        }
        Map resultForChart = [
                byYearOf:resultsByYearOf,
        ]

        render resultForChart as JSON
    }
    def searchStudent(){

        String query = params.remove('term');

        println("[Search student]"+query);

        //query = query ? new String(query.getBytes("8859_1"), "UTF8") : null

        List students = Student.findAllByStudentCodeLikeOrFirstNameLikeOrRegisterLike(query + '%',query + '%',query + '%').collect() {
            return [
                    id: it.id,
                    label: it.studentCode + " - "+ it.lastName + " - "+it.firstName,
                    value: it.studentCode
            ]
        }

        render students as JSON

    }
    /*oyutnii surdag ni unen gesen todorhoilolt hevlej baina*/

    def printDefinition(Long Id){
        println("[ PRINT DEFINITION ]"+ params)
        if (params['id']){
            render(template: "/student/printDefinition", model: [student: Student.get(params['id']), user:session.sysUser])
        }

    }

    def getDepartments(){

        log.info("[PARAMS GET DEPARTMENT]"+params)
        def facultyId = params['facultyId'];
        if (facultyId != 'all'){
            render(template:"/student/departmentsByFaculty",model: [departments:Department.findAllByFaculty(Faculty.get(facultyId))])
        }else {
            render "all";
        }

    }
    def getProgrammes(){

        log.info("[PARAMS GET DEPARTMENT]"+params)
        def curriculumId = params['departmentId']
        if (curriculumId != 'all') {
            render(template:"/student/programmeByDepartment",model: [programmes:Department.get(params['departmentId']).programmes ])
        }else{
            render "all";
        }

    }

    def data_for_datatable(){

        println("[DATA FOR STUDENT DATATABLES]"+params)
        int draw   = params.int("draw")
        int length = params.int("length")
        int start  = params.int("start")
        def gender = params["gender"]
        def faculty    = params["faculty"]
        def department = params["department"]
        def programme = params["programme"]

        def departments =params["departments[]"]
        def groupOfList =params["groupOf[]"]
        def yearOfList  =params["yearOf[]"]


        def departmentList= [];

        departments.each{obj->
            departmentList.add(Department.get(obj))
        }
        String dataTableOrderColumnIdx = params["order[0][column]"]
        String dataTableOrderColumnName = "columns[" + dataTableOrderColumnIdx + "][data]"

        String sortName = params[dataTableOrderColumnName] ?: "firstName"
        String sortDir = params["order[0][dir]"] ?: "asc"

        String queryString = params["search[value]"]

        PagedResultList criteriaResult = Student.createCriteria().list([max: length, offset:start]) {
            createAlias('department','d')
            createAlias('programme','c')
                readOnly true
            and{
                or {
                    ilike('firstName', '%' + queryString + '%')
                    ilike('lastName', '%' + queryString + '%')
                    ilike('register', '%' + queryString + '%')
                    ilike('studentCode', '%' + queryString + '%')
                    ilike('familyName', '%' + queryString + '%')
                }
                and{
                    log.info("1==========="+faculty+department+programme);
                    if (gender != "all") {
                        eq("gender", gender)
                    }
                    if (faculty != 'all' && department == 'all') {
                        log.info("2==========="+faculty+department+programme);
                        eq("faculty", Faculty.get(faculty))
                    }
                    if (department != 'all' && faculty != 'all' && programme == 'all' ) {
                        log.info("3==========="+faculty+department+programme);
                        eq("department", Department.get(department))
                    }
                    if (programme != 'all' && faculty != 'all' && department != 'all') {
                        log.info("4==========="+faculty+department+programme);
                        eq("programme", Programme.get(programme))
                    }
                    /*
                    if (departments != 'all' &&) {
                        'in'("department", departmentList)
                    }
*/
                    if (yearOfList != 'all' && !yearOfList.contains("all")) {
                        'in'("yearOf", yearOfList)
                    }
                    if (groupOfList != null && !groupOfList.contains("all")) {
                        'in'("groupOf", groupOfList)
                    }
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

        render dataTableResults as JSON

    }
    def show(Long id) {


        respond studentService.get(id)
    }

    def create() {
        respond new Student(params)
    }

    def save(Student student) {
        if (student == null) {
            notFound()
            return
        }

        try {
            studentService.save(student)
        } catch (ValidationException e) {
            respond student.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'student.label', default: 'Student'), student.id])
                redirect student
            }
            '*' { respond student, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond studentService.get(id)
    }

    def update(Student student) {
        if (student == null) {
            notFound()
            return
        }

        try {
            studentService.save(student)
        } catch (ValidationException e) {
            respond student.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'student.label', default: 'Student'), student.id])
                redirect student
            }
            '*'{ respond student, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        studentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'student.label', default: 'Student'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'student.label', default: 'Student'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
