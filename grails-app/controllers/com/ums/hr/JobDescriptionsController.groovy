package com.ums.hr


import com.ums.hr.Teacher
import grails.converters.JSON
import grails.gorm.PagedResultList
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class JobDescriptionsController {

    JobDescriptionsService jobDescriptionsService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond jobDescriptionsService.list(params), model:[jobDescriptionsCount: jobDescriptionsService.count()]
    }
    def chart() {

        log.info("[JobDescriptions CHART PARAMS]"+params)

        def criteria = JobDescriptions.createCriteria()
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

        PagedResultList criteriaResult = JobDescriptions.createCriteria().list([max: length, offset:start]) {
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
        respond jobDescriptionsService.get(id)
    }

    def create() {
        respond new JobDescriptions(params)
    }

    def save(JobDescriptions jobDescriptions) {
        if (jobDescriptions == null) {
            notFound()
            return
        }

        try {
            jobDescriptionsService.save(jobDescriptions)
        } catch (ValidationException e) {
            respond jobDescriptions.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'jobDescriptions.label', default: 'JobDescriptions'), jobDescriptions.id])
                redirect jobDescriptions
            }
            '*' { respond jobDescriptions, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond jobDescriptionsService.get(id)
    }

    def update(JobDescriptions jobDescriptions) {
        if (jobDescriptions == null) {
            notFound()
            return
        }

        try {
            jobDescriptionsService.save(jobDescriptions)
        } catch (ValidationException e) {
            respond jobDescriptions.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'jobDescriptions.label', default: 'JobDescriptions'), jobDescriptions.id])
                redirect jobDescriptions
            }
            '*'{ respond jobDescriptions, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        jobDescriptionsService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'jobDescriptions.label', default: 'JobDescriptions'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'jobDescriptions.label', default: 'JobDescriptions'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
