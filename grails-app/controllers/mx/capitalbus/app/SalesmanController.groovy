package mx.capitalbus.app

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import mx.capitalbus.app.repository.SalesmanRepository

@Secured(value = ["hasAnyRole('ROLE_SUPER_ADMIN','ROLE_SALESMAN', 'ROLE_ADMIN_CONTROL_BRACELET')"])
class SalesmanController {

    SalesmanRepository salesmanRepository
    def salesmanService

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

}
