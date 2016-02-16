/**
 * Created by becm on 2/10/16.
 */

function BraceleteGenerateController($rootScope,
                                     $scope,
                                     $http,
                                     $timeout,
                                     $stateParams,
                                     Bracelet,
                                     Circuit,
                                     KindPerson,
                                     DaysDuration, CostBracelet) {

    $scope.idCircuit = 1;
    $scope.idKindPerson = 1;
    $scope.idDaysDuration = 1;
    $scope.costCurrent = 0;
    $scope.amount = 0;
    $scope.alertNotFound;

    $scope.costBraceletList;
    $scope.kindPersontList;
    $scope.daysDurationList;
    $scope.braceletList = [];

    $scope.currentKindPersontList = [];
    $scope.currentDaysDurationList = [];


    $scope.$on('$viewContentLoaded', function () {
        App.initAjax();
        $scope.getCircuits();
    });

    $scope.breceletInstance  = Bracelet.create(function (data) {
        $scope.breceletInstance = data;
    });


    $scope.getCircuits = function () {

        App.blockUI(
            {
                target: "#generate-bracelet",
                boxed: !0,
                message: "Cargando..."
            });

        Circuit.query(function (data) {
            $scope.circuitList = data;
            $scope.getCostBraceletByCircuit(1)
        }, function (err) {
            console.log(err)
        });
    };

    $scope.getKindPerson = function () {
        KindPerson.query(function (data) {
            $scope.kindPersontList = data;
            $scope.getDaysDuration();

        }, function (err) {
            console.log(err)
        });
    };
    $scope.getDaysDuration = function () {
        DaysDuration.query(function (data) {
            $scope.daysDurationList = data;
            App.unblockUI("#generate-bracelet");
            $scope.updateCostOnView();
            $scope.preparingListOfBracelets();
        }, function (err) {
            console.log(err)
        });
    };
    $scope.getCostBraceletByCircuit = function (idCircuit) {
        CostBracelet.getCostBraceletByCircuit({
            id: idCircuit
        }, function (data) {
            $scope.costBraceletList = data[0];
            $scope.getKindPerson();
        }, function (err) {
            console.log(err)
        });
    };

    $scope.updateListsWithCurrentCircuit = function () {
        for (var i = 0; i < Object.keys($scope.costBraceletList).length; i++) {

            var tempKindPerson = {id: null, name: null};
            var tempDaysDuration = {id: null, days: null};

            for (var j = 0; j < $scope.kindPersontList.length; j++)
                if ($scope.costBraceletList[i].kindPerson.id == $scope.kindPersontList[j].id) {
                    tempKindPerson.id = $scope.kindPersontList[j].id;
                    tempKindPerson.name = $scope.kindPersontList[j].name;
                    $scope.currentKindPersontList.push(tempKindPerson);
                }

            for (var j = 0; j < $scope.daysDurationList.length; j++)
                if ($scope.costBraceletList[i].daysDuration.id == $scope.daysDurationList[j].id) {
                    tempDaysDuration.id = $scope.daysDurationList[j].id;
                    tempDaysDuration.days = $scope.daysDurationList[j].days;
                    $scope.currentDaysDurationList.push(tempDaysDuration);
                }
        }

    };

    $scope.updateCostOnView = function () {
        var flag = false;
        for (var i = 0; i < Object.keys($scope.costBraceletList).length; i++)
            if ($scope.costBraceletList[i].kindPerson.id == $scope.idKindPerson && $scope.costBraceletList[i].daysDuration.id == $scope.idDaysDuration) {
                $scope.costCurrent = $scope.costBraceletList[i].cost;
                flag = true;
            }
        if (!flag) {
            $scope.costCurrent = 0;
            $scope.alertNotFound = "No hay costo para esta selección";
        }
    };

    $scope.getNameOfKindPerson = function (id) {
        for (var j = 0; j < $scope.kindPersontList.length; j++)
            if (id == $scope.kindPersontList[j].id)
                return $scope.kindPersontList[j].name;
    };

    $scope.getNameOfDaysDuration = function (id) {
        for (var j = 0; j < $scope.daysDurationList.length; j++)
            if (id == $scope.daysDurationList[j].id) {
                return $scope.daysDurationList[j].days;
            }
    };


    $scope.preparingConfirmation = function () {
        if ($scope.amount <= 0 || isNaN($scope.amount) || $scope.amount == null)
            return;

        $scope.confirmation = "Añadir " + $scope.amount + " pulseras " +
            " para " + $scope.getNameOfKindPerson($scope.idKindPerson) + " de " + $scope.getNameOfDaysDuration($scope.idDaysDuration) + "? ";
    };

    $scope.getTotalBetweenKindPersonAndDaysDuration = function (ik, idd) {
        for (var i = 0; i < Object.keys($scope.costBraceletList).length; i++) {
            if (ik == $scope.costBraceletList[i].kindPerson.id  &&
                idd == $scope.costBraceletList[i].daysDuration.id ) {
                for (var j = 0; j < $scope.braceletList.length; j++) {
                    if ($scope.braceletList[j].idCost == $scope.costBraceletList[i].id) {
                        return $scope.braceletList[j].amount;
                    }
                }
            }
        }
    };
    $scope.getTotalByKIndPerson = function (idk) {
        var total = 0;
        for (var i = 0; i < Object.keys($scope.costBraceletList).length; i++) {
            if (idk == $scope.costBraceletList[i].kindPerson.id) {

                for (var j = 0; j < $scope.braceletList.length; j++) {
                    if ($scope.braceletList[j].idCost == $scope.costBraceletList[i].id) {
                        total += $scope.braceletList[j].amount;
                    }
                }

            }
        }
        return total;
    };
    $scope.getTotalByDaysDuration = function (idd) {
        var total = 0;
        for (var i = 0; i < Object.keys($scope.costBraceletList).length; i++) {
            if (idd ==  $scope.costBraceletList[i].daysDuration.id) {

                for (var j = 0; j < $scope.braceletList.length; j++) {
                    if ($scope.braceletList[j].idCost == $scope.costBraceletList[i].id) {
                        total += $scope.braceletList[j].amount;
                    }
                }

            }
        }
        return total;
    };
    $scope.getTotalOfBracelets = function (idd) {
        var total = 0;

        for (var j = 0; j < $scope.braceletList.length; j++) {
            total += $scope.braceletList[j].amount;
        }

        return total;
    };

    $scope.preparingListOfBracelets = function () {
        for (var i = 0; i < Object.keys($scope.costBraceletList).length; i++) {
            var bracelet = {
                "idCost": $scope.costBraceletList[i].id,
                "amount": 0
            };
            $scope.braceletList.push(bracelet);

        }
    };

    $scope.addBracelets = function () {
        if ($scope.amount <= 0 || isNaN($scope.amount) || $scope.amount == null)
            return;

        for (var i = 0; i < Object.keys($scope.costBraceletList).length; i++) {
            if ($scope.idKindPerson == $scope.costBraceletList[i].kindPerson.id  &&
                $scope.idDaysDuration == $scope.costBraceletList[i].daysDuration.id ) {

                for (var j = 0; j < $scope.braceletList.length; j++) {
                    if ($scope.braceletList[j].idCost == $scope.costBraceletList[i].id) {
                        $scope.braceletList[j].amount = $scope.amount;
                    }
                }
            }
        }
        $scope.amount = 0;
        return;
    };

    $scope.resetValuesBracelets = function () {
        for (var i = 0; i < $scope.braceletList.length; i++) {
            $scope.braceletList[i].amount = 0;
        }
    };


    $scope.generetingBracelets = function () {

        App.blockUI({
            boxed: !0,
            message: "Generando Brazaletes... \n NO ACTUALIZAR O CERRAR PÁGINA"
        });

        $scope.breceletInstance.$save({
            "json": $scope.prepareJSON()
        }, function (data) {
            App.unblockUI();

            var anchor = angular.element('<a/>');
            anchor.attr({
                href: 'data:text/csv;charset=utf-8,' + encodeURIComponent(data.text),
                target: '_blank',
                download: 'generación-de-'+$scope.getTotalOfBracelets()+'-brazaletes.csv'
            })[0].click();
            $scope.resetValuesBracelets();
        }, function (err) {
            App.unblockUI();
        });
    };
    $scope.prepareJSON = function () {
        var json = '';
        for (i = 0; i < $scope.braceletList.length; i++) {
            json += '{"amount":"' + $scope.braceletList[i].amount + '", "idCost":"' + $scope.braceletList[i].idCost + '"}';
            if (i < $scope.braceletList.length - 1)
                json += ','
        }
        return json + '';
    };


}