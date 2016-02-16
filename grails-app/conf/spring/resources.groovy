// Place your Spring DSL code here
beans = {
    salesmanRepository(mx.capitalbus.app.repository.SalesmanRepositoryImpl)
    userDetailsService(mx.capitalbus.app.MyUserDetailsService)
}
