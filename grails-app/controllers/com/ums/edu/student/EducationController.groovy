package com.ums.edu.student


import com.ums.hr.Teacher
import grails.converters.JSON
import grails.gorm.PagedResultList
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class EducationController {

    EducationService educationService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond educationService.list(params), model:[educationCount: educationService.count()]
    }
    def chart() {

        log.info("[Education CHART PARAMS]"+params)

        def criteria = Education.createCriteria()
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

        def degree     =  params["degree"];

        String dataTableOrderColumnIdx = params["order[0][column]"]
        String dataTableOrderColumnName = "columns[" + dataTableOrderColumnIdx + "][data]"

        String sortName = params[dataTableOrderColumnName] ?: "id"
        String sortDir = params["order[0][dir]"] ?: "asc"

        String queryString = params["search[value]"]

        PagedResultList criteriaResult = Education.createCriteria().list([max: length, offset:start]) {
            readOnly true
            or {
                ilike('degree', '%' + queryString + '%')
                ilike('specialization', '%' + queryString + '%')
                ilike('country', '%' + queryString + '%')
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
        respond educationService.get(id)
    }

    def create() {
        log.info("[CREATE EDUCATION PARAMS]"+params)
        respond new Education(params)
    }

    def save(Education education) {
        log.info("[CREATE EDUCATION PARAMS]"+params)
        if (education == null) {
            notFound()
            return
        }

        try {
            educationService.save(education)

        } catch (ValidationException e) {
            respond education.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'education.label', default: 'Education'), education.id])
                if (params.isModal) {
                    print("is modal "+params.isModal)
                    redirect(uri: "/teacher/profile/"+params.teacher,params: [educationDM: education])
                }else{
                    redirect education
                }

            }
            '*' { respond education, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond educationService.get(id)
    }

    def update(Education education) {
        if (education == null) {
            notFound()
            return
        }

        try {
            educationService.save(education)
        } catch (ValidationException e) {
            respond education.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'education.label', default: 'Education'), education.id])
                redirect education
            }
            '*'{ respond education, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        educationService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'education.label', default: 'Education'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'education.label', default: 'Education'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
