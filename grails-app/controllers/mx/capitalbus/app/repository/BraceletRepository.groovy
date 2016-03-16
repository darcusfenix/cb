package mx.capitalbus.app.repository

import mx.capitalbus.app.bracelet.CostBracelet
import mx.capitalbus.app.user.Salesman

/**
 * Created by becm on 2/19/16.
 */
interface BraceletRepository {

    def getBySalesmanOrderAndGroupByCostBracelet(Salesman s)
    def getBySalesmanOrderAndGroupBySold(Salesman s)
    def getBySalesmanOrderAndGroupByDeliveryDate(Salesman s, String dateText)
    def getBySalesmanNotSold(Salesman s, CostBracelet cb, String sd, String ed)
    def getHistoryBySalesmanYesSold(String sd, String ed, long id, String ss)
    def validarSubida(String code, long bus, float lan, float lat)
    def validarBajada(String code, long bus, float lan, float lat)
    def getBySalesmanAndDate(Salesman id, String sd, boolean f)
    def getCostBraceletsBySalesman(Salesman s, String sd, boolean f)
    def getBySalesmanAndDate(Salesman s, Date date)
    def getBySalesmanYetNotSold(Salesman s, String sd)
}
