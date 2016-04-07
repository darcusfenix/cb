package marshallers

import grails.converters.JSON
import mx.capitalbus.app.bracelet.Bracelet

class BraceletMarshaller {
    void register() {
        JSON.registerObjectMarshaller(Bracelet) {
            return [
                    id: it.id,
                    code : it.code,
                    activationDate : it.activationDate,
                    deliveryDate : it.assignmentDate,
                    creationDate : it.creationDate,
                    soldDate : it.soldDate,
                    sold : it.sold,
                    salesman : it.salesman.firstName + ' ' + it.salesman.lastName,
                    braceletState : it.braceletState.name,
                    costBracelet : it.costBracelet.id
            ]
        }
    }
}