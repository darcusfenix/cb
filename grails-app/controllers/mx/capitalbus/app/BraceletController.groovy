package mx.capitalbus.app

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import groovy.json.JsonSlurper
import groovy.util.logging.Log
import mx.capitalbus.app.bracelet.Bracelet
import mx.capitalbus.app.bracelet.CostBracelet
import mx.capitalbus.app.user.Salesman

import java.text.ParseException
import java.text.SimpleDateFormat


@Secured(value = ["hasAnyRole('ROLE_SUPER_ADMIN','ROLE_SALESMAN', 'ROLE_ADMIN_CONTROL_BRACELET')"])
class BraceletController {


    def braceletService
    def braceletRepository
    def springSecurityService

    def create() {
        render(new Bracelet() as JSON)
    }

    def index() {
        def rs
        def so
        def principal = springSecurityService.principal
        long id = principal.id
        switch (principal.authorities[0]) {
            case "ROLE_SALESMAN":
                def s = Salesman.findById(id)
                rs = braceletRepository.getBySalesmanOrderAndGroupByCostBracelet(s)

                break
        }
        render(rs as JSON)
    }

    def getMyAssignmentsSold() {
        def rs
        def principal = springSecurityService.principal
        long id = principal.id
        switch (principal.authorities[0]) {
            case "ROLE_SALESMAN":
                def s = Salesman.findById(id)
                rs = braceletRepository.getBySalesmanOrderAndGroupBySold(s)
                break
        }
        render(rs as JSON)
    }

    def save() {
        String json = params.list("json");
        if (json != null) {
            def jsonSlurper = new JsonSlurper()
            def object = jsonSlurper.parseText(json)
            def map = [:]
            for (String a : object) {
                def cc = JSON.parse(a)
                Integer ic = cc.idCost
                Integer ca = cc.amount
                map.put(ic, ca)
            }
            def mapCVS = braceletService.generatingBracelets(map)
            render([text: mapCVS + ""] as JSON)
        } else {
            response.status = 404
            render([message: message(code: "vendedor.notFound")] as JSON)
        }
    }

    def getListOfCreations() {
        def b = Bracelet.createCriteria()
        def results = b.list {
            projections {
                count("creationDate")
                groupProperty('creationDate')
            }
            order("creationDate", "asc")
        }
        render(results as JSON)
    }

    def getListOfAssignnments() {
        def principal = springSecurityService.principal
        long id = principal.id
        def b = Bracelet.createCriteria()
        def results = b.list {
            projections {
                count("deliveryDate")
                groupProperty('deliveryDate')
            }
            and {
                eq("salesman", Salesman.findById(id))
            }
            order("deliveryDate", "asc")
        }
        render(results as JSON)
    }

    def getResumeHistoryByDate() {
        def principal = springSecurityService.principal
        long id = principal.id

        def results = []
        results = braceletRepository.getBySalesmanOrderAndGroupByDeliveryDate(Salesman.findById(id), "")
        render(results as JSON)
    }

    def getBraceletsNotSold() {

        def cb = params.int("cb")
        def sd = params.sd
        def ed = params.ed

        def principal = springSecurityService.principal
        long id = principal.id

        def results = []
        results = braceletRepository.getBySalesmanNotSold(Salesman.findById(id), CostBracelet.findById(cb), sd, ed)
        render(results as JSON)
    }

    def getHistoryBySalesmanYesSold() {

        def sd = params.sd
        def ed = params.ed
        def ss = params.ss

        def principal = springSecurityService.principal
        long id = principal.id

        def results = []
        results = braceletRepository.getHistoryBySalesmanYesSold(sd, ed, id, ss)
        render(results as JSON)
    }

    def getCSV() {
        String date = params.d
        if (date != null) {
            def s = braceletService.getStringOfCSV(date)
            if (s != null) {
                response.setHeader("Content-disposition", "attachment; filename=brazaletes.csv")
                render(contentType: "text/csv", text: s)
            } else {
                response.sendError(404)
                render([message: "error"] as JSON)
            }
        } else {
            response.sendError(404)
            render([message: "error"] as JSON)
        }
    }

    @Secured('ROLE_ADMIN_CONTROL_BRACELET')
    def getListOfCreationsByCost() {
        def b = Bracelet.createCriteria()
        def results = b.list {
            projections {
                groupProperty('costBracelet')
                count("costBracelet")
                count("deliveryDate")
            }
            order("costBracelet", "asc")
        }
        render(results as JSON)
    }

    @Secured(value = ["hasRole('ROLE_ADMIN_CONTROL_BRACELET')"], httpMethod = 'POST')
    def toAssignForSalesman() {
        def jsonText = params.json
        def salesman = params.int("salesman")
        def op = params.int("op")
        if (jsonText != null || !jsonText.empty || salesman > 0) {
            def res = braceletService.updateBraceletsWithSalesman(jsonText, salesman, op)

            render(res as JSON)
        } else
            render(["message": "hubo un error"] as JSON)
    }

    @Secured(value = 'permitAll', httpMethod = 'POST')
    def verifyCodeWithScannerFirst() {

        String code = params.code
        long bus = params.long("bus") ? params.long("bus") : 0

        float lon = params.float("lon") ? params.float("lon") : 0f
        float lat = params.float("lat") ? params.float("lat") : 0f

        def res

        if (code.length() == 10)
            res = braceletRepository.validarSubida(code,bus,lon,lat)
        else
            res = 12

        def r = braceletService.getResponse(res);

        render(r as JSON)
    }

    @Secured(value = 'permitAll', httpMethod = 'POST')
    def verifyCodeWithScannerSecond() {

        String code = params.code
        long bus = params.long("bus") ? params.long("bus") : 0

        float lon = params.float("lon") ? params.float("lon") : 0f
        float lat = params.float("lat") ? params.float("lat") : 0f

        def res

        if (code.length() == 10)
            res = braceletRepository.validarBajada(code,bus,lon,lat)
        else
            res = 12

        def r = braceletService.getResponse(res);

        render(r as JSON)
    }

    def getHistorySalesman(){
        def sd = params.sd
        def ed = params.ed
        def results
        TimeZone.setDefault(TimeZone.getTimeZone("UTC-6"));
        Date start, end
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        def a = sdf.parse("2016-03-27T19:21:41Z")
        log.error(a)
        Calendar now = Calendar.getInstance();
        a.clearTime()
        log.error(a)
        def w =  a+1
        log.error(w)
        if (sd == null && ed == null) {
            now.set(Calendar.HOUR, 0);
            now.set(Calendar.MINUTE, 0);
            now.set(Calendar.SECOND, 0);
            now.set(Calendar.DAY_OF_WEEK_IN_MONTH, -7);
            start = new Date()

            end = now.getTime()

        }

        def query  = Bracelet.createCriteria()

        results = query.list {

            projections {
                groupProperty('deliveryDate')
                count("deliveryDate")
            }
/*
            and{
                between("deliveryDate", end, start)
            }
*/

            and{
                between('deliveryDate', a,w)
            }

//            maxResults(5)

            //order("salesman", "desc")

        }

        render (results as JSON)

    }

}
