package mx.capitalbus.app.repository

import grails.converters.JSON
import mx.capitalbus.app.bracelet.Bracelet
import mx.capitalbus.app.user.Salesman

/**
 * Created by becm on 2/19/16.
 */
class BraceletRepositoryImpl implements BraceletRepository{

    @Override
    def getBySalesmanOrderAndGroupByCostBracelet(Salesman s) {
        def b = Bracelet.createCriteria()
        def results = b.list {
            projections {
                groupProperty('costBracelet')
                count("costBracelet")
            }
            and{
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
                count("costBracelet")
            }
            and{
                eq("salesman", s)
                eq("sold", true)
            }
            order("costBracelet", "asc")
        }
        results
    }
}
