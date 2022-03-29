package com.ums.hr


import com.ums.hr.Teacher
import grails.converters.JSON
import grails.gorm.PagedResultList
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ProjectsController {

    ProjectsService projectsService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond projectsService.list(params), model:[projectsCount: projectsService.count()]
    }
    def chart() {

        log.info("[Projects CHART PARAMS]"+params)

        def criteria = Projects.createCriteria()
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

        PagedResultList criteriaResult = Projects.createCriteria().list([max: length, offset:start]) {
            readOnly true
            or {
                ilike('projectName', '%' + queryString + '%')
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
        respond projectsService.get(id)
    }

    def create() {
        respond new Projects(params)
    }

    def save(Projects projects) {
        if (projects == null) {
            notFound()
            return
        }

        try {
            projectsService.save(projects)
        } catch (ValidationException e) {
            respond projects.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'projects.label', default: 'Projects'), projects.id])

                if (params.isModal) {
                    print("is modal "+params.isModal)
                    redirect(uri: "/teacher/profile/"+params.teacher,params: [projectsDM: projects])
                }else{
                    redirect projects
                }
            }
            '*' { respond projects, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond projectsService.get(id)
    }

    def update(Projects projects) {
        if (projects == null) {
            notFound()
            return
        }

        try {
            projectsService.save(projects)
        } catch (ValidationException e) {
            respond projects.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'projects.label', default: 'Projects'), projects.id])
                redirect projects
            }
            '*'{ respond projects, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        projectsService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'projects.label', default: 'Projects'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'projects.label', default: 'Projects'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
