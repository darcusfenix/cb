package mx.capitalbus.app.repository

/**
 * Created by becm on 2/10/16.
 */
interface SalesmanRepository {
    def getBySearch(String s)
    def getById(Integer id)
}