/**
 * Created by darcusfenix on 1/26/16.
 */


angular.module('CapitalBusApp').config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise("/");

    $stateProvider
        .state('vendedorGenerar', {
            url: "/vendedor/:id/historial",
            templateUrl: "angularjs-app/views/vendedor/show.gsp",
            data: {pageTitle: 'Perfil de Vendedor', pageSubTitle: 'Generación y Asignación de Pulseras'},
            controller: "VendedorGenerarAsignarController"
        })

        .state('vendedorBuscar', {
            url: "/buscar-vendedor",
            templateUrl: "angularjs-app/views/vendedor/search.gsp",
            data: {pageTitle: 'Búsqueda de Vendedores', pageSubTitle: ''},
            controller: "SalesmanSearchController"

        })

        .state('vendedorReporte', {
            url: "/vendedor/:id/reporte-pulseras",
            templateUrl: "angularjs-app/views/vendedor/reporte.gsp",
            data: {pageTitle: 'Reporte De Pulseras', pageSubTitle: ''},
            controller: "VendedorReporteController",
            resolve: {
                deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'CapitalBusApp',
                        insertBefore: '#ng_load_plugins_before',
                        files: [

                            BASE_URL + 'angularjs-app/controllers/vendedor/VendedorReporteController.js'
                        ]
                    });
                }]
            }
        })
}]);