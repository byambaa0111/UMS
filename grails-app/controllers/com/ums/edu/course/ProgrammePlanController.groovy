package com.ums.edu.course

import com.ums.edu.uni.Department
import com.ums.hr.Teacher
import grails.converters.JSON
import grails.gorm.PagedResultList
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ProgrammePlanController {

    ProgrammePlanService programmePlanService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        def prog = ProgrammePlan.list()
        prog.each {obj->
            obj.setPlanName(obj.programme.programmeMN)

        }
        params.max = Math.min(max ?: 10, 100)
        respond programmePlanService.list(params), model:[programmePlanCount: programmePlanService.count()]
    }
    def chart() {

        log.info("[ProgrammePlan CHART PARAMS]"+params)

        def criteria = ProgrammePlan.createCriteria()
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
        def departments  = params.list(["departments[]"]);

        def departmentList= [];

        departments.each{obj->
            departmentList.add(Department.get(obj))
        }
        String dataTableOrderColumnIdx = params["order[0][column]"]
        String dataTableOrderColumnName = "columns[" + dataTableOrderColumnIdx + "][data]"

        String sortName = params[dataTableOrderColumnName] ?: "planIndex"
        String sortDir = params["order[0][dir]"] ?: "asc"

        String queryString = params["search[value]"]

        PagedResultList criteriaResult = ProgrammePlan.createCriteria().list([max: length, offset:start]) {
            readOnly true
            or {
                ilike('planIndex', '%' + queryString + '%')
            }and {
                log.info("==========department id list"+departmentList)
                if (departments!= null)  'in'('department',departmentList)
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
    def treeViewCoursesInPlan(Long id){

       /* <g:render template="treeVIewCoursesInPlan" model="[courseInPlanlist:this.programmePlan.coursesInPlans]"></g:render>*/

        render template: "treeViewCoursesInPlan", model:[courseInPlanlist:ProgrammePlan.get(id)?.coursesInPlans]
    }
    def show(Long id) {
        respond programmePlanService.get(id)
    }

    def create() {
        respond new ProgrammePlan(params)
    }

    def save(ProgrammePlan programmePlan) {
        if (programmePlan == null) {
            notFound()
            return
        }

        try {
            programmePlanService.save(programmePlan)
        } catch (ValidationException e) {
            respond programmePlan.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'programmePlan.label', default: 'ProgrammePlan'), programmePlan.id])
                redirect programmePlan
            }
            '*' { respond programmePlan, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond programmePlanService.get(id)
    }

    def update(ProgrammePlan programmePlan) {
        if (programmePlan == null) {
            notFound()
            return
        }

        try {
            programmePlanService.save(programmePlan)
        } catch (ValidationException e) {
            respond programmePlan.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'programmePlan.label', default: 'ProgrammePlan'), programmePlan.id])
                redirect programmePlan
            }
            '*'{ respond programmePlan, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        programmePlanService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'programmePlan.label', default: 'ProgrammePlan'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'programmePlan.label', default: 'ProgrammePlan'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
