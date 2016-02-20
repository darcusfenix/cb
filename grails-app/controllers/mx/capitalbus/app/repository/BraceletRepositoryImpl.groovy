package mx.capitalbus.app.repository

import grails.converters.JSON
import mx.capitalbus.app.bracelet.Bracelet
import mx.capitalbus.app.user.Salesman

/**
 * Created by becm on 2/19/16.
 */
class BraceletRepositoryImpl implements BraceletRepository{

    @Override
    def getBySalesmanOrderAndGroupBy(long s) {
        //println(Salesman.findById(1))
        def b = Bracelet.createCriteria()

        def results = b.list {
            projections {
                groupProperty('costBracelet')
                count("costBracelet")
            }
            order("costBracelet", "asc")
        }

        results
    }
}
