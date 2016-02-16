/**
 * Created by darcusfenix on 1/26/16.
 */

angular.module('CapitalBusApp').controller('VendedorReporteController',
    function ($rootScope,
              $scope,
              $http,
              $timeout,
              $stateParams,
              Vendedor) {

        $scope.$on('$viewContentLoaded', function () {
            App.initAjax();
            $scope.getThisVendedor();
        });

        $scope.error = [];
        $scope.alerta = [];

        $scope.costosList;
        $scope.personaList;
        $scope.duracionList;

        $scope.pulseraList = [];
        $scope.asignacionList = [];

        $scope.getThisVendedor = function () {

            App.blockUI(
                {
                    target: "#datos-personales",
                    boxed: !0,
                    message: "Cargando..."
                }, {
                    target: "#generar-pulseras",
                    boxed: !0,
                    message: "Cargando..."
                });

            $scope.vendedor = Vendedor.get({id: $stateParams.id}, function (data) {
                $scope.vendedor = data;
                App.unblockUI("#datos-personales");
                $scope.getAsignaciones();
            }, function (err) {
                $scope.error.flag = true;
                $scope.error.message = err.data.message;
            });

        };

        $scope.getAsignaciones = function () {
            $scope.vendedores = Vendedor.getAsignaciones({
                id: $stateParams.id
            }, function (data) {
                $scope.asignacionList = data;
                console.log($scope.asignacionList);
            }, function (err) {
                $scope.error.flag = true;
            });
        };


    });