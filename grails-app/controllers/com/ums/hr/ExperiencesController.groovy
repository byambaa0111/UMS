package com.ums.hr


import com.ums.hr.Teacher
import grails.converters.JSON
import grails.gorm.PagedResultList
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ExperiencesController {

    ExperiencesService experiencesService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond experiencesService.list(params), model:[experiencesCount: experiencesService.count()]
    }
    def chart() {

        log.info("[Experiences CHART PARAMS]"+params)

        def criteria = Experiences.createCriteria()
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

        String sortName = params[dataTableOrderColumnName] ?: "employerName"
        String sortDir = params["order[0][dir]"] ?: "asc"

        String queryString = params["search[value]"]

        PagedResultList criteriaResult = Experiences.createCriteria().list([max: length, offset:start]) {
            readOnly true
            or {
                ilike('employerName', '%' + queryString + '%')
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
        respond experiencesService.get(id)
    }

    def create() {
        respond new Experiences(params)
    }

    def save(Experiences experiences) {
        if (experiences == null) {
            notFound()
            return
        }

        try {
            experiencesService.save(experiences)
        } catch (ValidationException e) {
            respond experiences.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'experiences.label', default: 'Experiences'), experiences.id])

                if (params.isModal) {
                    print("is modal "+params.isModal)
                    redirect(uri: "/teacher/profile/"+params.teacher,params: [experiencesDM: experiences])
                }else {
                    redirect experiences
                }
            }
            '*' { respond experiences, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond experiencesService.get(id)
    }

    def update(Experiences experiences) {
        if (experiences == null) {
            notFound()
            return
        }

        try {
            experiencesService.save(experiences)
        } catch (ValidationException e) {
            respond experiences.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'experiences.label', default: 'Experiences'), experiences.id])
                redirect experiences
            }
            '*'{ respond experiences, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        experiencesService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'experiences.label', default: 'Experiences'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'experiences.label', default: 'Experiences'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
