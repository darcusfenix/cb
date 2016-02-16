package mx.capitalbus.app.bracelet

import mx.capitalbus.app.circuit.Bus
import mx.capitalbus.app.circuit.Circuit
import mx.capitalbus.app.user.Salesman

class Bracelet {
    String code
    Date activationDate
    Date deliveryDate
    Date creationDate

    static belongsTo = [salesman: Salesman, braceletState: BraceletState, costBracelet: CostBracelet]

    static constraints = {
      //  currentBus nullable: true
        salesman nullable: true
        deliveryDate nullable: true
        activationDate nullable: true
        code  unique: true
    }

    static mapping = {
        id generator: 'identity'
    }
}
