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

    def acuseRecibidoCorteCaja() {

        def ss = params.ss

        def principal = springSecurityService.principal
        long id = principal.id

        def results = []
        def series = []
        def totalBracelets = 0
        def totalMoney = 0
        def s = Salesman.findById(id)

        results = braceletRepository.getBySalesmanAndDate(s, ss, true)
        series = braceletRepository.getCostBraceletsBySalesman(s, ss, true)

        series.each { item ->
            totalBracelets += item[1]
            totalMoney += item[0].cost * item[1]
        }

        log.error(results)


        if (results)
            render(view: "acuse-recibido-cj", model: [resultados: results, salesman: s, date: ss, totalBracelets: totalBracelets, totalMoney: totalMoney, series: series])
        else
            redirect(uri: "/")
    }

    def acuseRecibidoAsignacion() {

        def ss = params.ss

        def principal = springSecurityService.principal
        long id = principal.id

        def results = []
        def series = []
        def totalBracelets = 0
        def totalMoney = 0
        def s = Salesman.findById(id)

        results = braceletRepository.getBySalesmanAndDate(s, ss, false)
        series = braceletRepository.getCostBraceletsBySalesman(s, ss, false)

        series.each { item ->
            totalBracelets += item[1]
            totalMoney += item[0].cost * item[1]
        }

        log.error(results)


        if (results)
            render(view:"acuse-recibido-as",  model: [resultados: results, salesman: s, date: ss, totalBracelets: totalBracelets, totalMoney: totalMoney, series: series])
        else
            redirect(uri: "/")
    }

}
