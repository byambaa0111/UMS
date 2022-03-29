package com.ums.hr

import com.ums.edu.student.Education
import com.ums.edu.student.Student
import com.ums.edu.uni.Department
import com.ums.edu.uni.FacultyService
import com.ums.helper.HelperService
import com.ums.system.SysUser
import com.ums.system.SysUserService
import helper.FunctionHelper
import org.hibernate.transform.Transformers
import com.ums.edu.uni.Faculty
import grails.converters.JSON
import grails.core.GrailsApplication
import grails.gorm.PagedResultList
import grails.validation.ValidationException

import javax.swing.text.html.HTML

import static org.springframework.http.HttpStatus.*

class TeacherController {

    GrailsApplication grailsApplication
    TeacherService    teacherService
    HelperService     helperService
    SysUserService sysUserService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def profile(Long id){
        respond teacher:teacherService.get(id),education:new Education(),experiences:new Experiences(),flanguages:new FLanguages(),projects: new Projects(),rewards:new Rewards();
    }

    def printDefinition(Long id){
        render(template: "/teacher/printJobDescription", model: [teacher:teacherService.get(id), user:session.sysUser])
    }

    def printJobDescription(Long id){
        log.info("[PRINT JOB DESCRIPTION]"+params)
        def teacher= teacherService.get(id)
        def jobDescription = JobDescriptions.findAllByJobTypeAndTeacherType(teacher.jobType,teacher.teacherType)
        redirect(controller:"JobDescriptions", action:"show", params:[id:jobDescription?.first()?.id] )
    }


    def editProfile(Long id){
        log.info("[EDIT PROFILE ]"+params)
        respond teacherService.get(id)
    }

    def teachersChart(){

    }



        /*Teacher chart */
    def chart() {

        log.info("[TEACHER CHART PARAMS]"+params)

        def criteria  = Teacher.createCriteria()
        def criteria1 = Teacher.createCriteria()
        def criteria2 = Teacher.createCriteria()
        def criteria3 = Teacher.createCriteria()
        def criteria4 = Teacher.createCriteria()
        def criteria5 = Teacher.createCriteria()
        def criteria6 = FLanguages.createCriteria()

        def resultsTeacherByDegree = criteria5.list {
            projections{
                groupProperty('degree')
                count("degree")
            }
        }
        def resultsTeacherType = criteria.list {
            projections{
                groupProperty('teacherType')
                count("teacherType")
            }
        }
        def resultsByLanguages  = criteria6.list {
            projections{
                groupProperty('language')
                count('id')
            }
        }
        def resultsByDepartment = criteria1.list {
            createAlias('department','d')
            projections{
                groupProperty('d.departmentCode')
                count('id')
            }
        }
        def resultsByJobType = criteria2.list {
            projections{
                groupProperty('jobType')
                count('jobType')
            }
        }
        def resultsByFaculty = criteria3.list {
            createAlias('faculty','f')
            projections{
                groupProperty('f.shortName')
                groupProperty('teacherType')
                count()
            }
        }

        print("[RESULT FACULTY]"+resultsByFaculty);

        def resultsByGender = criteria4.list {

            projections{
                groupProperty('gender')
                count('gender')
            }
        }
        Map resultForChart = [
                teacherType:resultsTeacherType,
                byDepartment:resultsByDepartment,
                jobType:resultsByJobType,
                byFaculties:resultsByFaculty,
                byGender:resultsByGender,
                byDegree:resultsTeacherByDegree,
                byLanguages :resultsByLanguages
        ]
        render   resultForChart as JSON
    }


    def index(Integer max) {

        log.info("[TEACHER INDEX PARAMS]"+params)

        /*PagedResultList criteriaResult = Teacher.createCriteria().list(max: 10, offset: 10)  {
            readOnly true
            or {
                ilike('firstName', '%' + queryString + '%')

            }
            order sortName, sortDir
        }
*/
        /*def recipient = grailsApplication.config.getProperty('foo.bar.hello')
        println "======================"+recipient*/
        params.max = Math.min(max ?: 10, 100)
        respond teacherService.list(params), model:[teacherCount: teacherService.count()]
    }

