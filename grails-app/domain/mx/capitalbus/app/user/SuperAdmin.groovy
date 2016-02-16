package mx.capitalbus.app.user

import mx.capitalbus.app.security.User

class SuperAdmin extends User{

    String firstName
    String lastName
    Date birthdate
    String telephone
    String gender


    static constraints = {
    }

    static mapping = {
        id generator: 'identity'
        table 'super_admin'
    }
}

