package mx.capitalbus.app

import grails.converters.JSON
import grails.transaction.Transactional
import groovy.json.JsonSlurper
import mx.capitalbus.app.bracelet.Bracelet
import mx.capitalbus.app.bracelet.CostBracelet

@Transactional
class SalesmanService {

    def springSecurityService

    def saveBraceletsSold(String j) {
       // TimeZone.setDefault(TimeZone.getTimeZone("GMT-6"));
        def jsonSlurper = new JsonSlurper()
        def object = jsonSlurper.parseText(j)

        def listCost = CostBracelet.list()
        Date d = new Date()

        def principal = springSecurityService.principal
        long id = principal.id


        for (String a : object) {
            def cc = JSON.parse(a)

            def item = listCost.find { p -> p.id == cc.idCost }

            def b = Bracelet.findById(cc.idBracelet)

            if (b != null && b.salesman.id == id)
            {
                b.sold = true
                b.soldDate = d
                b.save(flush:true);
            }

        }
        ["aa":"aaaa"]
    }
}
