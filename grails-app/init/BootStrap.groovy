import mx.capitalbus.app.bracelet.Bracelet
import mx.capitalbus.app.bracelet.BraceletState
import mx.capitalbus.app.bracelet.CostBracelet
import mx.capitalbus.app.bracelet.DaysDuration
import mx.capitalbus.app.bracelet.KindPerson
import mx.capitalbus.app.bracelet.PersonDuration
import mx.capitalbus.app.circuit.Bus
import mx.capitalbus.app.circuit.Circuit
import mx.capitalbus.app.security.Role
import mx.capitalbus.app.security.UserRole
import mx.capitalbus.app.user.AdminControlBracelets
import mx.capitalbus.app.user.Salesman
import mx.capitalbus.app.user.SuperAdmin

class BootStrap {

    def init = { servletContext ->

/*
        def superAdminRole = new Role('ROLE_SUPER_ADMIN').save()
        def salesManRole = new Role('ROLE_SALESMAN').save()

        def sm = new Salesman()
        sm.accountExpired = false
        sm.accountLocked = false
        sm.passwordExpired = false
        sm.password = "vendedor"
        sm.email = "vendedor-1@capitalbus.mx"
        sm.enabled = true
        sm.username = "halo"
        sm.birthdate = new Date()
        sm.firstName = "JUAN"
        sm.lastName = "CRISÓSTOMO"
        sm.telephone = "5530271655"
        sm.gender = "M"
        sm.save()

        def sa = new SuperAdmin()
        sa.accountExpired = false
        sa.accountLocked = false
        sa.passwordExpired = false
        sa.password = "capitalbus"
        sa.email = "super-admin@capitalbus.mx"
        sa.enabled = true
        sa.username = "admin"
        sa.birthdate = new Date()
        sa.firstName = "Benito"
        sa.lastName = "Mendoza"
        sa.telephone = "5530271655"
        sa.gender = "M"
        sa.save()


        UserRole.create sm,salesManRole
        UserRole.create sa,superAdminRole
*/
        def r = Role.findOrSaveByAuthority("ROLE_ADMIN_CONTROL_BRACELET")
        def u = AdminControlBracelets.findByUsername("control-brazaletes")
        /*
        def sa = new AdminControlBracelets()
        sa.accountExpired = false
        sa.accountLocked = false
        sa.passwordExpired = false
        sa.password = "control-brazaletes"
        sa.email = "control-brazaletes@capitalbus.mx"
        sa.enabled = true
        sa.username = "control-brazaletes"
        sa.save()
        */
        UserRole.create u,r
    }
    def destroy = {
    }
}
