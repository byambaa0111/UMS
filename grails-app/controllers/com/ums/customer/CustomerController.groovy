package com.ums.customer

import grails.converters.JSON
import grails.gorm.PagedResultList
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CustomerController {

    CustomerService customerService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond customerService.list(params), model:[customerCount: customerService.count()]
    }
        def data_for_datatable() {
            int draw = params.int("draw")
            int length = params.int("length")
            int start = params.int("start")

            String dataTableOrderColumnIdx = params["order[0][column]"]
            String dataTableOrderColumnName = "columns[" + dataTableOrderColumnIdx + "][data]"

            String sortName = params[dataTableOrderColumnName] ?: "id"
            String sortDir = params["order[0][dir]"] ?: "asc"

            String queryString = params["search[value]"]

            PagedResultList criteriaResult = Customer.createCriteria().list([max: length, offset:start]) {
                readOnly true
                or {
                    ilike('firstName', '%' + queryString + '%')
                    ilike('lastName', '%' + queryString + '%')
                    ilike('emailAddress', '%' + queryString + '%')
                    ilike('city', '%' + queryString + '%')
                    ilike('phoneNumber', '%' + queryString + '%')
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

        respond customerService.get(id)
    }

    def create() {
        respond new Customer(params)
    }

    def save(Customer customer) {
        if (customer == null) {
            notFound()
            return
        }

        try {
            customerService.save(customer)
        } catch (ValidationException e) {
            respond customer.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'customer.label', default: 'Customer'), customer.id])
                redirect customer
            }
            '*' { respond customer, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond customerService.get(id)
    }

    def update(Customer customer) {
        if (customer == null) {
            notFound()
            return
        }

        try {
            customerService.save(customer)
        } catch (ValidationException e) {
            respond customer.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'customer.label', default: 'Customer'), customer.id])
                redirect customer
            }
            '*'{ respond customer, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        customerService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'customer.label', default: 'Customer'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'customer.label', default: 'Customer'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
