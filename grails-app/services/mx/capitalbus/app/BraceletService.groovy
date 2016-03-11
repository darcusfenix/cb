package mx.capitalbus

import grails.converters.JSON
import grails.transaction.Transactional
import groovy.json.JsonSlurper
import mx.capitalbus.app.bracelet.Bracelet
import mx.capitalbus.app.bracelet.BraceletState
import mx.capitalbus.app.bracelet.CostBracelet
import mx.capitalbus.app.user.Salesman

import java.text.SimpleDateFormat

@Transactional
class BraceletService {

    def sessionFactory
    def grailsApplication
    def messageSource

    def generatingBracelets(Map objeto) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT-6"));
        def map = "ID,CODIGO,TIPO,FECHA_CREACION\n";
        //todo cuidar las secuencias con el id
        def bs = BraceletState.findById(1) // estado de brazalete como generado
        def dateNow = new Date()
        log.error(dateNow)

        objeto.each { w ->
            if (w.value > 0) {
                def sp = getLastAutoIncrementBracelet();

                def cc = CostBracelet.findById(w.key)
                for (int i = sp; i < (sp + w.value); i++) {
                    int idCod = i * 3;
                    def codeEncrypted = encodeId(idCod)
                    Bracelet bracelet = new Bracelet()
                    bracelet.code = codeEncrypted
                    bracelet.braceletState = bs
                    bracelet.costBracelet = cc
                    bracelet.creationDate = dateNow
                    if (bracelet.validate()) {
                        bracelet.save(flush: true)
                        if (bracelet.id != null) {
                            map += bracelet.id + "," + bracelet.code.toLowerCase().trim() + "," + bracelet.costBracelet.id + "," + bracelet.creationDate + "\n"
                        }
                    } else {
                        log.error(bracelet.errors)
                        return "error";
                    }
                }
            }
        }
        map
    }

    private String encodeId(int id) {
        id.encodeAsMD5().encodeAsSHA1().substring(0, 10)
    }

    private String getDatabaseSchema() {
        def dsUrl = grailsApplication.getProperties()
        String dbName = dsUrl.get('flatConfig').get('dataSource.url')
        dbName.substring(dbName.lastIndexOf("/") + 1)
    }

    private Integer getLastAutoIncrementBracelet() {
        //todo cuidar los cambios de nombre aquí mencionados y uso de secuencias en postgresql
        def session = sessionFactory.currentSession
        //def String query = 'SELECT AUTO_INCREMENT FROM information_schema.tables ' +
        //  'WHERE table_name=\'bracelet\' AND table_schema=\'' + getDatabaseSchema() + '\';'
        def query = "select nextval('bracelet_id_seq')"
        def sqlQuery = session.createSQLQuery(query)
        (Integer) sqlQuery.uniqueResult() ?: 0
    }

    def String getStringOfCSV(String date) {
        String mapCVS = null;
      //  TimeZone.setDefault(TimeZone.getTimeZone("GMT-6"));
        SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd hh:mm a");

        def d = dateParser.parse(date)

        if (d != null) {
            mapCVS = "ID,CODIGO,TIPO,FECHA_CREACION\n"
            Calendar calender = Calendar.getInstance();
            calender.setTimeInMillis(d.getTime());
            calender.add(Calendar.SECOND, 20);
            Date changeDate = calender.getTime();

            def bb = Bracelet.createCriteria()
            def results = bb.list {
                between("creationDate", d, changeDate)
            }

            results.each { b ->
                mapCVS += b.id + "," + b.code.toLowerCase().trim() + "," + b.costBracelet.id + "," + b.creationDate + "\n"
            }

        }
        mapCVS
    }

    def updateBraceletsWithSalesman(String textJson, Integer idSalesman) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+/-6:00"));
        def s = Salesman.findById(idSalesman)
        def mapMessage = [:]

        if (s != null) {
            def bs = BraceletState.findById(2) // estado de brazalete como activado
            def date = new Date()
            def jsonSlurper = new JsonSlurper()
            def object = jsonSlurper.parseText(textJson)

            for (String p : object) {
                def j = JSON.parse(p)
                Integer i = j.idCost
                long sr = j.startRange ? j.startRange : 0
                long er = j.endsRange ? j.endsRange : 0

                def serie = CostBracelet.findById(i)
                def query = Bracelet.where {
                    (costBracelet == serie && salesman == null && deliveryDate == null && (id in sr..er))
                }
                def braceletsList = query.list()


                if (braceletsList.size() > 0 && ((er - sr + 1) == braceletsList.size())) {
                    braceletsList.each { b ->
                        b.deliveryDate = date
                        b.braceletState = bs
                        b.salesman = s
                        if (b.validate())
                            b.save(flush: true)
                        else
                            log.error(b.errors)
                    }
                    mapMessage.put(i, ["idCost": i, "status": 1, "mensaje": "Se asignó el siguiente rango correctamente de:  <b>" + sr + "</b> hasta:  <b>" + er + "</b>"])
                } else {
                    mapMessage.put(i, ["idCost": i, "status": 0, "mensaje": "No fue posible asignar este rango de brazaletes de:  <b>" + sr + "</b> hasta:  <b>" + er + "</b>"])
                }

            }
        }
        mapMessage
    }

    def validarSubida(Bracelet r) {

        if (r.braceletState.id == 3l) {
            r.braceletState = BraceletState.findById(5)
            if (r.validate()) {
                r.save(flush: true)
            }
            return 5
        }
        if (r.braceletState.id == 4l) { // propio de subida
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
    }

    def getResponse(Integer res) {
        def r
        switch (res) {
            /*
            case -1:
                r = ["estado": "error", "codigo": res, "mensaje": "desconocido"]
                break
                }*/
            case 0:
                r = ["estado": "error", "codigo": res, "mensaje": "el código no existe"]
                break
            case 1:
                r = ["estado": "error", "codigo": res, "mensaje": "el brazalete no ha sido entregado a un vendedor"]
                break
            case 2:
                r = ["estado": "error", "codigo": res, "mensaje": "el brazalete ha exedido con más de 48 horas de validez"]
                break
            case 3:
                r = ["estado": "error", "codigo": res, "mensaje": "el tiempo de validez excedió de 24 horas a 48 horas"]
                break
            case 4:
                r = ["estado": "error", "codigo": res, "mensaje": "el brazalete fue entregado sin autorización"]
                break
            case 5:
                r = ["estado": "error", "codigo": res, "mensaje": "el brazalete no fue scaneado al bajar, se bloquea"]
                break
            case 6:
                r = ["estado": "success", "codigo": res, "mensaje": "el brazalete se cambió al estado: abordo"]
                break
            case 7:
                r = ["estado": "error", "codigo": res, "mensaje": "el brazalete está bloqueado, no se scaneo anteriormente en bajada"]
                break
            case 8:
                r = ["estado": "success", "codigo": res, "mensaje": "el brazalete pasó a estado de bajada"]
                break
            case 9:
                r = ["estado": "success", "codigo": res, "mensaje": "el brazalete sí pasó por escanner de bajada y subió al autobús"]
                break
            case 10:
                r = ["estado": "error", "codigo": res, "mensaje": "el brazalete no fue escaneado en subida"]
                break
            case 11:
                r = ["estado": "success", "codigo": res, "mensaje": "el brazalete sí pasó por escanner de bajada y subió al autobús"]
                break
            case 12:
                r = ["estado": "error", "codigo": res, "mensaje": "código no válido"]
                break
            case 13:
                r = ["estado": "error", "codigo": res, "mensaje": "el brazalete tiene estado de bajada"]
                break
            case 14:
                r = ["estado": "error", "codigo": res, "mensaje": "desconocido"]
                break
        }
        r
    }

}
