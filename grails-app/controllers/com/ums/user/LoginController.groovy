package com.ums.user

import com.ums.system.SysMenu
import com.ums.system.SysUser
import com.ums.system.SysGroupMenu
import com.ums.system.SysGroupMenuPermission
import com.ums.system.SysGroup
import grails.converters.JSON
import helper.FunctionHelper

class LoginController {

    def index = {redirect(uri: "/")}

    public List getSubMenu(long topMenuId)
    {
        List  subMenuList = SysMenu.findAll("from SysMenu as m where m.topMenu = "+topMenuId);

        List subList = new ArrayList();

        for(int i=0; i <subMenuList.size(); i++)
        {
            subMenuList[i].setSubMenuList(getSubMenu(subMenuList[i].getId()));
            subList.add(subMenuList[i]);

            log.debug("[SUB MENU NAME]"+subMenuList[i].getMenuName())
        }
        return subList;
    }

    public List createMenu(def sysUserGroup)
    {

        def sysGroupMenus = SysGroupMenu.findAllByGroupAndIsActive(sysUserGroup,true)

        List  topMenuList = new ArrayList();

        sysGroupMenus.each {it->
            topMenuList.add(it.menu)
        }

        Map menuMap = new HashMap();
        List topList = new ArrayList();

        for(int i=0; i < topMenuList.size(); i++)
        {
            if(topMenuList[i]?.getTopMenu() == null){

                topMenuList[i].setSubMenuList(getSubMenu(topMenuList[i].getId()));

                topList.add(topMenuList[i]);

                log.debug("[TOP MENU NAME]"+topMenuList[i].getMenuName())

            }
        }

        return topList;
    }

    public List createAllMenu()
    {
        def sysGroupMenus = SysMenu.list()

        List  topMenuList = new ArrayList();

        sysGroupMenus.each {it->
            topMenuList.add(it)
        }

        Map menuMap = new HashMap();
        List topList = new ArrayList();

        for(int i=0; i < topMenuList.size(); i++)
        {
            if(topMenuList[i].getTopMenu() == null){


                topMenuList[i].setSubMenuList(getSubMenu(topMenuList[i].getId()));

                topList.add(topMenuList[i]);

                log.debug("[TOP MENU NAME]"+topMenuList[i].getMenuName())

            }
        }

        return topList;
    }

    def createPermissionMap(){

        def permissionMap = [:]

        def actionList = []

        def grouMenuList =  SysGroupMenuPermission.findAllByGroupNameAndIsTrue(session.sysUserGroup.groupName,true)

        grouMenuList.each {it->

            if (!permissionMap.containsKey(it.controllerName)){

                permissionMap.putAt(it.controllerName,[])

                permissionMap.get(it.controllerName).add(it.actionName.trim())

            }  else{

                permissionMap.get(it.controllerName).add(it.actionName.trim())

            }
        }

        session["permission"] = permissionMap;

         log.debug("[Permission Map]"+permissionMap);
         //println("[Permission Map]"+session["permission"]);
        //permissionMap.putAt("student",["create","save","edit","list","show","index","renderAll","getSumKhoroo","update","printpayment"])


    }

   /* def loginFromApp={

        sys.user.Login cmd = new Login(params);

        def sessionLooker = new SessionLooker()

        def sysUser

        def studentCode = cmd.userId;

        println "[START LOGIN COMMAND]"+params+cmd.userId

        log.debug "Attempting login"+cmd.userId


        def helper = new FunctionHelper();

        if (studentCode.contains("D") || studentCode.contains("E"))
        {
            sysUser = SysUser.findByUserIdAndIsActive(studentCode,true);

        }else{

            sysUser = SysUser.findByUserIdAndIsActive("D"+studentCode,true);

            if (!sysUser) {
                sysUser = SysUser.findByUserIdAndIsActive("E"+studentCode,true);
            }
        }

        //sysUser = SysUser.findByUserIdAndIsActive(cmd.userId,true);




        if (sysUser) {
            sessionLooker = SessionLooker.findByUserId(sysUser.userId);

            if (!sessionLooker) {

                sessionLooker = new SessionLooker()

            }
            sessionLooker.setSessionId(helper.getRamdom(9999999).toString() + new Date().encodeAsMD5())

            sessionLooker.setUserId(sysUser.userId)

            sessionLooker.save();

            sysUser.lastLogin = new Date();

            sysUser.save(flush: true);

            render sessionLooker as JSON;

        } else {

            render "{NOTSUCCESS}"


        }





    }
*/
    def login = {

        log.debug "[START LOGIN COMMAND]"+params

        Login cmd = new Login(params);

        log.info "Attempting login"+cmd.userId

        if (cmd.hasErrors()) {

            flash.message = "User name or password not correct !!!!"
            redirect(controller: "home",action: "index" )

        } else {

            def helper = new FunctionHelper();

            def sysUser = SysUser.findByUserIdAndPasswordAndIsActive(cmd.userId,helper.strtomd5(cmd?.password),true);

            if (sysUser) {

                println("-------"+sysUser.sysGroup)
                List MenuList = new ArrayList();
                MenuList = createMenu(sysUser.sysGroup);
                session.sysUser = sysUser
                session.sysUserGroup = sysUser.sysGroup
                //session.university = University.list().first()
                session.menuList = MenuList;
                session["allmenu"] = createAllMenu();
                session["homepage"] =  sysUser.sysGroup.homePage

                createPermissionMap();
                sysUser.lastLogin = new Date();
                //sysUser.save(flush:true);
                println "Session d" +sysUser+session.sysUserGroup;
                redirect(controller: "home",action:"index")

                return

            } else {

                flash.loginError = "Invalid username or password. Please try again"

                flash.message =  "Invalid username or password. Please try again"

                render(view: "login",userName:cmd.userId )
            }

        }

    }

    def logout = {

        if (session.sysUser) {

            session.sysUser = null
            session.menuList = null;

        }
        redirect(uri: "/login")

    }

    def form = {

    }
}