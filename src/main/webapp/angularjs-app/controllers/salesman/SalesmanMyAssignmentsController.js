/**
 * Created by becm on 2/19/16.
 */

function SalesmanMyAssignmentsController($scope, Bracelet, Circuit, KindPerson, DaysDuration) {
    $scope.$on('$viewContentLoaded', function () {
        App.initAjax();
        $scope.getMyAssignments();
    });

    $scope.isSalesman = true;

    $scope.avalibleCostsList;
    $scope.braceletSoldList;

    $scope.getMyAssignments = function () {
        Bracelet.query(function (data) {
            $scope.avalibleCostsList = data;
            $scope.getCircuits();
        }, function (err) {

        });
    };

    $scope.getMyAssignmentsSold = function () {
        Bracelet.getMyAssignmentsSold(function (data) {
            $scope.braceletSoldList = data;
        }, function (err) {

        });
    };


    $scope.getCircuits = function () {
        Circuit.query(function (data) {
            $scope.circuitList = data;
            $scope.getKindPerson();
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
        }, function (err) {
            console.log(err)
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