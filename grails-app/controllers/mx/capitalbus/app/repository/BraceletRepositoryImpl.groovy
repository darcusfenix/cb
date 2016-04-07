package mx.capitalbus.app.repository

import grails.converters.JSON
import groovy.time.TimeCategory
import groovy.time.TimeDuration
import mx.capitalbus.app.bracelet.Bracelet
import mx.capitalbus.app.bracelet.BraceletState
import mx.capitalbus.app.bracelet.CostBracelet
import mx.capitalbus.app.user.Salesman
import org.apache.commons.logging.Log

import java.text.ParseException
import java.text.SimpleDateFormat

/**
 * Created by becm on 2/19/16.
 */
class BraceletRepositoryImpl implements BraceletRepository {

    def springSecurityService

    @Override
    def getBySalesmanOrderAndGroupByCostBracelet(Salesman s) {
        def b = Bracelet.createCriteria()
        def results = b.list {
            projections {
                groupProperty('costBracelet')
                count("costBracelet")
            }
            and {
                eq("salesman", s)
            }
            order("costBracelet", "asc")
        }
        results
    }

    @Override
    def getBySalesmanOrderAndGroupBySold(Salesman s) {
        def b = Bracelet.createCriteria()
        def results = b.list {
            projections {
                groupProperty('costBracelet')
                count("sold")
            }
            and {
                eq("salesman", s)
                eq("sold", true)
            }
            order("costBracelet", "asc")
        }
        results
    }

    @Override
    def getBySalesmanOrderAndGroupByDeliveryDate(Salesman s, String dateText) {

        def b = Bracelet.createCriteria()
        def results = b.list {
            projections {
                groupProperty('costBracelet')
                groupProperty('deliveryDate')
                count("costBracelet")
            }
            and {
                eq("salesman", s)
            }
            order("deliveryDate", "asc")
        }
        results
    }

