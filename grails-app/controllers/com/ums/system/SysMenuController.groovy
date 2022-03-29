package com.ums.system


import com.ums.hr.Teacher
import grails.converters.JSON
import grails.gorm.PagedResultList
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SysMenuController {

    SysMenuService sysMenuService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond sysMenuService.list(params), model:[sysMenuCount: sysMenuService.count()]
    }
    def chart() {

        log.info("[SysMenu CHART PARAMS]"+params)

        def criteria = SysMenu.createCriteria()
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

        String sortName = params[dataTableOrderColumnName] ?: "menuName"
        String sortDir = params["order[0][dir]"] ?: "asc"

        String queryString = params["search[value]"]

        PagedResultList criteriaResult = SysMenu.createCriteria().list([max: length, offset:start]) {
            readOnly true
            or {
                ilike('menuName', '%' + queryString + '%')
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
        respond sysMenuService.get(id)
    }

    def create() {
        respond new SysMenu(params)
    }

    def save(SysMenu sysMenu) {
        if (sysMenu == null) {
            notFound()
            return
        }

        try {
            sysMenuService.save(sysMenu)
        } catch (ValidationException e) {
            respond sysMenu.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sysMenu.label', default: 'SysMenu'), sysMenu.id])
                redirect sysMenu
            }
            '*' { respond sysMenu, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond sysMenuService.get(id)
    }

    def update(SysMenu sysMenu) {
        if (sysMenu == null) {
            notFound()
            return
        }

        try {
            sysMenuService.save(sysMenu)
        } catch (ValidationException e) {
            respond sysMenu.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sysMenu.label', default: 'SysMenu'), sysMenu.id])
                redirect sysMenu
            }
            '*'{ respond sysMenu, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        sysMenuService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sysMenu.label', default: 'SysMenu'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sysMenu.label', default: 'SysMenu'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
