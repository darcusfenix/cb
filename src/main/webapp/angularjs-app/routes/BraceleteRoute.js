/**
 * Created by grupo-becm on 1/25/16.
 */

angular.module('CapitalBusApp').config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise("/");

    $stateProvider
        .state('brazaleteGenerar', {
            url: "/generar-brazaletes",
            templateUrl: "angularjs-app/views/bracelet/generate.gsp",
            data: {pageTitle: 'Brazaletes', pageSubTitle: 'Generación de Brazaletes'},
            controller: "BraceleteGenerateController",
            resolve: {
                deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'CapitalBusApp',
                        insertBefore: '#ng_load_plugins_before',
                        files: [
                            BASE_URL + 'angularjs-app/resources/CircuitResource.js',
                            BASE_URL + 'angularjs-app/resources/CostBraceletResource.js',
                            BASE_URL + 'angularjs-app/controllers/bracelet/BraceleteGenerateController.js'
                        ]
                    });
                }]
            }
        }).state('brazaleteHistory', {
            url: "/historial-brazaletes",
            templateUrl: "angularjs-app/views/bracelet/creations.gsp",
            data: {pageTitle: 'Brazaletes', pageSubTitle: 'Historial de generaciones de Brazaletes'},
            controller: "HistoryBrazaletsController",
            resolve: {
                deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'CapitalBusApp',
                        insertBefore: '#ng_load_plugins_before',
                        files: [
                            BASE_URL + 'angularjs-app/controllers/bracelet/HistoryBrazaletsController.js'
                        ]
                    });
                }]
            }
        }).state('brazaleteAssign', {
            url: "/entregar-brazaletes",
            templateUrl: "angularjs-app/views/bracelet/assigns.gsp",
            data: {pageTitle: 'Entrega de Brazaletes a vendedores', pageSubTitle: ''},
            controller: "AssignBraceletsController",
            resolve: {
                deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'CapitalBusApp',
                        insertBefore: '#ng_load_plugins_before',
                        files: [
                            BASE_URL + 'angularjs-app/resources/CircuitResource.js',
                            BASE_URL + 'angularjs-app/resources/CostBraceletResource.js',
                            BASE_URL + 'angularjs-app/controllers/bracelet/AssignBraceletsController.js'
                        ]
                    });
                }]
            }
        }).state('brazaletSearch', {
            url: "/buscar-brazalete",
            templateUrl: "angularjs-app/views/bracelet/search.gsp",
            data: {pageTitle: 'Búsqueda de brazaletes', pageSubTitle: ''},
            controller: "AdminBraceletController",
            resolve: {
                deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'CapitalBusApp',
                        insertBefore: '#ng_load_plugins_before',
                        files: [
                            BASE_URL + 'angularjs-app/controllers/bracelet/AdminBraceletController.js'
                        ]
                    });
                }]
            }
        }).state('braceletAssignments', {
            url: "/historial-de-brazaletes",
            templateUrl: "angularjs-app/views/bracelet/assignmentsList.gsp",
            data: {pageTitle: 'Asignaciones de brazaletes', pageSubTitle: ''},
            controller: "HistoryBraceletController",
            resolve: {
                deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'CapitalBusApp',
                        insertBefore: '#ng_load_plugins_before',
                        files: [
                            BASE_URL + 'rs/global/plugins/bootstrap-daterangepicker/daterangepicker.min.css',
                            BASE_URL + 'rs/global/plugins/bootstrap-daterangepicker/daterangepicker.min.js',
                            BASE_URL + 'rs/pages/scripts/dashboard.js',
                            BASE_URL + 'angularjs-app/resources/CostBraceletResource.js',
                            BASE_URL + 'angularjs-app/controllers/bracelet/HistoryBraceletController.js'
                        ]
                    });
                }]
            }
        })
}]);