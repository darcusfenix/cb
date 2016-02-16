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
import mx.capitalbus.app.user.Salesman
import mx.capitalbus.app.user.SuperAdmin

class BootStrap {

    def init = { servletContext ->
/*
        Circuit c = new Circuit()
        c.name = "Cuircuito General"
        c.enabled = true
        c.road = "poin(1,0) to poin(1,0)"
        c.creationDate = new Date()
        c.save()

        KindPerson kp = new KindPerson()
        kp.name = "niños"
        kp.save()

        KindPerson kp2 = new KindPerson()
        kp2.name = "adultos"
        kp2.save()

        DaysDuration dd = new DaysDuration()
        dd.days = "lunes a viernes"
        dd.save()

        DaysDuration dd2 = new DaysDuration()
        dd2.days = "sábados, Domingos y días festivos"
        dd2.save(flush : true)

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


        BraceletState bs = new BraceletState()
        bs.name = "generado"
        bs.description = "..."
        bs.save()

        BraceletState bs2 = new BraceletState()
        bs2.name = "activado"
        bs2.description = "..."
        bs2.save()

        BraceletState bs3 = new BraceletState()
        bs3.name = "Abordo"
        bs3.description = "..."
        bs3.save()

        BraceletState bs8 = new BraceletState()
        bs8.name = "bajada"
        bs8.description = "..."
        bs8.save()

        BraceletState bs4 = new BraceletState()
        bs4.name = "Abordo sin escaneo de bajada primer oportunidad"
        bs4.description = "..."
        bs4.save()

        BraceletState bs5 = new BraceletState()
        bs5.name = "Abordo sin escaneo de bajada segunda oportunidad"
        bs5.description = "..."
        bs5.save()

        BraceletState bs6 = new BraceletState()
        bs6.name = "Abordo sin escaneo de bajada tercer oportunidad"
        bs6.description = "..."
        bs6.save()

        BraceletState bs7 = new BraceletState()
        bs7.name = "caducado"
        bs7.description = "..."
        bs7.save()

        Bus b = new Bus()
        b.circuit = c
        b.enabled = true
        b.currentAltitude = "aa"
        b.currentLatitude = "s"
        b.imei = "a123"
        b.model = "sss"
        b.plate = "ds"
        b.year = new Date()
        b.lastUpdatedLocation = new Date()
        b.save()

        CostBracelet cb = new CostBracelet()
        cb.cost = 170.00
        cb.kindPerson = kp2
        cb.daysDuration = dd2
        cb.circuit = c
        cb.save()

        CostBracelet cb2 = new CostBracelet()
        cb2.cost = 80.00
        cb2.kindPerson = kp
        cb2.daysDuration = dd2
        cb2.circuit = c
        cb2.save()

        CostBracelet cb3 = new CostBracelet()
        cb3.cost = 140.00
        cb3.kindPerson = kp2
        cb3.daysDuration = dd
        cb3.circuit = c
        cb3.save()

        CostBracelet cb4 = new CostBracelet()
        cb4.cost = 70.00
        cb4.kindPerson = kp
        cb4.daysDuration = dd
        cb4.circuit = c
        cb4.save()
*/
    }
    def destroy = {
    }
}
