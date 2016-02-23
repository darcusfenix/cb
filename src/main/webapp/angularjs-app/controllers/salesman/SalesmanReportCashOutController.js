/**
 * Created by becm on 2/16/16.
 */

function SalesmanReportCashOutController($scope, $filter, Bracelet, Circuit, KindPerson, DaysDuration) {
    $scope.$on('$viewContentLoaded', function () {
        App.initAjax();
        $scope.getMyAssignments();
    });

    $scope.isSalesman = true;

    $scope.avalibleCostsList;
    $scope.braceletSoldList = null;
    $scope.historyList = null;
    $scope.braceletNotSoldList = null;
    $scope.historyResumeList = [];

    $scope.colors =  [
        {"bg" :"#FFFFFF", "tc" : "#b02c6d"},
        {"bg" :"#FFFFFF", "tc" : "#496DA6"},
        {"bg" :"#b02c6d", "tc" : "#FFFFFF"},
        {"bg" :"#496DA6", "tc" : "#FFFFFF"}
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
        Bracelet.getBraceletsNotSold({
            cb : idCost
        },function (data) {
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

}