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
// ALTER TABLE bracelet ADD COLUMN "sold" BOOLEAN DEFAULT FALSE;
        // UPDATE bracelet set sold = FALSE;
/*

        def superAdminRole = new Role('ROLE_SUPER_ADMIN').save()

*/
        /*

        def salesManRole = Role.findOrSaveByAuthority('ROLE_SALESMAN').save()

        def sm = new Salesman()
        sm.accountExpired = false
        sm.accountLocked = false
        sm.passwordExpired = false
        sm.password = "pepetillo"
        sm.email = "pepe@capitalbus.mx"
        sm.enabled = true
        sm.username = "pepetillo"
        sm.birthdate = new Date()
        sm.firstName = "Miguel"
        sm.lastName = "Angel"
        sm.telephone = "5530271655"
        sm.gender = "M"
        sm.save()

        def sm2 = new Salesman()
        sm2.accountExpired = false
        sm2.accountLocked = false
        sm2.passwordExpired = false
        sm2.password = "cuadrado"
        sm2.email = "cuadrado@capitalbus.mx"
        sm2.enabled = true
        sm2.username = "cuadrado"
        sm2.birthdate = new Date()
        sm2.firstName = "Cuadrado"
        sm2.lastName = "al cuadrado"
        sm2.telephone = "5530271655"
        sm2.gender = "M"
        sm2.save()

        UserRole.create sm,salesManRole
        UserRole.create sm2,salesManRole
        */
/*
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
        //def r = Role.findOrSaveByAuthority("ROLE_ADMIN_CONTROL_BRACELET")
      // def u = AdminControlBracelets.findByUsername("control-brazaletes")

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

        //def sa = AdminControlBracelets.findByUsername("control-brazaletes")
      //  UserRole.create sa,r


    }
    def destroy = {
    }
}
