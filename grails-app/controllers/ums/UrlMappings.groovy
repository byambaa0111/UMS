package ums

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "/"(controller: "home", action: "index");
        "/login"(controller: "login", action: "login");
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
