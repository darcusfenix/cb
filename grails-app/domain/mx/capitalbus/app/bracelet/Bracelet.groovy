package mx.capitalbus.app.bracelet

import mx.capitalbus.app.circuit.Bus
import mx.capitalbus.app.circuit.Circuit
import mx.capitalbus.app.user.Salesman

class Bracelet {
    String code
    Date activationDate
    Date deliveryDate
    Date assignmentDate
    Date creationDate
    Date soldDate
    boolean sold = false

    static belongsTo = [salesman: Salesman, braceletState: BraceletState, costBracelet: CostBracelet]

    static constraints = {
        salesman nullable: true
        deliveryDate nullable: true
        activationDate nullable: true
        assignmentDate nullable: true
        soldDate nullable: true
        code  unique: true
    }

    static mapping = {
        id generator: 'identity'
        salesman fetch: 'join'
        braceletState fetch: 'join'
        costBracelet fetch: 'join'
        sold defaultValue: false
        assignmentDate defaultValue: null
    }
}
