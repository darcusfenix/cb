package mx.capitalbus.app.bracelet

class KindPerson {

    String name

    static hasMany = [joinTable: CostBracelet]
    static mappedBy = [joinTable: 'kindPerson']

    static constraints = {
    }

    static mapping = {
        id generator: 'identity'
    }
}
