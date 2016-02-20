package mx.capitalbus.app.repository

import mx.capitalbus.app.user.Salesman

/**
 * Created by becm on 2/19/16.
 */
interface BraceletRepository {

    def getBySalesmanOrderAndGroupBy(long s);

}
