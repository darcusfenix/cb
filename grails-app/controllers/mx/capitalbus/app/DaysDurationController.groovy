package mx.capitalbus.app

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import mx.capitalbus.app.bracelet.DaysDuration
@Secured(['ROLE_SUPER_ADMIN', 'ROLE_VENDEDOR'])
class DaysDurationController {

    def index() {
        render (DaysDuration.list() as JSON)
    }
}
