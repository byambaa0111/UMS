package com.ums.common

import com.ums.edu.uni.Department
import com.ums.edu.uni.Faculty

class CommonControlController {

    static allowedMethods = [save: "POST", getProPlanByDepId:"POST", update: "PUT", delete: "DELETE"]

    def index() { }


    def getDepartments(){

        log.info("[PARAMS GET DEPARTMENT]"+params)
        def facultyId = params['facultyId'];
        if (facultyId != 'all'){
            render(template:"/student/departmentsByFaculty",model: [departments: Department.findAllByFaculty(Faculty.get(facultyId))])
        }else {
            render "all";
        }

    }
    def getCurriculums(){

        log.info("[PARAMS GET DEPARTMENT]"+params)

        render(template:"/student/programmeByDepartment",model: [curriculums:Department.get(params['departmentId']).curriculums ])
    }

    def getProPlanByDepId(){
        log.info("[PARAMS GET getProPlanByDepId]"+params)
        render "ok"
    }
}
