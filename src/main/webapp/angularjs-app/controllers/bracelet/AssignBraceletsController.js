/**
 * Created by becm on 2/17/16.
 */

function AssignBraceletsController($rootScope, $scope, $http, $timeout, Salesman, Bracelet, Circuit, KindPerson, DaysDuration) {

    $scope.$on('$viewContentLoaded', function () {
        App.initAjax();
    });
    $scope.vendedorList = null;
    $scope.avalibleCostsList = null;
    $scope.kindPersontList;
    $scope.daysDurationList;
    $scope.circuitList = null;
    $scope.q;
    $scope.salesmanSelected;

    $scope.deliveryBraceletResumen = [];

    $scope.getVendedores = function () {
        if ($scope.q != null)
            $scope.vendedores = Salesman.query({
                'q': $scope.q
            }, function (data) {
                $scope.vendedorList = data;
            }, function (err) {

            });
    };

    Bracelet.costs(function (data) {
        $scope.avalibleCostsList = data;
        console.log($scope.avalibleCostsList)
        $scope.getCircuits();
    }, function (err) {

    });


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
            $scope.prepareDelivery();
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

    $scope.selectSalesman = function(it){
        $scope.salesmanSelected = it;
    };

    $scope.prepareDelivery = function(){
        for (var i = 0; i < $scope.avalibleCostsList.length; i++){
            var temp = {
                idCost: $scope.avalibleCostsList[i][0].id,
                startRange:  0,
                endsRange:  0
            };
            $scope.deliveryBraceletResumen.push(temp);
        }
    };
    $scope.validate = function(){
        var z = 0;
        for (var i = 0; i < $scope.deliveryBraceletResumen.length; i++){
            if ( ($scope.deliveryBraceletResumen[i].endsRange - $scope.deliveryBraceletResumen[i].startRange) <= 0
                || $scope.deliveryBraceletResumen[i].endsRange < $scope.deliveryBraceletResumen[i].startRange){
                z++
            }
            if (i > 0){
                if (
                    ( ($scope.deliveryBraceletResumen[i-1].endsRange + $scope.deliveryBraceletResumen[i-1].startRange) >=
                        ($scope.deliveryBraceletResumen[i].endsRange + $scope.deliveryBraceletResumen[i].startRange) )

                    || ($scope.deliveryBraceletResumen[i-1].endsRange  == $scope.deliveryBraceletResumen[i].endsRange )
                    || ($scope.deliveryBraceletResumen[i-1].startRange  == $scope.deliveryBraceletResumen[i].startRange )

                    || ($scope.deliveryBraceletResumen[i].startRange  == $scope.deliveryBraceletResumen[i-1].endsRange )
                    || ($scope.deliveryBraceletResumen[i].endsRange  == $scope.deliveryBraceletResumen[i-1].startRange )
                ){
                    z++
                }
            }
        }
        return (z == 0 ? true : false)
    };
}