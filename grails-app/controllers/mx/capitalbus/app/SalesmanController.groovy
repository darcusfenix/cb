package mx.capitalbus.app

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import mx.capitalbus.app.repository.SalesmanRepository
import mx.capitalbus.app.user.Salesman

@Secured(value = ["hasAnyRole('ROLE_SUPER_ADMIN','ROLE_SALESMAN', 'ROLE_ADMIN_CONTROL_BRACELET')"])
class SalesmanController {

    SalesmanRepository salesmanRepository
    def salesmanService
    def braceletRepository
    def springSecurityService

    def index(String q) {
        def s = q ?: params.list('q')
        render(salesmanRepository.getBySearch(s) as JSON)
    }
    def create(String q) {
        def s = q ?: params.list('q')
        render(salesmanRepository.getBySearch(s) as JSON)
    }

    def get(Integer id) {
        def v = salesmanRepository.getById(id ?: params.int('id'))
        if ( v != null) {
            render(v as JSON);
        } else {
            response.status = 404
            render([message: message(code: "vendedor.notFound")] as JSON)
        }
    }

    def saveCorteCaja() {
        def j = params.list('json')
        def r = []
        if (j != null)
            r = salesmanService.saveBraceletsSold(j)
        render (r as JSON)
    }

    def acuseRecibido() {

        def sd = params.sd
        def ed = params.ed
        def ss = params.ss

        def principal = springSecurityService.principal
        long id = principal.id

        def results = []
        def totalBracelets = 0
        def totalMoney = 0

        results = braceletRepository.getHistoryBySalesmanYesSold(sd, ed, id, ss)

        results.each { i ->
            totalBracelets += i[1]
            totalMoney += i[1] * i[2].cost
        }

        def s = Salesman.findById(id)
        if (results)
            render(view:"acuse-recibido",  model: [ resultados : results, salesman : s, date : ss, totalBracelets : totalBracelets, totalMoney : totalMoney])
        else
            redirect(uri: "/")
    }

}
