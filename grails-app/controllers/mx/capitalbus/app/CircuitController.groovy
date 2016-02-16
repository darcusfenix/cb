package mx.capitalbus.app

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import mx.capitalbus.app.circuit.Circuit


@Secured('ROLE_SUPER_ADMIN')
class CircuitController {

    def index() {
        render (Circuit.findAllByEnabled(true) as JSON)
    }

}
