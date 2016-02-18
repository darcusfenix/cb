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

    def generatingBracelets(Map objeto) {

        def map = "ID,CODIGO,TIPO,FECHA_CREACION\n";
        //todo cuidar las secuencias con el id
        def bs = BraceletState.findByName(1) // estado de brazalete como generado
        def dateNow = new Date()


        objeto.each { w ->
            if (w.value > 0){
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

    def String getStringOfCSV(String date){
        String mapCVS = null;

        SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd hh:mm a");

        def d = dateParser.parse(date)

        if (d != null) {
            mapCVS = "ID,CODIGO,TIPO,FECHA_CREACION\n"
            Calendar calender = Calendar.getInstance();
            calender.setTimeInMillis(d.getTime());
            calender.add(Calendar.SECOND, 1000);
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
    def updateBraceletsWithSalesman(String textJson, Integer idSalesman){
        TimeZone.setDefault(TimeZone.getTimeZone("GMT-6"));
        def s = Salesman.findById(idSalesman)
        def mapMessage = [:]

        if (s != null){
            def bs = BraceletState.findById(2) // estado de brazalete como activado
            def date = new Date()
            def jsonSlurper = new JsonSlurper()
            def object = jsonSlurper.parseText(textJson)

            for (String p : object) {
                def j = JSON.parse(p)
                Integer i = j.idCost
                long sr = j.startRange
                long er = j.endsRange

                def serie = CostBracelet.findById(i)
                def query = Bracelet.where {
                    (costBracelet == serie && salesman == null && deliveryDate == null && (id in sr..er))
                }
                def braceletsList = query.list()

                /*def braceletsList = Bracelet.createCriteria().list {
                    and {
                        eq("costBracelet", serie)
                        eq("salesman", null)
                        eq("deliveryDate", null)
                        between("id", sr, er)
                    }
                }
                */

                if (braceletsList.size() > 0){
                    log.error("hubo resultados")
                    braceletsList.each { b ->
                        log.error(b)
                        b.deliveryDate = date
                        b.braceletState = bs
                        b.salesman = s
                        if (b.validate())
                            b.save(flush: true)
                        else
                            log.error(b.errors)
                    }
                    mapMessage.put(i, "El rango se asignó exitoxamente")
                }
                else{
                    log.error("no hubo resultados")
                    mapMessage.put(i, "EL rango no coincide")
                }

            }
        }
        mapMessage
    }
}
