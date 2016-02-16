package mx.capitalbus.app.circuit

import mx.capitalbus.app.bracelet.CostBracelet

class Circuit {

    String name
    Date creationDate
    String road
    boolean enabled

    static hasMany = [bus: Bus, joinTable: CostBracelet]
    static mappedBy = [bus: 'circuit', joinTable: 'circuit']


    static constraints = {
    }

    static mapping = {
        id generator: 'identity'
    }
}
