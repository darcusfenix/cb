package mx.capitalbus.app.circuit

class Bus {

    String plate
    String imei
    String model
    Date year
    Date lastUpdatedLocation
    String currentLatitude
    String currentAltitude
    boolean enabled

    //Circuit circuit
    static belongsTo = [circuit: Circuit]

    static constraints = {
    }

    static mapping = {
        id generator: 'identity'
        circuit nullable: true
    }
}
