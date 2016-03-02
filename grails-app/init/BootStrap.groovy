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

        //todo modificar en producción antes de desplegar ---> YA EN PRODUCCIÓN
        // ALTER TABLE bracelet ADD COLUMN "sold" BOOLEAN DEFAULT FALSE;
        // UPDATE bracelet set sold = FALSE;
        // delete from bracelet_state where id >= 5 and id <=8;
        // ALTER SEQUENCE bracelet_state_id_seq RESTART WITH 5;

        // todo recomiendo hacer esto para más análisis
        // UPDATE bracelet set last_date_scan=null, latitude=0.0, longitude=0.0, number_scans=0;
/*

        def salesManRole = Role.findOrSaveByAuthority('ROLE_SALESMAN').save()

        def sm1 = new Salesman()
        sm1.accountExpired = false
        sm1.accountLocked = false
        sm1.passwordExpired = false
        sm1.password = "v-alejandra-karina"
        sm1.email = "v-alejandra-karina@capitalbus.mx"
        sm1.enabled = true
        sm1.username = "v-alejandra-karina"
        sm1.birthdate = new Date()
        sm1.firstName = "ALEJANDRA KARINA"
        sm1.lastName = "ESQUIVEL VILLEGAS"
        sm1.telephone = "5555555555"
        sm1.gender = "F"
        sm1.save()

        def sm2 = new Salesman()
        sm2.accountExpired = false
        sm2.accountLocked = false
        sm2.passwordExpired = false
        sm2.password = "v-cesar-augusto"
        sm2.email = "v-cesar-augusto@capitalbus.mx"
        sm2.enabled = true
        sm2.username = "v-cesar-augusto"
        sm2.birthdate = new Date()
        sm2.firstName = "CESAR AUGUSTO"
        sm2.lastName = "BARRALES PORCAYO"
        sm2.telephone = "5555555555"
        sm2.gender = "F"
        sm2.save()


        def sm3 = new Salesman()
        sm3.accountExpired = false
        sm3.accountLocked = false
        sm3.passwordExpired = false
        sm3.password = "v-cesar-rodriguez"
        sm3.email = "v-cesar-rodriguez@capitalbus.mx"
        sm3.enabled = true
        sm3.username = "v-cesar-rodriguez"
        sm3.birthdate = new Date()
        sm3.firstName = "CÉSAR"
        sm3.lastName = "RODRÍGUEZ CAZARES"
        sm3.telephone = "5555555555"
        sm3.gender = "F"
        sm3.save()

        def sm4 = new Salesman()
        sm4.accountExpired = false
        sm4.accountLocked = false
        sm4.passwordExpired = false
        sm4.password = "v-daphne-stephany"
        sm4.email = "v-daphne-stephany@capitalbus.mx"
        sm4.enabled = true
        sm4.username = "v-daphne-stephany"
        sm4.birthdate = new Date()
        sm4.firstName = "DAPHNE STEPHANY"
        sm4.lastName = "GARRIDO MARTINEZ"
        sm4.telephone = "5555555555"
        sm4.gender = "F"
        sm4.save()

        def sm5 = new Salesman()
        sm5.accountExpired = false
        sm5.accountLocked = false
        sm5.passwordExpired = false
        sm5.password = "v-maria-gabriela"
        sm5.email = "v-maria-gabriela@capitalbus.mx"
        sm5.enabled = true
        sm5.username = "v-maria-gabriela"
        sm5.birthdate = new Date()
        sm5.firstName = "MARIA GABRIELA"
        sm5.lastName = "VARGAS VALLADOLID"
        sm5.telephone = "5555555555"
        sm5.gender = "F"
        sm5.save()


        def sm6 = new Salesman()
        sm6.accountExpired = false
        sm6.accountLocked = false
        sm6.passwordExpired = false
        sm6.password = "v-guadalupe-garcia"
        sm6.email = "v-guadalupe-garcia@capitalbus.mx"
        sm6.enabled = true
        sm6.username = "v-guadalupe-garcia"
        sm6.birthdate = new Date()
        sm6.firstName = "GUADALUPE"
        sm6.lastName = "GARCÍA CARRIZOSA"
        sm6.telephone = "5555555555"
        sm6.gender = "F"
        sm6.save()

        def sm7 = new Salesman()
        sm7.accountExpired = false
        sm7.accountLocked = false
        sm7.passwordExpired = false
        sm7.password = "v-irma-rosa"
        sm7.email = "v-irma-rosa@capitalbus.mx"
        sm7.enabled = true
        sm7.username = "v-irma-rosa"
        sm7.birthdate = new Date()
        sm7.firstName = "IRMA"
        sm7.lastName = "DE LA ROSA CASTAÑEDA"
        sm7.telephone = "5555555555"
        sm7.gender = "F"
        sm7.save()

        def sm8 = new Salesman()
        sm8.accountExpired = false
        sm8.accountLocked = false
        sm8.passwordExpired = false
        sm8.password = "v-isaac-alberto"
        sm8.email = "v-isaac-alberto@capitalbus.mx"
        sm8.enabled = true
        sm8.username = "v-isaac-alberto"
        sm8.birthdate = new Date()
        sm8.firstName = "ISAAC ALBERTO"
        sm8.lastName = "RODRÍGUEZ RODRIGUEZ"
        sm8.telephone = "5555555555"
        sm8.gender = "F"
        sm8.save()

        def sm9 = new Salesman()
        sm9.accountExpired = false
        sm9.accountLocked = false
        sm9.passwordExpired = false
        sm9.password = "v-carlos-ivan"
        sm9.email = "v-carlos-ivan@capitalbus.mx"
        sm9.enabled = true
        sm9.username = "v-carlos-ivan"
        sm9.birthdate = new Date()
        sm9.firstName = "CARLOS IVAN"
        sm9.lastName = "REYES TORRES"
        sm9.telephone = "5555555555"
        sm9.gender = "F"
        sm9.save()

        def smQ = new Salesman()
        smQ.accountExpired = false
        smQ.accountLocked = false
        smQ.passwordExpired = false
        smQ.password = "v-jordan-joa"
        smQ.email = "v-jordan-joa@capitalbus.mx"
        smQ.enabled = true
        smQ.username = "v-jordan-joa"
        smQ.birthdate = new Date()
        smQ.firstName = "JORDAN JOA"
        smQ.lastName = "FLORES HERNANDEZ"
        smQ.telephone = "5555555555"
        smQ.gender = "F"
        smQ.save()

        def smW = new Salesman()
        smW.accountExpired = false
        smW.accountLocked = false
        smW.passwordExpired = false
        smW.password = "v-luis-enrique"
        smW.email = "v-luis-enrique@capitalbus.mx"
        smW.enabled = true
        smW.username = "v-luis-enrique"
        smW.birthdate = new Date()
        smW.firstName = "LUIS ENRIQUE"
        smW.lastName = "RETIS PEREZ"
        smW.telephone = "5555555555"
        smW.gender = "F"
        smW.save()

        def smE = new Salesman()
        smE.accountExpired = false
        smE.accountLocked = false
        smE.passwordExpired = false
        smE.password = "v-martha-elena"
        smE.email = "v-martha-elena@capitalbus.mx"
        smE.enabled = true
        smE.username = "v-martha-elena"
        smE.birthdate = new Date()
        smE.firstName = "MARTHA ELENA"
        smE.lastName = "POSADAS SANCHEZ"
        smE.telephone = "5555555555"
        smE.gender = "F"
        smE.save()

        def smR = new Salesman()
        smR.accountExpired = false
        smR.accountLocked = false
        smR.passwordExpired = false
        smR.password = "v-ricardo-tenorio"
        smR.email = "v-ricardo-tenorio@capitalbus.mx"
        smR.enabled = true
        smR.username = "v-ricardo-tenorio"
        smR.birthdate = new Date()
        smR.firstName = "RICARDO"
        smR.lastName = "TENORIO HERNANDEZ"
        smR.telephone = "5555555555"
        smR.gender = "F"
        smR.save()

        def smT = new Salesman()
        smT.accountExpired = false
        smT.accountLocked = false
        smT.passwordExpired = false
        smT.password = "v-yesenia-martha"
        smT.email = "v-yesenia-martha@capitalbus.mx"
        smT.enabled = true
        smT.username = "v-yesenia-martha"
        smT.birthdate = new Date()
        smT.firstName = "YESENIA MARTHA"
        smT.lastName = "GUTIERREZ ALTAMIRANO"
        smT.telephone = "5555555555"
        smT.gender = "F"
        smT.save()

        def smY = new Salesman()
        smY.accountExpired = false
        smY.accountLocked = false
        smY.passwordExpired = false
        smY.password = "v-alejandro-vallejo"
        smY.email = "v-alejandro-vallejo@capitalbus.mx"
        smY.enabled = true
        smY.username = "v-alejandro-vallejo"
        smY.birthdate = new Date()
        smY.firstName = "ALEJANDRO"
        smY.lastName = "VALLEJO DAVILA"
        smY.telephone = "5555555555"
        smY.gender = "F"
        smY.save()

        def smU = new Salesman()
        smU.accountExpired = false
        smU.accountLocked = false
        smU.passwordExpired = false
        smU.password = "v-daniela-lara"
        smU.email = "v-daniela-lara@capitalbus.mx"
        smU.enabled = true
        smU.username = "v-daniela-lara"
        smU.birthdate = new Date()
        smU.firstName = "DANIELA"
        smU.lastName = "LARA MADRID"
        smU.telephone = "5555555555"
        smU.gender = "F"
        smU.save()

        def smI = new Salesman()
        smI.accountExpired = false
        smI.accountLocked = false
        smI.passwordExpired = false
        smI.password = "v-denise-vanessa"
        smI.email = "v-denise-vanessa@capitalbus.mx"
        smI.enabled = true
        smI.username = "v-denise-vanessa"
        smI.birthdate = new Date()
        smI.firstName = "DENISE VANESSA"
        smI.lastName = "GOMEZ OLVERA"
        smI.telephone = "5555555555"
        smI.gender = "F"
        smI.save()

        def smO = new Salesman()
        smO.accountExpired = false
        smO.accountLocked = false
        smO.passwordExpired = false
        smO.password = "v-jenifer-montserrat"
        smO.email = "v-jenifer-montserrat@capitalbus.mx"
        smO.enabled = true
        smO.username = "v-jenifer-montserrat"
        smO.birthdate = new Date()
        smO.firstName = "JENIFER MONTSERRAT"
        smO.lastName = "MALVAEZ POMPA"
        smO.telephone = "5555555555"
        smO.gender = "F"
        smO.save()

        def smP = new Salesman()
        smP.accountExpired = false
        smP.accountLocked = false
        smP.passwordExpired = false
        smP.password = "v-sandra-pamela"
        smP.email = "v-sandra-pamela@capitalbus.mx"
        smP.enabled = true
        smP.username = "v-sandra-pamela"
        smP.birthdate = new Date()
        smP.firstName = "SANDRA PAMELA"
        smP.lastName = "DOMINGUEZ ROMERO"
        smP.telephone = "5555555555"
        smP.gender = "F"
        smP.save()

        def smA = new Salesman()
        smA.accountExpired = false
        smA.accountLocked = false
        smA.passwordExpired = false
        smA.password = "v-samuel-rivera"
        smA.email = "v-samuel-rivera@capitalbus.mx"
        smA.enabled = true
        smA.username = "v-samuel-rivera"
        smA.birthdate = new Date()
        smA.firstName = "SAMUEL"
        smA.lastName = "RIVERA GARCIA"
        smA.telephone = "5555555555"
        smA.gender = "F"
        smA.save()

        def smS = new Salesman()
        smS.accountExpired = false
        smS.accountLocked = false
        smS.passwordExpired = false
        smS.password = "v-yahaira-montoya"
        smS.email = "v-yahaira-montoya@capitalbus.mx"
        smS.enabled = true
        smS.username = "v-yahaira-montoya"
        smS.birthdate = new Date()
        smS.firstName = "YAHAIRA"
        smS.lastName = "MONTOYA PEREZ"
        smS.telephone = "5555555555"
        smS.gender = "F"
        smS.save()

        def smD = new Salesman()
        smD.accountExpired = false
        smD.accountLocked = false
        smD.passwordExpired = false
        smD.password = "v-maria-fernanda"
        smD.email = "v-maria-fernanda@capitalbus.mx"
        smD.enabled = true
        smD.username = "v-maria-fernanda"
        smD.birthdate = new Date()
        smD.firstName = "MARIA FERNANDA"
        smD.lastName = "GOMEZ RAMIREZ"
        smD.telephone = "5555555555"
        smD.gender = "F"
        smD.save()

        def smF = new Salesman()
        smF.accountExpired = false
        smF.accountLocked = false
        smF.passwordExpired = false
        smF.password = "v-jaqueline-ehunice"
        smF.email = "v-jaqueline-ehunice@capitalbus.mx"
        smF.enabled = true
        smF.username = "v-jaqueline-ehunice"
        smF.birthdate = new Date()
        smF.firstName = "JAQUELINE EHUNICE"
        smF.lastName = "RODRIGUEZ DE LA ROSA"
        smF.telephone = "5555555555"
        smF.gender = "F"
        smF.save()

        def smG = new Salesman()
        smG.accountExpired = false
        smG.accountLocked = false
        smG.passwordExpired = false
        smG.password = "v-marina-marquez"
        smG.email = "v-marina-marquez@capitalbus.mx"
        smG.enabled = true
        smG.username = "v-marina-marquez"
        smG.birthdate = new Date()
        smG.firstName = "MARINA"
        smG.lastName = "MARQUEZ MENDIZABAL"
        smG.telephone = "5555555555"
        smG.gender = "F"
        smG.save()

        def smH = new Salesman()
        smH.accountExpired = false
        smH.accountLocked = false
        smH.passwordExpired = false
        smH.password = "v-perla-jimenez"
        smH.email = "v-perla-jimenez@capitalbus.mx"
        smH.enabled = true
        smH.username = "v-perla-jimenez"
        smH.birthdate = new Date()
        smH.firstName = "PERLA"
        smH.lastName = "JIMENEZ"
        smH.telephone = "5555555555"
        smH.gender = "F"
        smH.save()

        def smJ = new Salesman()
        smJ.accountExpired = false
        smJ.accountLocked = false
        smJ.passwordExpired = false
        smJ.password = "v-maria-teresa"
        smJ.email = "v-maria-teresa@capitalbus.mx"
        smJ.enabled = true
        smJ.username = "v-maria-teresa"
        smJ.birthdate = new Date()
        smJ.firstName = "MARIA"
        smJ.lastName = "TERESA GUTIERREZ"
        smJ.telephone = "5555555555"
        smJ.gender = "F"
        smJ.save()

        def smK = new Salesman()
        smK.accountExpired = false
        smK.accountLocked = false
        smK.passwordExpired = false
        smK.password = "v-fabian-ariel"
        smK.email = "v-fabian-ariel@capitalbus.mx"
        smK.enabled = true
        smK.username = "v-fabian-ariel"
        smK.birthdate = new Date()
        smK.firstName = "FABIAN ARIEL"
        smK.lastName = "LOPEZ GONZALEZ"
        smK.telephone = "5555555555"
        smK.gender = "F"
        smK.save()

        def smL = new Salesman()
        smL.accountExpired = false
        smL.accountLocked = false
        smL.passwordExpired = false
        smL.password = "v-edmundo-gutierrez"
        smL.email = "v-edmundo-gutierrez@capitalbus.mx"
        smL.enabled = true
        smL.username = "v-edmundo-gutierrez"
        smL.birthdate = new Date()
        smL.firstName = "EDMUNDO"
        smL.lastName = "GUTIERREZ AREVALO"
        smL.telephone = "5555555555"
        smL.gender = "F"
        smL.save()

        UserRole.create sm1, salesManRole
        UserRole.create sm2, salesManRole
        UserRole.create sm3, salesManRole
        UserRole.create sm4, salesManRole
        UserRole.create sm5, salesManRole
        UserRole.create sm6, salesManRole
        UserRole.create sm7, salesManRole
        UserRole.create sm8, salesManRole
        UserRole.create sm9, salesManRole
        UserRole.create smQ, salesManRole
        UserRole.create smW, salesManRole
        UserRole.create smE, salesManRole
        UserRole.create smR, salesManRole
        UserRole.create smT, salesManRole
        UserRole.create smY, salesManRole
        UserRole.create smU, salesManRole
        UserRole.create smI, salesManRole
        UserRole.create smO, salesManRole
        UserRole.create smP, salesManRole
        UserRole.create smA, salesManRole
        UserRole.create smS, salesManRole
        UserRole.create smD, salesManRole
        UserRole.create smF, salesManRole
        UserRole.create smG, salesManRole
        UserRole.create smH, salesManRole
        UserRole.create smJ, salesManRole
        UserRole.create smK, salesManRole
        UserRole.create smL, salesManRole


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

        UserRole.create sa, r


        BraceletState bs = new BraceletState()
        bs.description = "..."
        bs.name = "bloqueado"
        if (bs.validate()) {
            bs.save(flush: true)
        }
*/
    }
    def destroy = {
    }
}
