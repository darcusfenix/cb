package mx.capitalbus.app.bracelet

import mx.capitalbus.app.circuit.Circuit


class CostBracelet {

    float cost

    //static hasMany = [bracelet: Bracelet]
    //static mappedBy = [bracelet: 'costBracelet']

    static belongsTo = [daysDuration : DaysDuration, kindPerson : KindPerson, circuit : Circuit]

    static constraints = {
    }

    static mapping = {
        id generator: 'identity'
    }



}
