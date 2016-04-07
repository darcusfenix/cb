/**
 * Created by becm on 2/16/16.
 */

function SalesmanReportCashOutController($scope, $filter, Bracelet, Circuit, KindPerson, DaysDuration, Salesman) {
    $scope.$on('$viewContentLoaded', function () {
        App.initAjax();
        $scope.getMyAssignments();
        $scope.updateRangeDate();
    });

    $scope.isSalesman = true;
    $scope.successSave = false;

    $scope.startDate = null;
    $scope.endDate = null;
    $scope.idCost = null;

    $scope.avalibleCostsList;
    $scope.braceletSoldList = null;
    $scope.historyList = null;
    $scope.braceletNotSoldList = null;
    $scope.historyResumeList = [];
    $scope.reportList = [];


    $scope.checkboxModelFilter = {
        selected: false,
        scanningBus: false,
        all: true
    };

    $scope.braceletInstance = Bracelet.create(function (data) {
        $scope.braceletInstance = data;
    });

    $scope.getMyAssignmentsSold = function () {
        Bracelet.getMyAssignmentsSold(function (data) {
            $scope.braceletSoldList = data;
        }, function (err) {
        });
    };

    $scope.getTotalBraceletsSold = function (id) {
        if ($scope.braceletSoldList == null)
            return;
        for (var i = 0; i < $scope.braceletSoldList.length; i++) {
            if ($scope.braceletSoldList[i][0].id == id)
                return $scope.braceletSoldList[i][1]
        }
    };


    $scope.colors = [
        {"bg": "#FFFFFF", "tc": "#b02c6d"},
        {"bg": "#FFFFFF", "tc": "#496DA6"},
        {"bg": "#b02c6d", "tc": "#FFFFFF"},
        {"bg": "#496DA6", "tc": "#FFFFFF"}
    ];

    $scope.getMyAssignments = function () {
        App.blockUI(
            {
                target: "#corte",
                boxed: !0,
                message: "Cargando..."
            });
        Bracelet.query(function (data) {
            $scope.avalibleCostsList = data;
            if ($scope.avalibleCostsList.length != 0)
                $scope.getCircuits();
        }, function (err) {
        });
    };

    $scope.getBraceletsNotSold = function (idCost) {
        $scope.idCost = idCost;
        App.blockUI(
            {
                target: "#corte",
                boxed: !0,
                message: "Cargando..."
            });
        Bracelet.getBraceletsNotSold({
            cb: idCost,
            sd: $scope.startDate,
            ed: $scope.endDate
        }, function (data) {
            $scope.braceletNotSoldList = data;
            App.unblockUI("#corte");
            $scope.addToReportOnlyScanned();
        }, function (err) {
        });
    };

    $scope.getCircuits = function () {
        Circuit.query(function (data) {
            $scope.circuitList = data;
            $scope.getKindPerson();
        }, function (err) {
        });
    };

    $scope.getKindPerson = function () {
        KindPerson.query(function (data) {
            $scope.kindPersontList = data;
            $scope.getDaysDuration();
        }, function (err) {
        });
    };
    $scope.getDaysDuration = function () {
        DaysDuration.query(function (data) {
            $scope.daysDurationList = data;
            $scope.getBraceletsNotSold($scope.avalibleCostsList[0][0].id);
            $scope.getMyAssignmentsSold();
        }, function (err) {
        });
    };

    $scope.getNameOfKindPerson = function (id) {
        if ($scope.kindPersontList != null)
            for (var j = 0; j < $scope.kindPersontList.length; j++)
                if (id == $scope.kindPersontList[j].id)
                    return $scope.kindPersontList[j].name;
    };

    $scope.getNameOfDaysDuration = function (id) {
        if ($scope.daysDurationList != null)
            for (var j = 0; j < $scope.daysDurationList.length; j++)
                if (id == $scope.daysDurationList[j].id) {
                    return $scope.daysDurationList[j].days;
                }
    };

    $scope.getNameOfCircuit = function (id) {
        if ($scope.circuitList != null)
            for (var j = 0; j < $scope.circuitList.length; j++)
                if (id == $scope.circuitList[j].id) {
                    return $scope.circuitList[j].name;
                }
    };
    $scope.addToReport = function (idCost, idBracelet) {
        $scope.successSave = false;
        var tem = {
            "idCost": idCost,
            "idBracelet": idBracelet,
            "move": true
        };
        var f = false;
        if ($scope.reportList.length >= 0) {
            for (var i = 0; i < $scope.reportList.length; i++) {
                if ($scope.reportList[i].idCost == idCost
                    && $scope.reportList[i].idBracelet == idBracelet) {
                    if ($scope.reportList[i].move) {
                        $scope.reportList.splice(i, 1);
                        f = true;
                    } else {
                        return;
                    }
                }
            }
        }
        if (!f)
            $scope.reportList.push(tem);
    };


    $scope.addToReportOnlyScanned = function () {
        for (var i = 0; i < $scope.braceletNotSoldList.length; i++) {
            if ($scope.braceletNotSoldList[i].activationDate != null) {
                var f = false;
                if ($scope.reportList.length != 0)
                    for (var j = 0; j < $scope.reportList.length; j++) {
                        if ($scope.reportList[j].idCost == $scope.braceletNotSoldList[i].costBracelet.id
                            && $scope.reportList[j].idBracelet == $scope.braceletNotSoldList[i].id)
                            f = true;
                    }
                if (!f) {
                    var tem = {
                        "idCost": $scope.braceletNotSoldList[i].costBracelet.id,
                        "idBracelet": $scope.braceletNotSoldList[i].id,
                        "move": false
                    };
                    $scope.reportList.push(tem);
                }
            }
        }
    };

    $scope.verify = function (idBracelet) {
        var f = false;
        for (var i = 0; i < $scope.reportList.length; i++) {
            if ($scope.reportList[i].idBracelet == idBracelet) {
                f = true;
            }
        }
        return f;
    };
    $scope.getTotalByCostSelected = function (idCost) {
        var f = 0;
        for (var i = 0; i < $scope.reportList.length; i++) {
            if ($scope.reportList[i].idCost == idCost) {
                f++;
            }
        }
        return f;
    };
    $scope.getTotalSold = function () {
        var f = 0;
        if (typeof $scope.avalibleCostsList !== 'undefined') {
            for (var j = 0; j < $scope.avalibleCostsList.length; j++) {
                f += $scope.getTotalByCostSelected($scope.avalibleCostsList[j][0].id) * $scope.avalibleCostsList[j][0].cost
            }
        }
        return f;
    };
    $scope.getBraceletsSelected = function (idCost) {
        var f = "";

        for (var i = 0; i < $scope.reportList.length; i++) {
            if ($scope.reportList[i].idCost == idCost) {
                f += $scope.reportList[i].idBracelet + ", "
            }
        }

        return f;
    };

    $scope.saveCorteCaja = function () {
        App.blockUI(
            {
                boxed: !0,
                message: "Generando corte de caja..."
            });
        var l = JSON.stringify($scope.reportList);
        $scope.braceletInstance.$saveCorteCaja(
            {
                "json": l
            }, function (data) {
                $scope.getMyAssignmentsSold();
                App.unblockUI();
                $scope.reportList = [];
                $scope.successSave = true;
                $('body').scrollTop(0);
                $scope.getBraceletsNotSold($scope.idCost);
            }, function (err) {

            });
    };

    $scope.updateRangeDate = function () {
        $('#dashboard-report-range').daterangepicker({
            "ranges": {
                'Hoy': [moment(), moment()],
                'Ayer': [moment().subtract('days', 1), moment().subtract('days', 1)],
                'Últimos 7 días': [moment().subtract('days', 6), moment()],
                'Últimos 30 DÍAS': [moment().subtract('days', 29), moment()],
                'Este Mes': [moment().startOf('month'), moment().endOf('month')],
                'Último Mes': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
            },
            "locale": {
                "format": "yyyy-MM-dd hh:mm:ss a",
                "separator": " - ",
                "applyLabel": "Aplicar",
                "cancelLabel": "Cancelar",
                "fromLabel": "Desde",
                "toLabel": "Hasta",
                "customRangeLabel": "Personalizar",
                "daysOfWeek": [
                    "Do",
                    "Lu",
                    "Ma",
                    "Mie",
                    "Ju",
                    "Vi",
                    "Sa"
                ],
                "monthNames": [
                    "ENERO",
                    "FEBRERO",
                    "MARZO",
                    "ABRIL",
                    "MAYO",
                    "JUNIO",
                    "JULIO",
                    "AGOSTO",
                    "SEPTIEMBRE",
                    "OCTUBRE",
                    "NOVIEMBRE",
                    "DICIEMBRE"
                ],
                "firstDay": 1
            },

            opens: (App.isRTL() ? 'right' : 'left'),

        }, function (start, end, label) {
            $('#dashboard-report-range span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
            $scope.startDate = start.format('YYYY-MM-DD hh:mm:ss a');
            $scope.endDate = end.format('YYYY-MM-DD hh:mm:ss a');
            $scope.getBraceletsNotSold($scope.idCost);
        });

        $('#dashboard-report-range span').html(moment().format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));
        $('#dashboard-report-range').show();

        $scope.startDate = moment().format('YYYY-MM-DD hh:mm:ss a');
        $scope.endDate = moment().format('YYYY-MM-DD hh:mm:ss a');

    };

}