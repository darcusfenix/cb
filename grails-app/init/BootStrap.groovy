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

        //todo modificar en producción antes de desplegar
        // ALTER TABLE bracelet ADD COLUMN "sold" BOOLEAN DEFAULT FALSE;
        // UPDATE bracelet set sold = FALSE;
        // delete from bracelet_state where id >= 5 and id <=8;
        // ALTER SEQUENCE bracelet_state_id_seq RESTART WITH 5;

        // todo recomiendo hacer esto para más análisis
        // UPDATE bracelet set last_date_scan=null, latitude=0.0, longitude=0.0, number_scans=0;
/*
        def salesManRole = Role.findOrSaveByAuthority('ROLE_SALESMAN').save()

        def sm = new Salesman()
        sm.accountExpired = false
        sm.accountLocked = false
        sm.passwordExpired = false
        sm.password = "pepe"
        sm.email = "pepe@capitalbus.mx"
        sm.enabled = true
        sm.username = "pepe"
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

        def r = Role.findOrSaveByAuthority("ROLE_ADMIN_CONTROL_BRACELET")


        def sa = new AdminControlBracelets()
        sa.accountExpired = false
        sa.accountLocked = false
        sa.passwordExpired = false
        sa.password = "control-brazaletes"
        sa.email = "control-brazaletes@capitalbus.mx"
        sa.enabled = true
        sa.username = "control-brazaletes"
        sa.save()

        UserRole.create sa,r


        BraceletState bs = new BraceletState()
        bs.description = "..."
        bs.name = "bloqueado"
        if(bs.validate()){
            bs.save(flush: true)
        }
*/
    }
    def destroy = {
    }
}
