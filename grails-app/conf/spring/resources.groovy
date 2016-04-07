import marshallers.CustomObjectMarshallers
import marshallers.BraceletMarshaller

beans = {
    customObjectMarshallers(CustomObjectMarshallers) {
        marshallers = [
                new BraceletMarshaller()
        ]
    }
    salesmanRepository(mx.capitalbus.app.repository.SalesmanRepositoryImpl)
    braceletRepository(mx.capitalbus.app.repository.BraceletRepositoryImpl)
    userDetailsService(mx.capitalbus.app.MyUserDetailsService)
}
