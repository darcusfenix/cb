package mx.capitalbus.app

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import mx.capitalbus.app.bracelet.KindPerson
@Secured(['ROLE_SUPER_ADMIN', 'ROLE_VENDEDOR'])
class KindPersonController {

    def index() {
        render(KindPerson.list() as JSON)
    }
}
