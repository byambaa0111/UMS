package com.ums.payment

import com.ums.edu.uni.Department
import com.ums.hr.Teacher
import grails.converters.JSON
import grails.gorm.PagedResultList
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PaymentTypeController {

    PaymentTypeService paymentTypeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond paymentTypeService.list(params), model:[paymentTypeCount: paymentTypeService.count()]
    }
    def chart() {

        log.info("[PaymentType CHART PARAMS]"+params)

        def criteria = PaymentType.createCriteria()
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

        def department = params["department"]
        def year = params["year"]

        def yearOfList  = params.list("yearOf[]")


        String dataTableOrderColumnIdx = params["order[0][column]"]
        String dataTableOrderColumnName = "columns[" + dataTableOrderColumnIdx + "][data]"

        String sortName = params[dataTableOrderColumnName] ?: "paymentType"
        String sortDir = params["order[0][dir]"] ?: "asc"

        String queryString = params["search[value]"]

        PagedResultList criteriaResult = PaymentType.createCriteria().list([max: length, offset:start]) {
            readOnly true
            or {
                ilike('paymentType', '%' + queryString + '%')
            }and{

                if (yearOfList != null && !yearOfList.contains("all")) {
                    'in'("year", yearOfList)
                }
                if (department ) {
                    eq("department", Department.get(department))
                }
                eq("startDate", year)
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
        respond paymentTypeService.get(id)
    }

    def create() {
        respond new PaymentType(params)
    }

    def save(PaymentType paymentType) {
        if (paymentType == null) {
            notFound()
            return
        }

        try {
            paymentTypeService.save(paymentType)
        } catch (ValidationException e) {
            respond paymentType.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'paymentType.label', default: 'PaymentType'), paymentType.id])
                redirect paymentType
            }
            '*' { respond paymentType, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond paymentTypeService.get(id)
    }

    def update(PaymentType paymentType) {
        if (paymentType == null) {
            notFound()
            return
        }

        try {
            paymentTypeService.save(paymentType)
        } catch (ValidationException e) {
            respond paymentType.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'paymentType.label', default: 'PaymentType'), paymentType.id])
                redirect paymentType
            }
            '*'{ respond paymentType, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        paymentTypeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'paymentType.label', default: 'PaymentType'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'paymentType.label', default: 'PaymentType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
