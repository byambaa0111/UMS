<%=packageName ? "package ${packageName}" : ''%>


import grails.converters.JSON
import grails.gorm.PagedResultList

import static org.springframework.http.HttpStatus.*
import org.springframework.transaction.TransactionStatus

class ${className}Controller {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        ${className}.async.task {
            [${propertyName}List: list(params), count: count() ]
        }.then { result ->
            respond result.${propertyName}List, model:[${propertyName}Count: result.count]
        }
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

        PagedResultList criteriaResult = ${className}.createCriteria().list([max: length, offset:start]) {
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
        ${className}.async.get(id).then { ${propertyName} ->
            respond ${propertyName}
        }
    }

    def create() {
        respond new ${className}(params)
    }

    def save(${className} ${propertyName}) {
        ${className}.async.withTransaction { TransactionStatus status ->
            if (${propertyName} == null) {
                status.setRollbackOnly()
                notFound()
                return
            }

            if(${propertyName}.hasErrors()) {
                status.setRollbackOnly()
                respond ${propertyName}.errors, view:'create' // STATUS CODE 422
                return
            }

            ${propertyName}.save flush:true
            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.created.message', args: [message(code: '${propertyName}.label', default: '${className}'), ${propertyName}.id])
                    redirect ${propertyName}
                }
                '*' { respond ${propertyName}, [status: CREATED] }
            }
        }
    }

    def edit(Long id) {
        ${className}.async.get(id).then { ${propertyName} ->
            respond ${propertyName}
        }
    }

    def update(Long id) {
        ${className}.async.withTransaction { TransactionStatus status ->
            def ${propertyName} = ${className}.get(id)
            if (${propertyName} == null) {
                status.setRollbackOnly()
                notFound()
                return
            }

            ${propertyName}.properties = params
            if( !${propertyName}.save(flush:true) ) {
                status.setRollbackOnly()
                respond ${propertyName}.errors, view:'edit' // STATUS CODE 422
                return
            }

            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.updated.message', args: [message(code: '${className}.label', default: '${className}'), ${propertyName}.id])
                    redirect ${propertyName}
                }
                '*'{ respond ${propertyName}, [status: OK] }
            }
        }
    }

    def delete(Long id) {
        ${className}.async.withTransaction { TransactionStatus status ->
            def ${propertyName} = ${className}.get(id)
            if (${propertyName} == null) {
                status.setRollbackOnly()
                notFound()
                return
            }

            ${propertyName}.delete flush:true

            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.deleted.message', args: [message(code: '${className}.label', default: '${className}'), ${propertyName}.id])
                    redirect action:"index", method:"GET"
                }
                '*'{ render status: NO_CONTENT }
            }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: '${propertyName}.label', default: '${className}'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}