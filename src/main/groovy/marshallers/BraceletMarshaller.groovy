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
                    assignmentDate : it.assignmentDate,
                    creationDate : it.creationDate,
                    soldDate : it.soldDate,
                    sold : it.sold,
                    salesman : it.salesman != null ? (it.salesman != null ? it.salesman.firstName : '') + ' ' + (it.salesman != null ? it.salesman.lastName : '') : null,
                    braceletState : it.braceletState.name,
                    costBracelet : it.costBracelet.id
            ]
        }
    }
}