/**
 * Created by becm on 2/16/16.
 */

function SalesmanReportCashOutController($scope, $filter, Bracelet, Circuit, KindPerson, DaysDuration) {
    $scope.$on('$viewContentLoaded', function () {
        App.initAjax();
        $scope.getMyAssignments();
        $scope.updateRangeDate();
    });

    $scope.isSalesman = true;
    $scope.startDate = null;
    $scope.endDate = null;

    $scope.avalibleCostsList;
    $scope.braceletSoldList = null;
    $scope.historyList = null;
    $scope.braceletNotSoldList = null;
    $scope.historyResumeList = [];
    $scope.reportList = [];

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
            $scope.getCircuits();
        }, function (err) {
        });
    };

    $scope.getBraceletsNotSold = function (idCost) {
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
        var tem = {
            "idCost": idCost,
            "idBracelet": idBracelet
        };
        var f = false;
        if ($scope.reportList.length >= 0) {
            for (var i = 0; i < $scope.reportList.length; i++) {
                if ($scope.reportList[i].idCost == idCost && $scope.reportList[i].idBracelet == idBracelet) {
                    $scope.reportList.splice(i, 1);
                    f = true;
                }
            }
        }
        if (!f)
            $scope.reportList.push(tem);
    };
    $scope.verify = function (idBracelet) {
        var f = false;
        for (var i = 0; i < $scope.reportList.length; i++) {
            if ($scope.reportList[i].idBracelet == idBracelet) {
                f =  true;
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
                    "Enero",
                    "Febrero",
                    "Marzo",
                    "Abril",
                    "Mayo",
                    "Junio",
                    "Julio",
                    "Agosto",
                    "Septiembre",
                    "Octubre",
                    "Noviembre",
                    "Deciembre"
                ],
                "firstDay": 1
            },

            opens: (App.isRTL() ? 'right' : 'left'),

        }, function(start, end, label) {
            $('#dashboard-report-range span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
            $scope.startDate = start.format('YYYY-MM-DD hh:mm:ss a');
            $scope.endDate = end.format('YYYY-MM-DD hh:mm:ss a');
            $scope.getBraceletsNotSold($scope.avalibleCostsList[0][0].id);
        });

        $('#dashboard-report-range span').html(moment().format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));
        $('#dashboard-report-range').show();
        $scope.startDate = moment().format('YYYY-MM-DD hh:mm:ss a');
        $scope.endDate = moment().format('YYYY-MM-DD hh:mm:ss a');

    };
    
}