    def data_for_datatable(){

        println("[DATA FOR DATATABLES]"+params)

        int draw   = params.int("draw")
        int length = params.int("length")
        int start  = params.int("start")

        def teacherType =  params["teacherType[]"];
        def faculties   =  params["faculties[]"];
        def gender      =  params["gender"];
        def degree      =  params["degree"];
        def jobType     =  params["jobType"];
        def faculty     =  params["faculty"];
        def department  =  params["department"];


        println("[DATA FOR DATATABLES]"+teacherType+faculty)

        String dataTableOrderColumnIdx = params["order[0][column]"]
        String dataTableOrderColumnName = "columns[" + dataTableOrderColumnIdx + "][data]"

        String sortName = params[dataTableOrderColumnName] ?: "firstName"
        String sortDir  = params["order[0][dir]"] ?: "asc"

        String queryString = params["search[value]"]

        PagedResultList criteriaResult = Teacher.createCriteria().list([max: length, offset:start]) {
            readOnly true
            and {
                or{
                    ilike('firstName',   '%' + queryString + '%')
                    ilike('lastName',    '%' + queryString + '%')
                    ilike('emailMnu',    '%' + queryString + '%')
                    ilike('register',    '%' + queryString + '%')
                    ilike('phoneNumber', '%' + queryString + '%')
                    ilike('degree',      '%' + queryString + '%')
                    }
                and {

                    if (gender != "all") {
                        eq("gender", gender)
                    }
                    if (degree != "all") {
                        eq("degree", degree)
                    }
                    if (jobType != "all") {
                        eq("jobType", jobType)
                    }

                    if (teacherType!= null) {
                        'in'("teacherType", teacherType)
                    }

                    if (faculty != 'all') {
                        log.info("2==========="+faculty+department);
                        eq("faculty", Faculty.get(faculty))
                    }

                    if (department != "all" && department != null) {
                        log.info("3==========="+faculty+department);
                        eq ("department", Department.get(department))
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
        respond teacherService.get(id)
    }

    def getTeacherCode(){
        log.print("[GET CODE]"+params)
        def department = Department.get(params.getInt("id"))
        def result = ""+department?.faculty?.id+department?.id + teacherService.list().last().id+1
        render result as String
    }
    def create() {
        respond new Teacher(params)
    }

    def save(Teacher teacher) {

        log.info("[SAVE TEACHERS]"+params)
        log.info("[1SAVE TEACHERS]"+teacher.facultyId)
        log.info("[SAVeqE TEACHERS]"+params)
        log.info("[232SAVE TEACHERS]"+teacher.facultyId)

        if (teacher == null) {
            notFound()
            return
        }

        try {

            def sysUser = new SysUser()

            sysUser.fullName  =  teacher.lastName + " "+teacher.firstName
            sysUser.userId    =  "T"+teacher.teacherCode;
            sysUser.password  =  helperService.strToMd5(teacher.teacherCode)

            sysUser.email     = teacher.email;
            sysUser.emailMnu  = teacher.emailMnu;
            sysUser.sysGroup  =  helperService.getSysGroup("TEACHER")

            def dep = Department.get(params["department"])

            teacher.setFaculty(dep.faculty)

            teacher.sysUser = sysUser
/*savehiigdej baina */

            if (sysUser.validate() && teacher.validate()) {

                    sysUserService.save(sysUser);
                    teacherService.save(teacher)

            }else{

                    respond teacher.errors, view:'create'
                    return
            }



        } catch (ValidationException e) {

            respond teacher.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'teacher.label', default: 'Teacher'), teacher.id])
                redirect teacher
            }
            '*' { respond teacher, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond teacherService.get(id)
    }

    def update(Teacher teacher) {
        log.info("[UPDATE TEACHERS]`"+params)
        log.info("[UPDATE TEACHERS]"+teacher.lastName)
        if (teacher == null) {
            notFound()
            return
        }

        try {
            teacherService.save(teacher)
        } catch (ValidationException e) {
            respond teacher.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'teacher.label', default: 'Teacher'), teacher.id])
                redirect teacher
            }
            '*'{ respond teacher, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        teacherService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'teacher.label', default: 'Teacher'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'teacher.label', default: 'Teacher'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
