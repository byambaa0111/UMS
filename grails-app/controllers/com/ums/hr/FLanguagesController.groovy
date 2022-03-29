package com.ums.hr

import grails.converters.JSON
import grails.gorm.PagedResultList
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class FLanguagesController {

    FLanguagesService FLanguagesService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond FLanguagesService.list(params), model:[FLanguagesCount: FLanguagesService.count()]
    }

    def data_for_datatable(){

        println("[DATA FOR DATATABLES]"+params)
        int draw   = params.int("draw")
        int length = params.int("length")
        int start  = params.int("start")

        def language      =  params["language"];

        String dataTableOrderColumnIdx = params["order[0][column]"]
        String dataTableOrderColumnName = "columns[" + dataTableOrderColumnIdx + "][data]"

        String sortName = params[dataTableOrderColumnName] ?: "id"
        String sortDir = params["order[0][dir]"] ?: "asc"

        String queryString = params["search[value]"]

        PagedResultList criteriaResult = FLanguages.createCriteria().list([max: length, offset:start]) {
            readOnly true
            or {
                ilike('language', '%' + queryString + '%')
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
        respond FLanguagesService.get(id)
    }

    def create() {
        respond new FLanguages(params)
    }

    def save(FLanguages FLanguages) {
        if (FLanguages == null) {
            notFound()
            return
        }

        try {
            FLanguagesService.save(FLanguages)
        } catch (ValidationException e) {
            respond FLanguages.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'FLanguages.label', default: 'FLanguages'), FLanguages.id])
                if (params.isModal) {
                    print("is modal "+params.isModal)
                    redirect(uri: "/teacher/profile/"+params.teacher,params: [FLanguagesDM: FLanguages])
                }else{
                    redirect FLanguages
                }

            }
            '*' { respond FLanguages, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond FLanguagesService.get(id)
    }

    def update(FLanguages FLanguages) {
        if (FLanguages == null) {
            notFound()
            return
        }

        try {
            FLanguagesService.save(FLanguages)
        } catch (ValidationException e) {
            respond FLanguages.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'FLanguages.label', default: 'FLanguages'), FLanguages.id])
                redirect FLanguages
            }
            '*'{ respond FLanguages, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        FLanguagesService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'FLanguages.label', default: 'FLanguages'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'FLanguages.label', default: 'FLanguages'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
