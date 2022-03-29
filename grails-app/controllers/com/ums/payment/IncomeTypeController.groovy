package com.ums.payment


import com.ums.hr.Teacher
import grails.converters.JSON
import grails.gorm.PagedResultList
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class IncomeTypeController {

    IncomeTypeService incomeTypeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond incomeTypeService.list(params), model:[incomeTypeCount: incomeTypeService.count()]
    }
    def chart() {

        log.info("[IncomeType CHART PARAMS]"+params)

        def criteria = IncomeType.createCriteria()
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

        PagedResultList criteriaResult = IncomeType.createCriteria().list([max: length, offset:start]) {
            readOnly true
            or {
                ilike('incomeType', '%' + queryString + '%')
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
        respond incomeTypeService.get(id)
    }

    def create() {
        respond new IncomeType(params)
    }

    def save(IncomeType incomeType) {
        if (incomeType == null) {
            notFound()
            return
        }

        try {
            incomeTypeService.save(incomeType)
        } catch (ValidationException e) {
            respond incomeType.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'incomeType.label', default: 'IncomeType'), incomeType.id])
                redirect incomeType
            }
            '*' { respond incomeType, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond incomeTypeService.get(id)
    }

    def update(IncomeType incomeType) {
        if (incomeType == null) {
            notFound()
            return
        }

        try {
            incomeTypeService.save(incomeType)
        } catch (ValidationException e) {
            respond incomeType.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'incomeType.label', default: 'IncomeType'), incomeType.id])
                redirect incomeType
            }
            '*'{ respond incomeType, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        incomeTypeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'incomeType.label', default: 'IncomeType'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'incomeType.label', default: 'IncomeType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
