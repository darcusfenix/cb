// Place your Spring DSL code here
beans = {
    salesmanRepository(mx.capitalbus.app.repository.SalesmanRepositoryImpl)
    braceletRepository(mx.capitalbus.app.repository.BraceletRepositoryImpl)
    userDetailsService(mx.capitalbus.app.MyUserDetailsService)
}
