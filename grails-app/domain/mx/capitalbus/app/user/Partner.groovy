package mx.capitalbus.app.user

import mx.capitalbus.app.security.User

class Partner extends User{

    String companyName

    static constraints = {
    }

    static mapping = {
        id generator: 'identity'
        table 'partner'
    }
}
