package mx.capitalbus.app.bracelet

class BraceletState {

    String name
    String description

    //static hasMany = [bracelet: Bracelet]
    //static mappedBy = [bracelet: 'braceletState']

    static constraints = {
    }

    static mapping = {
        id generator: 'identity'
    }
}
