package com.ums.edu.uni

import grails.converters.JSON
import grails.gorm.PagedResultList
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class FacultyController {

    FacultyService facultyService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond facultyService.list(params), model:[facultyCount: facultyService.count()]
    }

    def data_for_datatable(){

        println("[DATA FOR DATATABLES]"+params)
        int draw   = params.int("draw")
        int length = params.int("length")
        int start  = params.int("start")

        String dataTableOrderColumnIdx = params["order[0][column]"]
        String dataTableOrderColumnName = "columns[" + dataTableOrderColumnIdx + "][data]"

        String sortName = params[dataTableOrderColumnName] ?: "facultyName"
        String sortDir = params["order[0][dir]"] ?: "asc"

        String queryString = params["search[value]"]

        PagedResultList criteriaResult = Faculty.createCriteria().list([max: length, offset:start]) {
            readOnly true
            or {
                ilike('facultyName', '%' + queryString + '%')
                ilike('phoneNumber', '%' + queryString + '%')
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
        respond facultyService.get(id)
    }

    def create() {
        respond new Faculty(params)
    }

    def save(Faculty faculty) {
        if (faculty == null) {
            notFound()
            return
        }

        try {
            facultyService.save(faculty)
        } catch (ValidationException e) {
            respond faculty.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'faculty.label', default: 'Faculty'), faculty.id])
                redirect faculty
            }
            '*' { respond faculty, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond facultyService.get(id)
    }

    def update(Faculty faculty) {
        if (faculty == null) {
            notFound()
            return
        }

        try {
            facultyService.save(faculty)
        } catch (ValidationException e) {
            respond faculty.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'faculty.label', default: 'Faculty'), faculty.id])
                redirect faculty
            }
            '*'{ respond faculty, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        facultyService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'faculty.label', default: 'Faculty'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'faculty.label', default: 'Faculty'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
