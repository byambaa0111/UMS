package com.ums.system

class HomeController {

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def index = {

        println("[home controller]")

        if (session['sysUser']) {
            log.info "login in"
            respond "aldaa garlaa", view:'index'
        }else{
            log.info "not login in"
            redirect(controller: "home",action:"login")
        }
    }
    def login(){

    }

}
