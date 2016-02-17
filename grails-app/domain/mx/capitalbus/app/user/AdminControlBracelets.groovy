package mx.capitalbus.app.user

import mx.capitalbus.app.security.User

class AdminControlBracelets extends User{



    static constraints = {
    }

    static mapping = {
        id generator: 'identity'
        table 'admin_control_bracelets'
    }
}

