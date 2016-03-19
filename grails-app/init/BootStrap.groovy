import mx.capitalbus.app.bracelet.Bracelet
import mx.capitalbus.app.bracelet.BraceletState
import mx.capitalbus.app.bracelet.CostBracelet
import mx.capitalbus.app.bracelet.DaysDuration
import mx.capitalbus.app.bracelet.KindPerson
import mx.capitalbus.app.bracelet.PersonDuration
import mx.capitalbus.app.circuit.Bus
import mx.capitalbus.app.circuit.Circuit
import mx.capitalbus.app.security.Role
import mx.capitalbus.app.security.RoleGroup
import mx.capitalbus.app.security.RoleGroupRole
import mx.capitalbus.app.security.UserRole
import mx.capitalbus.app.security.UserRoleGroup
import mx.capitalbus.app.user.AdminControlBracelets
import mx.capitalbus.app.user.Salesman
import mx.capitalbus.app.user.SuperAdmin

class BootStrap {

    def init = { servletContext ->
        def results
        def query =  Salesman.where {
                id >= 41
        }
        results = query.order('id', 'asc').list()
        log.error(results)
        results.each{ s ->
            s.password = "v-" + s.username
            s.username = "v-" + s.username
            log.error(s.password + "\n")
            //s.beforeUpdate()
            if(s.validate())
                s.save(flush:true)
            else
                log.error(s.errors)
        }
    }
    def destroy = {
    }
}
