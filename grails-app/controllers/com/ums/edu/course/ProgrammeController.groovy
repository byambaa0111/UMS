package com.ums.edu.course

import com.ums.edu.uni.Faculty
import com.ums.hr.Teacher
import grails.converters.JSON
import grails.gorm.PagedResultList
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ProgrammeController {

    ProgrammeService programmeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond programmeService.list(params), model:[programmeCount: programmeService.count()]
    }
    def chart() {

        log.info("[Programme CHART PARAMS]"+params)

        def criteria = Programme.createCriteria()
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
        def faculties = params.list("faculties[]")
        List fList = new ArrayList()

        faculties.each{it->
            fList.add(Faculty.get(it))
        }

        String dataTableOrderColumnIdx = params["order[0][column]"]
        String dataTableOrderColumnName = "columns[" + dataTableOrderColumnIdx + "][data]"

        String sortName = params[dataTableOrderColumnName] ?: "programmeCode"
        String sortDir = params["order[0][dir]"] ?: "asc"

        String queryString = params["search[value]"]

        PagedResultList criteriaResult = Programme.createCriteria().list([max: length, offset:start]) {

            readOnly true
            or {
                ilike('programmeCode', '%' + queryString + '%')
            }
            and{
                if (faculties != null) {
                    'in'("faculty", fList)
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
        respond programmeService.get(id)
    }

    def create() {
        respond new Programme(params)
    }

    def save(Programme programme) {
        if (programme == null) {
            notFound()
            return
        }

        try {
            programmeService.save(programme)
        } catch (ValidationException e) {
            respond programme.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'programme.label', default: 'Programme'), programme.id])
                redirect programme
            }
            '*' { respond programme, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond programmeService.get(id)
    }

    def update(Programme programme) {
        if (programme == null) {
            notFound()
            return
        }

        try {
            programmeService.save(programme)
        } catch (ValidationException e) {
            respond programme.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'programme.label', default: 'Programme'), programme.id])
                redirect programme
            }
            '*'{ respond programme, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        programmeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'programme.label', default: 'Programme'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'programme.label', default: 'Programme'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
