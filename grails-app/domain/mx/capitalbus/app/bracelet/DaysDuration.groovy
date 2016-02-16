package mx.capitalbus.app.bracelet

class DaysDuration {

    String days

    static hasMany = [joinTable: CostBracelet]
    static mappedBy = [joinTable: 'daysDuration']

    static constraints = {
    }

    static mapping = {
        id generator: 'identity'
    }
}
