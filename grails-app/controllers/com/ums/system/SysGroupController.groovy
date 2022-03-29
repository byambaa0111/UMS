package com.ums.system


import com.ums.hr.Teacher
import grails.converters.JSON
import grails.gorm.PagedResultList
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SysGroupController {

    SysGroupService sysGroupService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond sysGroupService.list(params), model:[sysGroupCount: sysGroupService.count()]
    }
    def chart() {

        log.info("[SysGroup CHART PARAMS]"+params)

        def criteria = SysGroup.createCriteria()
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

        String sortName = params[dataTableOrderColumnName] ?: "groupName"
        String sortDir = params["order[0][dir]"] ?: "asc"

        String queryString = params["search[value]"]

        PagedResultList criteriaResult = SysGroup.createCriteria().list([max: length, offset:start]) {
            readOnly true
            or {
                ilike('groupName', '%' + queryString + '%')
                ilike('groupDesc', '%' + queryString + '%')
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
        respond sysGroupService.get(id)
    }

    def create() {
        respond new SysGroup(params)
    }

    def save(SysGroup sysGroup) {
        if (sysGroup == null) {
            notFound()
            return
        }

        try {
            sysGroupService.save(sysGroup)
        } catch (ValidationException e) {
            respond sysGroup.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sysGroup.label', default: 'SysGroup'), sysGroup.id])
                redirect sysGroup
            }
            '*' { respond sysGroup, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond sysGroupService.get(id)
    }

    def update(SysGroup sysGroup) {
        if (sysGroup == null) {
            notFound()
            return
        }

        try {
            sysGroupService.save(sysGroup)
        } catch (ValidationException e) {
            respond sysGroup.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sysGroup.label', default: 'SysGroup'), sysGroup.id])
                redirect sysGroup
            }
            '*'{ respond sysGroup, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        sysGroupService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sysGroup.label', default: 'SysGroup'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sysGroup.label', default: 'SysGroup'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
