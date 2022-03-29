package com.ums.system

import grails.converters.JSON
import grails.gorm.PagedResultList
import grails.validation.ValidationException
import helper.FunctionHelper

import static org.springframework.http.HttpStatus.*

class SysUserController {

    SysUserService sysUserService
    FunctionHelper helper = new FunctionHelper();
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        log.print("[SYSUSER INDEX PARAMS]"+params)
        params.max = Math.min(max ?: 10, 100)
        respond sysUserService.list(params), model:[sysUserCount: sysUserService.count()]
    }
    def data_for_datatable() {

        print("[DATA FOR DATATABLES]"+params)
        int draw = params.int("draw")
        int length = params.int("length")
        int start = params.int("start")
        def sysGroupId = params.get("sysGroupName[]")

        String dataTableOrderColumnIdx = params["order[0][column]"]
        String dataTableOrderColumnName = "columns[" + dataTableOrderColumnIdx + "][data]"

        String sortName = params[dataTableOrderColumnName] ?: "id"
        String sortDir = params["order[0][dir]"] ?: "asc"

        String queryString = params["search[value]"]

        PagedResultList criteriaResult = SysUser.createCriteria().list([max: length, offset:start]) {
            readOnly true
            or {
                ilike('userId', '%' + queryString + '%')
                ilike('fullName', '%' + queryString + '%')
                ilike('email', '%' + queryString + '%')
                ilike('emailMnu', '%' + queryString + '%')
            }
            and {
                if (sysGroupId != "all") {
                    eq("sysGroup",SysGroup.get(sysGroupId))
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
        respond sysUserService.get(id)
    }

    def create() {
        respond new SysUser(params)
    }

    def save(SysUser sysUser) {
        if (sysUser == null) {
            notFound()
            return
        }

        try {
            sysUser.password = helper.strtomd5(sysUser.password)
            sysUserService.save(sysUser)
        } catch (ValidationException e) {
            respond sysUser.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sysUser.label', default: 'SysUser'), sysUser.id])
                redirect sysUser
            }
            '*' { respond sysUser, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond sysUserService.get(id)
    }

    def update(SysUser sysUser) {
        if (sysUser == null) {
            notFound()
            return
        }

        try {
            sysUser.password = helper.strtomd5(sysUser.password)
            sysUserService.save(sysUser)
        } catch (ValidationException e) {
            respond sysUser.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sysUser.label', default: 'SysUser'), sysUser.id])
                redirect sysUser
            }
            '*'{ respond sysUser, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        sysUserService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sysUser.label', default: 'SysUser'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sysUser.label', default: 'SysUser'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
