package com.ums.edu.uni

import grails.converters.JSON
import grails.gorm.PagedResultList
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class UniversityController {

    UniversityService universityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond universityService.list(params), model:[universityCount: universityService.count()]
    }

    def data_for_datatable(){

        println("[DATA FOR DATATABLES]"+params)
        int draw   = params.int("draw")
        int length = params.int("length")
        int start  = params.int("start")

        String dataTableOrderColumnIdx = params["order[0][column]"]
        String dataTableOrderColumnName = "columns[" + dataTableOrderColumnIdx + "][data]"

        String sortName = params[dataTableOrderColumnName] ?: "universityName"
        String sortDir = params["order[0][dir]"] ?: "asc"

        String queryString = params["search[value]"]

        PagedResultList criteriaResult = University.createCriteria().list([max: length, offset:start]) {
            readOnly true
            or {
                ilike('universityName', '%' + queryString + '%')
                ilike('universityNameEn', '%' + queryString + '%')
                ilike('universityShortName', '%' + queryString + '%')
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
        respond universityService.get(id)
    }

    def create() {
        respond new University(params)
    }

    def save(University university) {
        if (university == null) {
            notFound()
            return
        }

        try {
            universityService.save(university)
        } catch (ValidationException e) {
            respond university.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'university.label', default: 'University'), university.id])
                redirect university
            }
            '*' { respond university, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond universityService.get(id)
    }

    def update(University university) {
        if (university == null) {
            notFound()
            return
        }

        try {
            universityService.save(university)
        } catch (ValidationException e) {
            respond university.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'university.label', default: 'University'), university.id])
                redirect university
            }
            '*'{ respond university, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        universityService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'university.label', default: 'University'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'university.label', default: 'University'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