    @Override
    def getBySalesmanNotSold(Salesman s, CostBracelet cb, String sd, String ed) {
        Date start, end
        TimeZone.setDefault(TimeZone.getTimeZone("GMT-6"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");



        Calendar now = Calendar.getInstance();
        if (sd == null && ed == null) {
            now.set(Calendar.HOUR, 0);
            now.set(Calendar.MINUTE, 0);
            now.set(Calendar.SECOND, 0);
            now.set(Calendar.HOUR_OF_DAY, 0);
            start = now.getTime()
            end = new Date()

        } else {

            now.setTime(sdf.parse(ed));
            now.set(Calendar.HOUR, 23);
            now.set(Calendar.MINUTE, 59);
            now.set(Calendar.SECOND, 59);
            now.set(Calendar.HOUR_OF_DAY, 23);

            end = now.getTime()

            now.setTime(sdf.parse(sd));
            now.set(Calendar.HOUR, 0);
            now.set(Calendar.MINUTE, 0);
            now.set(Calendar.SECOND, 0);
            now.set(Calendar.HOUR_OF_DAY, 0);
            start = now.getTime()

        }



        def results
        def query = Bracelet.where {
            (salesman == s) && (costBracelet == cb) && (sold == false) && (deliveryDate >= start && deliveryDate <= end)
        }
        results = query.order('id', 'asc').list()

        results
    }

    @Override
    def getHistoryBySalesmanYesSold(String sd, String ed, long id, String ss) {
        Date start, end
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");

        Calendar now = Calendar.getInstance();
        if (sd == null && ed == null) {
            now.set(Calendar.HOUR, 0);
            now.set(Calendar.MINUTE, 0);
            now.set(Calendar.SECOND, 0);
            now.set(Calendar.HOUR_OF_DAY, 0);
            end = new Date()
            now.set(Calendar.DAY_OF_MONTH, -29);
            start = now.getTime()
        } else {
            try
            {
                now.setTime(sdf.parse(ed));
                now.set(Calendar.HOUR, 23);
                now.set(Calendar.MINUTE, 59);
                now.set(Calendar.SECOND, 59);
                now.set(Calendar.HOUR_OF_DAY, 23);

                end = now.getTime()

                now.setTime(sdf.parse(sd));
                now.set(Calendar.HOUR, 0);
                now.set(Calendar.MINUTE, 0);
                now.set(Calendar.SECOND, 0);
                now.set(Calendar.HOUR_OF_DAY, 0);
                start = now.getTime()

            }catch(ParseException e) {

            }
        }

        def results
        def query = Bracelet.createCriteria()
        results = query.list {
            projections {
                groupProperty('soldDate')
                count("soldDate")
                if (ss) {
                    groupProperty('costBracelet')
                    count("costBracelet")
                }
            }
            and {
                eq("salesman", Salesman.findById(id))
                eq("sold", true)
                if (ss) {
                    def a
                    try
                    {
                        a = sdf.parse(ss)
                    }catch(ParseException pe) {
                        a = new Date()
                    }

                    now.setTime(a);
                    now.add(Calendar.SECOND, 1);
                    def e = now.getTime()
                    println(" -- " + a + " -- " + e)
                    between("soldDate", a, e)
                } else {
                    between("soldDate", start, end)
                }
            }
            order("soldDate", "desc")
        }
        results
    }

/*
* LISTADO DE RESPUESTAS
*
* 0 -> el brazalete no existe en la base de datos. ALARMA  #
* 1 -> existe brazalete, pero no tiene fecha de entrega a un vendedor. ALARMA #
* 2 -> el tiempo de validez excedió más de 48 horas. ALARMA #
* 3 -> el tiempo de validez excedió de 24 horas a 48 horas. ALARMA #
* 4 -> existe brazalete, pero el vendedor no está autorizado a vendeder. ALARMA
* 5 -> si el escaneo tiene el estado 3 (ABORDO), Cambiar estado a BLOQUEADA.
* 6 -> todo bien, sólo actuaizar fecha de venta
* 7 -> el brazalete ha sido bloqueado por no scanear en bajada
* 8 -> el brazalete ha pasado a estado de bajada
* 9 -> el brazalete se escaneó otra vez que sí pasó por bajada
* 10 -> el brazalete sin fecha de activación
*
* */
    @Override
    def validarSubida(String code, long bus, float lon, float lat ) {
        def r

        r = Bracelet.findByCode(code, [fetch: [salesman: 'eager']])

        if (!r)  // no existe
            return 0

        if (r.deliveryDate == null) // existe, pero no fue entregado a un vendedor
            return 1

        if (r.activationDate) { // ya activado, pero...

            def timeStart = r.activationDate
            def timeStop = new Date()
            TimeDuration duration = TimeCategory.minus(timeStop, timeStart)

            if (duration.days >= 1 && duration.days <= 2) // validez excedió de 1 a 2 días
                return 3
            if (duration.days > 2) // validez tiene más de 2 días
                return 2
        }

        if (!r.salesman.enabled) // el vendedor entregó un brazalete no autorizado
            return 4


        if (r.braceletState.id == 3l) {
            r.braceletState = BraceletState.findById(5)
            if (r.validate()) {
                r.save(flush: true)
            }
            return 5
        }
        if (r.braceletState.id == 4l) {
            r.braceletState = BraceletState.findById(3)
            if (r.validate()) {
                r.save(flush: true)
            }
            return 9
        }
        if (r.braceletState.id == 2l && r.activationDate == null) {
            r.braceletState = BraceletState.findById(3)
            r.activationDate = new Date()
            if (r.validate()) {
                r.save(flush: true)
            }
            return 6
        }

        if (r.braceletState.id == 5l) {
            return 7
        }
        return 14
    }

    @Override
    def validarBajada(String code, long bus, float lon, float lat ) {
        def r

        r = Bracelet.findByCode(code, [fetch: [salesman: 'eager']])

        if (!r)  // no existe
            return 0

        if (r.deliveryDate == null) // existe, pero no fue entregado a un vendedor
            return 1

        if (r.activationDate) { // ya activado, pero...

            def timeStart = r.activationDate
            def timeStop = new Date()
            TimeDuration duration = TimeCategory.minus(timeStop, timeStart)

            if (duration.days >= 1 && duration.days <= 2) // validez excedió de 1 a 2 días
                return 3
            if (duration.days > 2) // validez tiene más de 2 días
                return 2
        }

        if (!r.salesman.enabled) // el vendedor entregó un brazalete no autorizado
            return 4

        if (r.braceletState.id == 5l) {
            return 7
        }

        if (r.braceletState.id == 2l && r.activationDate != null) {
            return 10
        }

        if (r.braceletState.id == 3l && r.activationDate) {
            def timeStart = r.activationDate
            def timeStop = new Date()
            TimeDuration duration = TimeCategory.minus(timeStop, timeStart)
            if (duration.days <= 1){
                r.braceletState = BraceletState.findById(4)
                return 8
            }
        }

        if (r.braceletState.id == 4l) {
            return 13
        }

        return 14
    }

    @Override
    def getBySalesmanAndDate(Salesman s, String sd, boolean f) {
        Date start, end

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");

        Calendar now = Calendar.getInstance();
        now.setTime(sdf.parse(sd));
        start = now.getTime()

        now.add(Calendar.SECOND, 1);
        end = now.getTime()



        def results
        def query = Bracelet.where {
            salesman == s
            if (f){
                (soldDate >= start && soldDate <= end)
            }else{
                (deliveryDate >= start && deliveryDate <= end)
            }
        }
        results = query.order('id', 'asc').list()
        results
    }
    @Override
    def getBySalesmanAndDate(Salesman s, Date date) {

        def results

        def query = Bracelet.where {
            salesman == s && deliveryDate == date
        }

        results = query.order('id', 'asc').list()
        results
    }

    @Override
    def getBySalesmanYetNotSold(Salesman s, String sd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");

        Calendar now = Calendar.getInstance();
        now.setTime(sdf.parse(sd));


        def results
        def query = Bracelet.where {
            salesman == s && deliveryDate < now.getTime() && sold == false
        }
        results = query.order('id', 'asc').list()
        results
    }

    @Override
    def getCostBraceletsBySalesman(Salesman s, String sd, boolean f) {
        Date start, end

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");

        Calendar now = Calendar.getInstance();
        now.setTime(sdf.parse(sd));
        start = now.getTime()

        now.add(Calendar.SECOND, 1);
        end = now.getTime()

        def results
        def query = Bracelet.createCriteria()
        results = query.list {
            projections {
                    groupProperty('costBracelet')
                    count("costBracelet")
            }
            and {
                eq("salesman", s)
                if (f){
                    eq("sold",f)
                    between("soldDate", start, end)
                }else{
                    between("deliveryDate", start, end)
                }
            }
            order("costBracelet", "asc")
        }
        results
    }

    def getHistory(String sd, String ed, Integer op){
        TimeZone.setDefault(TimeZone.getTimeZone("GMT-5"))
        Calendar now = Calendar.getInstance()
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a")
        def results
        Date start, end

        if (sd == null || ed == null || (sd.equals(ed))) {
            end = now.getTime()
            start = now.getTime()
            start.clearTime();
        } else {
            start = sdf.parse(sd)
            end = sdf.parse(ed)
        }
        def query = Bracelet.createCriteria()
        results = query.list {
            projections {
                if (op == 1) {
                    groupProperty('deliveryDate')
                    count("deliveryDate")
                }
                if (op == 2) {
                    groupProperty('assignmentDate')
                    count("assignmentDate")
                }
            }
            and {
                if (op == 1) {
                    between("deliveryDate", start, end)
                }
                if (op == 2) {
                    between("assignmentDate", start, end)
                }
            }
            if (op == 1) {
                order("deliveryDate", "desc")
            }
            if (op == 2) {
                order("assignmentDate", "desc")
            }
        }
        def map = [:]

        results.eachWithIndex { r, index ->
            def s = [0, 1,2,3,4]
            def a = Bracelet.createCriteria().list {
                switch (op) {
                    case 1:
                        projections {
                            groupProperty('costBracelet')
                            count("costBracelet")
                        }
                        and {
                            eq('deliveryDate', r[0])
                        }
                        break;
                    case 2:
                        projections {
                            groupProperty('costBracelet')
                            count("costBracelet")
                        }
                        and {
                            eq('assignmentDate', r[0])
                        }
                        break;
                }
            }
            def b
            def c
            switch (op){
                case 1:
                    b = Bracelet.findAllByDeliveryDate(r[0])
                    c = Bracelet.countByDeliveryDate(r[0])
                    break;
                case 2:
                    b = Bracelet.findAllByAssignmentDate(r[0])
                    c = Bracelet.countByAssignmentDate(r[0])
                    break;
            }
            s[0] = r[0]
            s[1] = a
            s[2] = b[0].salesman
            s[3] = b[0].id
            s[4] = b[b.size()-1].id
            s[5] = c
            println(s)
            map.put(index,s)
        }
        map
    }

}
