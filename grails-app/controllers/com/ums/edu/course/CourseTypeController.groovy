package com.ums.edu.course


import com.ums.hr.Teacher
import grails.converters.JSON
import grails.gorm.PagedResultList
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CourseTypeController {

    CourseTypeService courseTypeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond courseTypeService.list(params), model:[courseTypeCount: courseTypeService.count()]
    }
    def chart() {

        log.info("[CourseType CHART PARAMS]"+params)

        def criteria = CourseType.createCriteria()
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

        PagedResultList criteriaResult = CourseType.createCriteria().list([max: length, offset:start]) {
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
        respond courseTypeService.get(id)
    }

    def create() {
        respond new CourseType(params)
    }

    def save(CourseType courseType) {
        if (courseType == null) {
            notFound()
            return
        }

        try {
            courseTypeService.save(courseType)
        } catch (ValidationException e) {
            respond courseType.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'courseType.label', default: 'CourseType'), courseType.id])
                redirect courseType
            }
            '*' { respond courseType, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond courseTypeService.get(id)
    }

    def update(CourseType courseType) {
        if (courseType == null) {
            notFound()
            return
        }

        try {
            courseTypeService.save(courseType)
        } catch (ValidationException e) {
            respond courseType.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'courseType.label', default: 'CourseType'), courseType.id])
                redirect courseType
            }
            '*'{ respond courseType, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        courseTypeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'courseType.label', default: 'CourseType'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'courseType.label', default: 'CourseType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
