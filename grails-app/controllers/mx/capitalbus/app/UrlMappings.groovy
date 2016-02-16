package mx.capitalbus.app

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')


        "/salesman/$id"(controller: 'salesman', action: 'get')
        "/bracelet/history"(controller: 'bracelet', action: 'getListOfCreations')
        "/bracelet/date"(controller: 'bracelet', action: 'getCSV')
        "/costBracelet/circuit/$id"(controller: 'costBracelet', action: 'costBraceletByCircuit')
    }
}
