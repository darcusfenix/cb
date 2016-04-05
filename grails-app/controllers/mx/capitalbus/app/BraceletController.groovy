package mx.capitalbus.app

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import groovy.json.JsonSlurper
import groovy.util.logging.Log
import mx.capitalbus.app.bracelet.Bracelet
import mx.capitalbus.app.bracelet.CostBracelet
import mx.capitalbus.app.user.Salesman
import sun.util.resources.cldr.en.TimeZoneNames_en_ZA

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
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        def sd = params.sd
        def ed = params.ed
        def op = params.int("op")
        def results
        log.error(sd)
        log.error(ed)
        Date start, end

        if (sd == null && ed == null) {
            start = now.getTime()
            end = now.getTime()
            end.clearTime();
        }else{
            start  = sdf.parse(sd)
            end  = sdf.parse(ed).clearTime()
        }

        def query  = Bracelet.createCriteria()

        log.error("END -----> "  + end)
        log.error("START -----> "  + start)

        results = query.list {
            projections {
                if (op == 1){
                    groupProperty('deliveryDate')
                    count("deliveryDate")
                }
                if (op == 2){
                    groupProperty('assignmentDate')
                    count("assignmentDate")
                }
                if (op == 3){
                    groupProperty('activationDate')
                    count("activationDate")
                }
            }
            and{
                if (op == 1){
                    between("deliveryDate", start, end)
                }
                if (op == 2){
                    between("assignmentDate", start, end)
                }
                if (op == 3){
                    between("activationDate", start, end)
                }
            }
            if (op == 1){
                order("deliveryDate", "desc")
            }
            if (op == 2){
                order("assignmentDate", "desc")
            }
            if (op == 3){
                order("activationDate", "desc")
            }
        }
        results.each{ r ->
            log.error(r[0])
            def  s = Bracelet.findAllBy
        }

        render (results as JSON)

    }

}
