package mx.capitalbus.app

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import mx.capitalbus.app.bracelet.CostBracelet
import mx.capitalbus.app.circuit.Circuit
@Secured(['ROLE_SUPER_ADMIN', 'ROLE_VENDEDOR'])
class CostBraceletController {

    def costBraceletByCircuit() {
        def r  = CostBracelet.findAllByCircuit(Circuit.findById(params.int('id')))
        render ([r] as JSON)
    }
}
