package ums

import com.ums.system.SysActionLog


class SecurityFilters {

    def filters = {

        all(controller: '*', action: '*') {

            before = {

                def appActionList = ["getAllGradesForAStudent", "getStudentInfo", "getPaymentsForAStudent", "getWifiCode",'getFaculties','getCourseContent']
                def appControllerList = ["student", "payment", "WifiAccounts","mobile"]
                if (session.sysUser == null && appControllerList.contains(controllerName) && appActionList.contains(actionName))  {
                      println("[LOGIN FORM APPS]"+params)
                } else{
/*
                    def sysActionLog = new SysActionLog();
                    sysActionLog.controllersName = controllerName
                    sysActionLog.actionsName = actionName
                    sysActionLog.parametersName = params
                    sysActionLog.createdDate    = new Date()
                    sysActionLog.currentUser    = session['sysUser']
                    sysActionLog.currentUserName = session['sysUser']?.userId
                    sysActionLog.ipAddress = InetAddress.getLocalHost().getHostAddress();
                    sysActionLog.save(flush: true)*/

                    if (session.sysUser != null && controllerName != null) {

                        println("inside if  " + session.sysUser.userId + session["permission"])

                        if (session.sysUser.userId) {

                            def permissionMap = session["permission"]
                            def actionList = ["create", "save", "edit", "list", "show"]
                            def actionBoolean = false

                            println("[SESSION LOGIN HIISEN USER] " + session.sysUser.userId)

                            if (permissionMap.containsKey(controllerName)) {

                                println(" [SYSTEMD ZOVSHOORSON UILDEL] " + permissionMap.containsKey(controllerName))

                                permissionMap.get(controllerName).each { action ->
                                    if (action.contains(actionName)) {
                                        actionBoolean = true
                                        println("[SYSTEMD ZOVSHOORSON UILDEL]=2")
                                    }
                                }
                            } else {
                                 actionBoolean = false;
                            }

                            if (session.sysUser.userId == "admin") {
                                actionBoolean = true
                                println("[END ZOVHON ADMIN ULDEL HIINE]")
                            }

                            if (!actionBoolean) {
                                //redirect(uri: "//")
                                println("[SYSTEMD ZOVSHOOROOGUI UILDEL]")
                                render(template: "/nopermission")
                                return
                            } else {
                                println("[OK GO]" + actionBoolean)
                            }
                        } else {
                            render(template: "/nopermission")
                            return
                            // redirect(uri: "//")
                        }

                    } else {
                        if (controllerName == "login") {
                            return
                        } else {
                             // render(template: "/nopermission")
                            redirect(controller: "login",action: "login")
                            println("================================21")
                        }
                    }
                }
            }

            after = { Map model ->
                println "[session start after]"
            }
            afterView = { Exception e ->

            }
        }
    }
}
