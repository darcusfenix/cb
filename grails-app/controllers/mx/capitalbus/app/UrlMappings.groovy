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
        "/salesman/corteCaja"(controller: 'salesman', action: 'saveCorteCaja')

        "/api/autobus/brazalete/subida/$code"(controller: 'bracelet', action: 'verifyCodeWithScannerFirst')
        "/api/autobus/brazalete/bajada/$code"(controller: 'bracelet', action: 'verifyCodeWithScannerSecond')

        "/bracelet/history"(controller: 'bracelet', action: 'getListOfCreations')
        "/bracelet/salesman/history"(controller: 'bracelet', action: 'getListOfAssignnments')
        "/bracelet/salesman/history/resume"(controller: 'bracelet', action: 'getResumeHistoryByDate')
        "/bracelet/salesman/sold"(controller: 'bracelet', action: 'getMyAssignmentsSold')
        "/bracelet/salesman/notSold"(controller: 'bracelet', action: 'getBraceletsNotSold')
        "/bracelet/salesman/yesSold"(controller: 'bracelet', action: 'getHistoryBySalesmanYesSold')

        "/bracelet/costs"(controller: 'bracelet', action: 'getListOfCreationsByCost')
        "/bracelet/date"(controller: 'bracelet', action: 'getCSV')
        "/bracelet/toAssignForSalesman"(controller: 'bracelet', action: 'toAssignForSalesman')

        "/costBracelet/circuit/$id"(controller: 'costBracelet', action: 'costBraceletByCircuit')

        "/vendedor/acuse/corte-caja/$ss"(view: 'salesman/acuse-recibido-cj', controller: 'salesman', action: 'acuseRecibidoCorteCaja')
        "/vendedor/acuse/asignacion/$ss"(view: 'salesman/acuse-recibido-as', controller: 'salesman', action: 'acuseRecibidoAsignacion')
    }
}
