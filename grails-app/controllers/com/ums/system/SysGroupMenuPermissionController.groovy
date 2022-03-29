package com.ums.system


import com.ums.hr.Teacher
import grails.converters.JSON
import grails.gorm.PagedResultList
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SysGroupMenuPermissionController {

    SysGroupMenuPermissionService sysGroupMenuPermissionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond sysGroupMenuPermissionService.list(params), model:[sysGroupMenuPermissionCount: sysGroupMenuPermissionService.count()]
    }
    def chart() {

        log.info("[SysGroupMenuPermission CHART PARAMS]"+params)

        def criteria = SysGroupMenuPermission.createCriteria()
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

        PagedResultList criteriaResult = SysGroupMenuPermission.createCriteria().list([max: length, offset:start]) {
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
        respond sysGroupMenuPermissionService.get(id)
    }

    def create() {
        respond new SysGroupMenuPermission(params)
    }

    def save(SysGroupMenuPermission sysGroupMenuPermission) {
        if (sysGroupMenuPermission == null) {
            notFound()
            return
        }

        try {
            sysGroupMenuPermissionService.save(sysGroupMenuPermission)
        } catch (ValidationException e) {
            respond sysGroupMenuPermission.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sysGroupMenuPermission.label', default: 'SysGroupMenuPermission'), sysGroupMenuPermission.id])
                redirect sysGroupMenuPermission
            }
            '*' { respond sysGroupMenuPermission, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond sysGroupMenuPermissionService.get(id)
    }

    def update(SysGroupMenuPermission sysGroupMenuPermission) {
        if (sysGroupMenuPermission == null) {
            notFound()
            return
        }

        try {
            sysGroupMenuPermissionService.save(sysGroupMenuPermission)
        } catch (ValidationException e) {
            respond sysGroupMenuPermission.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sysGroupMenuPermission.label', default: 'SysGroupMenuPermission'), sysGroupMenuPermission.id])
                redirect sysGroupMenuPermission
            }
            '*'{ respond sysGroupMenuPermission, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        sysGroupMenuPermissionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sysGroupMenuPermission.label', default: 'SysGroupMenuPermission'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sysGroupMenuPermission.label', default: 'SysGroupMenuPermission'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
