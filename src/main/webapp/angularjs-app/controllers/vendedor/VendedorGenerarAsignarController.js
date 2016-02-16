/**
 * Created by darcusfenix on 1/26/16.
 */

angular.module('CapitalBusApp').controller('VendedorGenerarAsignarController',
    function ($rootScope,
              $scope,
              $http,
              $timeout,
              $stateParams,
              Vendedor,
              CostoPulsera,
              Persona,
              Duracion,
              Pulsera) {

        $scope.$on('$viewContentLoaded', function () {
            App.initAjax();
            $scope.getThisVendedor();
        });

        $scope.error = [];
        $scope.alerta = [];
        $scope.confirmacion;

        $scope.costosList;
        $scope.personaList;
        $scope.duracionList;
        $scope.pulseraList = [];

        $scope.idPersona = 1;
        $scope.idDuracion = 1;

        $scope.costo = 0.00;
        $scope.cantidad = 0;

        $scope.getThisVendedor = function () {

            App.blockUI(
                {
                    target: "#generar-pulseras",
                    boxed: !0,
                    message: "Cargando..."
                },{
                    target: "#datos-personales",
                    boxed: !0,
                    message: "Cargando..."
                });
            $scope.vendedor = Vendedor.get({id: $stateParams.id}, function (data) {
                $scope.vendedor = data;
                App.unblockUI("#datos-personales");
                $scope.getCostosPulsera();
            }, function (err) {
                $scope.error.flag = true;
                $scope.error.message = err.data.message;
            });

        };

        $scope.getCostosPulsera = function () {
            $scope.vendedores = CostoPulsera.query(function (data) {
                $scope.costosList = data;
                //todo Debido a un error entre db y mapeo cuidar la posición del segundo corchete para --> $scope.costosList[i][2]
                $scope.getPersonas();
                $scope.prepararEnvio();
            }, function (err) {
                $scope.error.flag = true;
            });
        };

        $scope.getPersonas = function () {
            $scope.vendedores = Persona.query(function (data) {
                $scope.personaList = data;
                $scope.getDuracion();
            }, function (err) {
                $scope.error.flag = true;
            });
        };

        $scope.getDuracion = function () {
            $scope.vendedores = Duracion.query(function (data) {
                $scope.duracionList = data;
                App.unblockUI("#generar-pulseras");
            }, function (err) {
                $scope.error.flag = true;
            });
        };

        $scope.updateCosto = function () {
            $scope.alerta.flag = false;
            var flag = false;
            for (var i = 0; i < $scope.costosList.length; i++) {
                if ($scope.idPersona == $scope.costosList[i][2].persona.id &&
                    $scope.idDuracion == $scope.costosList[i][2].duracion.id) {
                    $scope.costo = $scope.costosList[i][1];
                    return null;
                }
            }
            if (!flag) {
                $scope.alerta.flag = true;
                $scope.alerta.message = "Esta persona no cuenta con estas horas";
            }
            $scope.preparConfirmacion();
        };

        $scope.getNameOfPersona = function (id) {
            for (var i = 0; i < $scope.personaList.length; i++) {
                if (id == $scope.personaList[i].id) {
                    return $scope.personaList[i].nombre;
                }
            }
        };

        $scope.getHorasOfDuracion = function (id) {
            for (var i = 0; i < $scope.duracionList.length; i++) {
                if (id == $scope.duracionList[i].id) {
                    return $scope.duracionList[i].duracion;
                }
            }
        };

        $scope.preparConfirmacion = function () {
            $scope.confirmacion = "Añadir " + $scope.cantidad + " pulseras a " + $scope.vendedor.nombres + " " + $scope.vendedor.apellidos + "" +
                " para " + $scope.getNameOfPersona($scope.idPersona) + " de " + $scope.getHorasOfDuracion($scope.idDuracion) + " horas ?.";
        };

        $scope.resetValoresPulseras = function () {
            for (var i = 0; i < $scope.pulseraList.length; i++) {
                $scope.pulseraList[i].cantidad = 0;
            }
        };

        $scope.prepararEnvio = function () {
            for (var i = 0; i < $scope.costosList.length; i++) {
                $scope.pulsera = {
                    "vendedor": $stateParams.id,
                    "cantidad": 0,
                    "costo": $scope.costosList[i][0]
                };
                $scope.pulseraList.push($scope.pulsera);
            }
        };

        $scope.sumarPulseras = function () {
            if ($scope.cantidad <= 0 || isNaN($scope.cantidad) || $scope.cantidad == null)
                return;

            for (var i = 0; i < $scope.costosList.length; i++) {
                if ($scope.idPersona == $scope.costosList[i][2].persona.id &&
                    $scope.idDuracion == $scope.costosList[i][2].duracion.id) {
                    for (var j = 0; j < $scope.pulseraList.length; j++) {
                        if ($scope.pulseraList[j].costo == $scope.costosList[i][0]) {
                            $scope.pulseraList[j].cantidad = $scope.cantidad;
                        }
                    }
                }
            }
            $scope.alerta.flag = false;
            $scope.cantidad = 0;
            return;
        };

        $scope.getTotalBetweenPersonaAndDuracion = function (persona, duracion) {
            for (var i = 0; i < $scope.costosList.length; i++) {
                if (persona == $scope.costosList[i][2].persona.id &&
                    duracion == $scope.costosList[i][2].duracion.id) {

                    for (var j = 0; j < $scope.pulseraList.length; j++) {
                        if ($scope.pulseraList[j].costo == $scope.costosList[i][0]) {
                            return $scope.pulseraList[j].cantidad;
                        }
                    }

                }
            }
        };

        $scope.getTotalByDuracion = function (duracion) {
            var total = 0;
            for (var i = 0; i < $scope.costosList.length; i++) {
                if (duracion == $scope.costosList[i][2].duracion.id) {

                    for (var j = 0; j < $scope.pulseraList.length; j++) {
                        if ($scope.pulseraList[j].costo == $scope.costosList[i][0]) {
                            total += $scope.pulseraList[j].cantidad;
                        }
                    }

                }
            }
            return total;
        };

        $scope.getTotalByPersona = function (persona) {
            var total = 0;
            for (var i = 0; i < $scope.costosList.length; i++) {
                if (persona == $scope.costosList[i][2].persona.id) {

                    for (var j = 0; j < $scope.pulseraList.length; j++) {
                        if ($scope.pulseraList[j].costo == $scope.costosList[i][0]) {
                            total += $scope.pulseraList[j].cantidad;
                        }
                    }

                }
            }
            return total;
        };

        $scope.getTotalOfPulseras = function () {
            var total = 0;

            for (var j = 0; j < $scope.pulseraList.length; j++) {
                total += $scope.pulseraList[j].cantidad;
            }

            return total;
        };

        $scope.pulseraInstance = Pulsera.create(function (data) {
            $scope.pulseraInstance = data;
        });

        $scope.generarPulseras = function () {

            App.blockUI({
                boxed: !0,
                message: "Generando y Asignando pulseras a " + $scope.vendedor.nombres + " " + $scope.vendedor.apellidos + "..."
            });

            $scope.pulseraInstance.$save({
                "json": $scope.generarJSON(),
                "idVendedor": $stateParams.id
            }, function (data) {
                App.unblockUI();
                $scope.resetValoresPulseras();
                var anchor = angular.element('<a/>');
                anchor.attr({
                    href: 'data:text/csv;charset=utf-8,' + encodeURIComponent(data.text),
                    target: '_blank',
                    download: $scope.vendedor.nombres + '-' + $scope.vendedor.apellidos + '.csv'
                })[0].click();

            }, function (err) {
                App.unblockUI();
            });
        };
        $scope.generarJSON = function () {
            var json = '[';
            for (i = 0; i < $scope.pulseraList.length; i++) {
                json += '{"vendedor":"' + $stateParams.id + '","cantidad": "' + $scope.pulseraList[i].cantidad + '", "idCosto": "' + $scope.pulseraList[i].costo + '"}';
                if (i < $scope.pulseraList.length - 1)
                    json += ','
            }
            return json + ']';
        };

    });