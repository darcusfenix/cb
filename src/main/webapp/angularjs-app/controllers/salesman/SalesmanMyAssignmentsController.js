/**
 * Created by becm on 2/19/16.
 */

function SalesmanMyAssignmentsController($scope, $filter, Bracelet, Circuit, KindPerson, DaysDuration) {
    $scope.$on('$viewContentLoaded', function () {
        App.initAjax();
        $scope.getMyAssignments();
    });

    $scope.isSalesman = true;

    $scope.avalibleCostsList;
    $scope.braceletSoldList = null;
    $scope.historyList = null;
    $scope.historyResumeList = [];

    $scope.getMyAssignments = function () {
        Bracelet.query(function (data) {
            $scope.avalibleCostsList = data;
            $scope.getCircuits();
        }, function (err) {
        });
    };
    $scope.getMyhistory = function () {
        Bracelet.historyBySalesman(function (data) {
            $scope.historyList = data;
            $scope.getResumeHistoryByDate();
        }, function (err) {
        });
    };

    $scope.getResumeHistoryByDate = function () {
        Bracelet.getResumeHistoryByDate(function(data){
            $scope.historyResumeList = data;
        }, function (err) {
        });
    };

    $scope.getTotalBySerie = function (d, c) {
        for (var i = 0; i < $scope.historyResumeList.length; i++ ){
            if ($scope.historyResumeList[i][1] == d && $scope.historyResumeList[i][0].id == c)
                return $scope.historyResumeList[i][2]

        }
    };

    $scope.getMyAssignmentsSold = function () {
        Bracelet.getMyAssignmentsSold(function (data) {
            $scope.braceletSoldList = data;
            $scope.getMyhistory();
        }, function (err) {
        });
    };

    $scope.getTotalBraceletsSold = function (id) {
        if($scope.braceletSoldList == null)
            return;
        for(var i = 0; i < $scope.braceletSoldList.length; i++){
            if($scope.braceletSoldList[i][0].id == id)
                return $scope.braceletSoldList[i][1]
        }
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

}