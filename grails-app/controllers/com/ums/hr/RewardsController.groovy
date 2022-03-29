package com.ums.hr


import com.ums.hr.Teacher
import grails.converters.JSON
import grails.gorm.PagedResultList
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class RewardsController {

    RewardsService rewardsService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond rewardsService.list(params), model:[rewardsCount: rewardsService.count()]
    }
    def chart() {

        log.info("[Rewards CHART PARAMS]"+params)

        def criteria = Rewards.createCriteria()
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

        PagedResultList criteriaResult = Rewards.createCriteria().list([max: length, offset:start]) {
            readOnly true
            or {
                ilike('rewardName', '%' + queryString + '%')
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
        respond rewardsService.get(id)
    }

    def create() {
        respond new Rewards(params)
    }

    def save(Rewards rewards) {
        if (rewards == null) {
            notFound()
            return
        }

        try {
            rewardsService.save(rewards)
        } catch (ValidationException e) {
            respond rewards.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'rewards.label', default: 'Rewards'), rewards.id])
                if (params.isModal) {
                    print("is modal "+params.isModal)
                    redirect(uri: "/teacher/profile/"+params.teacher,params: [rewardsDM: rewards])
                }else {
                    redirect rewards
                }

            }
            '*' { respond rewards, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond rewardsService.get(id)
    }

    def update(Rewards rewards) {
        if (rewards == null) {
            notFound()
            return
        }

        try {
            rewardsService.save(rewards)
        } catch (ValidationException e) {
            respond rewards.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'rewards.label', default: 'Rewards'), rewards.id])
                redirect rewards
            }
            '*'{ respond rewards, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        rewardsService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'rewards.label', default: 'Rewards'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'rewards.label', default: 'Rewards'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
