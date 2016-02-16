package mx.capitalbus.app.bracelet

import mx.capitalbus.app.circuit.Circuit
import org.apache.commons.lang.builder.HashCodeBuilder

class PersonDuration implements Serializable{



    //Circuit circuit

    static belongsTo = [kindPerson: KindPerson, daysDuration: DaysDuration]
/*
    static hasMany = [costBracelet: CostBracelet]
    static mappedBy = [costBracelet: 'personDuration']

*/

    PersonDuration(KindPerson u, DaysDuration r) {
        this()
        kindPerson = u
        daysDuration = r
    }
/*

    @Override
    boolean equals(other) {
        if (!(other instanceof PersonDuration)) {
            return false
        }
        other.kindPerson?.id == kindPerson?.id &&
                other.daysDuration?.id == daysDuration?.id
    }

    @Override
    int hashCode() {
        def builder = new HashCodeBuilder()
        if (kindPerson) builder.append(kindPerson.id)
        if (daysDuration) builder.append(daysDuration.id)
        builder.toHashCode()
    }
    */

    static constraints = {
    }

    static mapping = {
        id composite: ['kindPerson', 'daysDuration']
        version false
    }

    static PersonDuration create(KindPerson kindPerson, DaysDuration daysDuration, Circuit circuit,  boolean flush = false) {
        def instance = new PersonDuration(kindPerson: kindPerson, daysDuration: daysDuration, circuit: circuit)
        instance.save(flush: flush, insert: true)
        instance
    }
    static mapWith = "none"
}
