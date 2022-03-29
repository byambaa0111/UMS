package com.ums.payment
import com.ums.edu.course.Programme
import com.ums.edu.student.Student
import com.ums.edu.uni.Department
import com.ums.edu.uni.Faculty
import com.ums.hr.Teacher
import grails.converters.JSON
import grails.gorm.PagedResultList
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PaymentController {

    PaymentService paymentService

    static allowedMethods = [save: "POST",renderStudents: "POST",searchByStudent: "POST",savePaymentsBy: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond paymentService.list(params), model:[paymentCount: paymentService.count()]

    }
    def searchByStudent(){
        log.info("[SEARCH BY STUDENT]"+params)
        def sp = params["question"];
        def student;
        if (sp != null) {
            student = Student.findByStudentCode(sp)
            render template: "paymentsByStudent", model:[student:student]
        }else {
            render "хэллп";
        }

    }
    def savePaymentsBy(){
        log.info("[SAVE PAYMENT BY ]"+params)

    }
    def paymentsBy(){
        log.info("[PAYMENT BY ]")
    }
    def renderStudents(){
        log.info("[RENDER STUDETNS PARAMS]"+params)
        def gender     = params["gender"]
        def faculty    = params["faculty"]
        def department = params["department"]
        def programme  = params["programme"]
        def schoolYear = params["year"]
        def groupOfList = params.list("groupOf")
        def yearOfList  = params.list("yearOf")

        String queryString = params["search[value]"]

        def criteriaResult = Student.createCriteria().list() {
            readOnly true
                and{


                    if (faculty != 'all' && department == 'all') {

                        eq("faculty", Faculty.get(faculty))
                    }
                    if (department != 'all' && faculty != 'all' && programme == 'all' ) {

                        eq("department", Department.get(department))
                    }
                    if (programme != 'all' && faculty != 'all' && department != 'all') {

                        eq("programme", Programme.get(programme))
                    }
                    if (yearOfList != 'all' && !yearOfList.contains("all")) {
                        'in'("yearOf", yearOfList)
                    }
                    if (groupOfList != null && !groupOfList.contains("all")) {
                        'in'("groupOf", groupOfList)
                    }
                }
            order  "firstName", "asc"
        }
        log.info("============="+criteriaResult)
        render template: "studentsTable", model: [studentList:criteriaResult,bundleParam:params,schoolYear:schoolYear]

    }
    def chart() {

        log.info("[Payment CHART PARAMS]"+params)

        def criteria = Payment.createCriteria()
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

        PagedResultList criteriaResult = Payment.createCriteria().list([max: length, offset:start]) {
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
        respond paymentService.get(id)
    }

    def create() {
        respond new Payment(params)
    }

    def save(Payment payment) {
        if (payment == null) {
            notFound()
            return
        }

        try {
            paymentService.save(payment)
        } catch (ValidationException e) {
            respond payment.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'payment.label', default: 'Payment'), payment.id])
                redirect payment
            }
            '*' { respond payment, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond paymentService.get(id)
    }

    def update(Payment payment) {
        if (payment == null) {
            notFound()
            return
        }

        try {
            paymentService.save(payment)
        } catch (ValidationException e) {
            respond payment.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'payment.label', default: 'Payment'), payment.id])
                redirect payment
            }
            '*'{ respond payment, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        paymentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'payment.label', default: 'Payment'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'payment.label', default: 'Payment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
