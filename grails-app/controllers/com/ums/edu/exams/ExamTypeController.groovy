package com.ums.edu.exams


import com.ums.hr.Teacher
import grails.converters.JSON
import grails.gorm.PagedResultList
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ExamTypeController {

    ExamTypeService examTypeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond examTypeService.list(params), model:[examTypeCount: examTypeService.count()]
    }
    def chart() {

        log.info("[ExamType CHART PARAMS]"+params)

        def criteria = ExamType.createCriteria()
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

        PagedResultList criteriaResult = ExamType.createCriteria().list([max: length, offset:start]) {
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
        respond examTypeService.get(id)
    }

    def create() {
        respond new ExamType(params)
    }

    def save(ExamType examType) {
        if (examType == null) {
            notFound()
            return
        }

        try {
            examTypeService.save(examType)
        } catch (ValidationException e) {
            respond examType.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'examType.label', default: 'ExamType'), examType.id])
                redirect examType
            }
            '*' { respond examType, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond examTypeService.get(id)
    }

    def update(ExamType examType) {
        if (examType == null) {
            notFound()
            return
        }

        try {
            examTypeService.save(examType)
        } catch (ValidationException e) {
            respond examType.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'examType.label', default: 'ExamType'), examType.id])
                redirect examType
            }
            '*'{ respond examType, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        examTypeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'examType.label', default: 'ExamType'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'examType.label', default: 'ExamType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
