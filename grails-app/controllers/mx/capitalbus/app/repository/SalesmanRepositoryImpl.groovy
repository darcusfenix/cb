package mx.capitalbus.app.repository

import mx.capitalbus.app.user.Salesman


/**
 * Created by becm on 2/10/16.
 */
class SalesmanRepositoryImpl implements SalesmanRepository{

    def getBySearch(String s){
        def v = Salesman.createCriteria()
        def results = v.list {
            and {
                eq("enabled", true)
            }
            or {
                like("username", "%" + s + "%")
                like("email", "%" + s + "%")
                like("firstName", "%" + s + "%")
                like("lastName", "%" + s + "%")
            }
            maxResults(25)
        }
        results
    }

    def getById(Integer id){
        def v = Salesman.findById(id)
        v
    }
}
