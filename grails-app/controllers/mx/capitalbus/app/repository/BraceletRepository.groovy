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
    def getHistoryBySalesmanYesSold(String sd, String ed, long id)
}
