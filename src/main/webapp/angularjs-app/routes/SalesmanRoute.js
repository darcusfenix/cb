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

        .state('vendedorCorteCaja', {
            url: "/corte-de-caja",
            templateUrl: "angularjs-app/views/salesman/report-cash-out.gsp",
            data: {pageTitle: 'Corte de Caja', pageSubTitle: ''},
            controller: "SalesmanReportCashOutController",
            resolve: {
                deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'CapitalBusApp',
                        insertBefore: '#ng_load_plugins_before',
                        files: [
                            BASE_URL + 'rs/global/plugins/bootstrap-daterangepicker/daterangepicker.min.css',
                            BASE_URL + 'rs/global/plugins/bootstrap-daterangepicker/daterangepicker.min.js',
                            BASE_URL + 'rs/pages/scripts/dashboard.js',
                            BASE_URL + 'angularjs-app/resources/CircuitResource.js',
                            BASE_URL + 'angularjs-app/resources/CostBraceletResource.js',
                            BASE_URL + 'angularjs-app/controllers/salesman/SalesmanReportCashOutController.js',
                            BASE_URL + 'angularjs-app/directives/SalesmanReportCashOutDirective.js'
                        ]
                    });
                }]
            }
        })

        .state('salesmanMyAssignments', {
            url: "/mis-asignaciones",
            templateUrl: "angularjs-app/views/salesman/my-assignments.gsp",
            data: {pageTitle: 'Brazaletes', pageSubTitle: 'Asignaciones'},
            controller: "SalesmanMyAssignmentsController",
            resolve: {
                deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'CapitalBusApp',
                        insertBefore: '#ng_load_plugins_before',
                        files: [
                            BASE_URL + 'angularjs-app/resources/CircuitResource.js',
                            BASE_URL + 'angularjs-app/resources/CostBraceletResource.js',
                            BASE_URL + 'angularjs-app/controllers/salesman/SalesmanMyAssignmentsController.js',
                            BASE_URL + 'angularjs-app/services/ResumeBraceletsService.js'
                        ]
                    });
                }]
            }
        })

        .state('salesmanMyCashOut', {
            url: "/historial-corte-de-caja",
            templateUrl: "angularjs-app/views/salesman/my-cash-out.gsp",
            data: {pageTitle: 'Corte de Caja', pageSubTitle: 'Mi historial generado'},
            controller: "SalesmanHistoryCashOutController",
            resolve: {
                deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'CapitalBusApp',
                        insertBefore: '#ng_load_plugins_before',
                        files: [
                            BASE_URL + 'angularjs-app/resources/CircuitResource.js',
                            BASE_URL + 'angularjs-app/resources/CostBraceletResource.js',
                            BASE_URL + 'angularjs-app/controllers/salesman/SalesmanHistoryCashOutController.js',
                            BASE_URL + 'angularjs-app/directives/ReportPDFDirective.js'
                        ]
                    });
                }]
            }
        })
}]);