package com.ums.system


import com.ums.hr.Teacher
import grails.converters.JSON
import grails.gorm.PagedResultList
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SysGroupMenuController {

    SysGroupMenuService sysGroupMenuService
    SysGroupMenuPermissionService sysGroupMenuPermissionService
    int sysGroupId;
    List<SysGroupMenu>  sysGroupMenuList;

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond sysGroupMenuService.list(params), model:[sysGroupMenuCount: sysGroupMenuService.count()]
    }
    def chart() {

        log.info("[SysGroupMenu CHART PARAMS]"+params)

        def criteria = SysGroupMenu.createCriteria()
        def results = criteria.list {
            projections{
                groupProperty('type')
                count("type")
            }
        }

        render results as JSON
    }

    def search = {

        println("[search params ]"+params);

        List nowMenuList = new ArrayList();

        if (params['id'] != null){
            //    this.sysGroupId = Integer.getInteger(params?.id);

            def sysGroup = SysGroup.get(params?.id);

            sysGroupMenuList= SysGroupMenu.findAllByGroupAndIsActive(sysGroup,true)  //("from SysGroupMenu as m where m.group = "+sysGroup.id);

            println("[MENUS ]"+sysGroupMenuList)

            if(sysGroupMenuList != null && sysGroupMenuList[0] != null)
            {
                sysGroupMenuList.each{sysGroupNowMenu ->
                    nowMenuList.add(sysGroupNowMenu.menu);
                }

                sysGroupMenuList[0].putMenu(nowMenuList);
                //render(view:'create',model:[sysGroupMenuInstance:sysGroupMenuList[0]])
                render(template:'sysMenus', model:[sysGroupMenuInstanceList:sysGroupMenuList[0]])

            }else{
                flash.message = "${sysGroup} d menu alga baina";
                println("[now]");
                render(template:'sysMenus', model:[sysGroupMenuInstanceList:null])
            }
        }else{
            render(template:'sysMenus', model:[sysGroupMenuInstanceList:null])
        }
    }

    def savePermission(){

        println("[ Save Permission ]"+params)

        if (params?.groupId){

            String uniquiKey =  new String(SysGroup.get(params?.groupId).groupName+params?.controllerName+params?.actionName?.trim())?.trim()

            println (uniquiKey)

            if (!SysGroupMenuPermission.findByUniquiKey(uniquiKey)){

                def sysGroupMenuPermissionInstance = new SysGroupMenuPermission(params)

                def sysGroupMenuInstance = SysGroupMenu.findByGroupAndMenu(SysGroup.get(params?.groupId),SysMenu.get(params?.menuId))
                sysGroupMenuPermissionInstance.controllerName = params?.controllerName
                sysGroupMenuPermissionInstance.actionName = params?.actionName
                sysGroupMenuPermissionInstance.menu = SysMenu.get(params?.menuId)
                sysGroupMenuPermissionInstance.groupName =  SysGroup.get(params?.groupId).groupName
                sysGroupMenuPermissionInstance.sysGroupMenu = sysGroupMenuInstance
                sysGroupMenuPermissionInstance.isTrue = true
                sysGroupMenuPermissionInstance.setUniquiKey()

                try {
                    sysGroupMenuPermissionService.save(sysGroupMenuPermissionInstance)
                } catch (ValidationException e) {
                    println "Admin group does not validate! Errors:"
                    respond sysGroupMenuPermissionInstance.errorsallErrors.each {
                        println "==========="+it
                    }
                    return
                }

            }else{
                def sysGroupMenuPermissionInstance =  SysGroupMenuPermission.findByUniquiKey(uniquiKey)

                if (sysGroupMenuPermissionInstance.isTrue){
                    sysGroupMenuPermissionInstance.isTrue = false
                }  else{
                    sysGroupMenuPermissionInstance.isTrue = true
                }

                try {
                    sysGroupMenuPermissionService.save(sysGroupMenuPermissionInstance)
                } catch (ValidationException e) {
                    println "Admin group does not validate! Errors:"
                    respond sysGroupMenuPermissionInstance.errorsallErrors.each {
                        println "==========="+it
                    }
                    return
                }

            }


        }

    }

    def showList(){

        println("[ SHOW LIST ]"+params?.groupId)

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        if (params?.groupId){
            def menus = SysGroupMenu.findAllByGroupAndIsActive(SysGroup.get(params?.groupId),true).menu;
        }

        render(template: "/sysGroupMenu/groupMenuPermission", model: [sysGroupMenuInstanceList:params?.groupId ? SysGroupMenu.findAllByGroupAndIsActive(SysGroup.get(params?.groupId),true) :null ])


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

        PagedResultList criteriaResult = SysGroupMenu.createCriteria().list([max: length, offset:start]) {
            readOnly true
            or {
                ilike('groupName', '%' + queryString + '%')
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
        respond sysGroupMenuService.get(id)
    }

    def create() {
        respond new SysGroupMenu(params)
    }

    def save(SysGroupMenu sysGroupMenu) {
        if (sysGroupMenu == null) {
            notFound()
            return
        }

        try {
            sysGroupMenuService.save(sysGroupMenu)
        } catch (ValidationException e) {
            respond sysGroupMenu.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sysGroupMenu.label', default: 'SysGroupMenu'), sysGroupMenu.id])
                redirect sysGroupMenu
            }
            '*' { respond sysGroupMenu, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond sysGroupMenuService.get(id)
    }

    def update(SysGroupMenu sysGroupMenu) {
        if (sysGroupMenu == null) {
            notFound()
            return
        }

        try {
            sysGroupMenuService.save(sysGroupMenu)
        } catch (ValidationException e) {
            respond sysGroupMenu.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sysGroupMenu.label', default: 'SysGroupMenu'), sysGroupMenu.id])
                redirect sysGroupMenu
            }
            '*'{ respond sysGroupMenu, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        sysGroupMenuService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sysGroupMenu.label', default: 'SysGroupMenu'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sysGroupMenu.label', default: 'SysGroupMenu'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
