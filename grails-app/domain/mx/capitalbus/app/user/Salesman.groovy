package mx.capitalbus.app.user

import mx.capitalbus.app.bracelet.Bracelet
import mx.capitalbus.app.security.User


class Salesman extends User{

    String firstName
    String lastName
    Date birthdate
    String telephone
    String gender

    static hasMany = [bracelet: Bracelet]
    static mappedBy = [bracelet: 'salesman']

    static constraints = {
    }

    static mapping = {
        id generator: 'sequence'
        table 'salesman'
    }
}
