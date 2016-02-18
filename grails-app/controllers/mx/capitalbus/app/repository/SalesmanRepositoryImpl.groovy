package mx.capitalbus.app.repository

import mx.capitalbus.app.user.Salesman


/**
 * Created by becm on 2/10/16.
 */
class SalesmanRepositoryImpl implements SalesmanRepository {

    def getBySearch(String s) {
        if (s != null) {
            def v = Salesman.createCriteria()
            def results = v.list {
                and {
                    eq("enabled", true)
                }
                or {
                    ilike("username", "%" + s + "%")
                    ilike("email", "%" + s + "%")
                    ilike("firstName", "%" + s + "%")
                    ilike("lastName", "%" + s + "%")
                }
                maxResults(5)
            }
            results
        }
    }

    def getById(Integer id) {
        def v = Salesman.findById(id)
        v
    }
}
