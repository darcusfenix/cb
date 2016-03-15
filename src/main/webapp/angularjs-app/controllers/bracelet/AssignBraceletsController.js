/**
 * Created by becm on 2/17/16.
 */

function AssignBraceletsController($rootScope, $scope, $http, $filter, Salesman, Bracelet, Circuit, KindPerson, DaysDuration) {

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
    $scope.responseList = null;
    $scope.isSalesman = false;

    $scope.f1 = false;
    $scope.f2 = false;

    $scope.deliveryBraceletResumen = [];

    $scope.breceletInstance = Bracelet.create(function (data) {
        $scope.breceletInstance = data;
    });

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

    $scope.selectSalesman = function (it) {
        $scope.resetValuesRange();
        $scope.salesmanSelected = it;
        $scope.hideResponse();
    };

    $scope.prepareDelivery = function () {
        for (var i = 0; i < $scope.avalibleCostsList.length; i++) {
            var temp = {
                "idCost": $scope.avalibleCostsList[i][0].id,
                "startRange": 0,
                "endsRange": 0
            };
            $scope.deliveryBraceletResumen.push(temp);
        }
    };
    $scope.validate = function () {
        var z = 0;
        for (var i = 0; i < $scope.deliveryBraceletResumen.length; i++) {
            /*
            if (($scope.deliveryBraceletResumen[i].endsRange - $scope.deliveryBraceletResumen[i].startRange) <= 0
                || $scope.deliveryBraceletResumen[i].endsRange < $scope.deliveryBraceletResumen[i].startRange) {
                z++
            }

            if (i > 0) {
                if (
                    ( ($scope.deliveryBraceletResumen[i - 1].endsRange + $scope.deliveryBraceletResumen[i - 1].startRange) >=
                    ($scope.deliveryBraceletResumen[i].endsRange + $scope.deliveryBraceletResumen[i].startRange) )

                    || ($scope.deliveryBraceletResumen[i - 1].endsRange == $scope.deliveryBraceletResumen[i].endsRange )
                    || ($scope.deliveryBraceletResumen[i - 1].startRange == $scope.deliveryBraceletResumen[i].startRange )

                    || ($scope.deliveryBraceletResumen[i].startRange == $scope.deliveryBraceletResumen[i - 1].endsRange )
                    || ($scope.deliveryBraceletResumen[i].endsRange == $scope.deliveryBraceletResumen[i - 1].startRange )
                ) {
                    z++
                }
            }
            */
        }
        return (z == 0 ? true : false)
    };

    $scope.toAssignForSalesman = function () {
        $('#asignar').modal('hide');
        App.blockUI({
            boxed: !0,
            message: "Asignando brazaletes a " + $scope.salesmanSelected.firstName + " " + $scope.salesmanSelected.lastName + " ... \n NO ACTUALIZAR O CERRAR PÁGINA"
        });

        $scope.breceletInstance.$toAssign({
            "json": $scope.prepareJSON(),
            "salesman": $scope.salesmanSelected.id
        }, function (data) {
            $scope.responseList = data;

            App.unblockUI();

            $scope.showResponse();
            $('body').scrollTop(0);
        }, function (err) {
            App.unblockUI();
        });
    };
    $scope.prepareJSON = function () {
        var jsonText = '[';
        for (var i = 0; i < $scope.deliveryBraceletResumen.length; i++) {
            jsonText += '{"idCost":"' + $scope.deliveryBraceletResumen[i].idCost
                + '", "startRange":"' + (Number.isInteger($scope.deliveryBraceletResumen[i].startRange) ?  $scope.deliveryBraceletResumen[i].startRange : 0)
                + '", "endsRange":"' + (Number.isInteger($scope.deliveryBraceletResumen[i].endsRange) ? $scope.deliveryBraceletResumen[i].endsRange : 0)
                + '"}';
            if (i < $scope.deliveryBraceletResumen.length - 1)
                jsonText += ','
        }
        return jsonText + ']';
    };
    $scope.resetValuesRange = function () {
        for (var i = 0; i < $scope.deliveryBraceletResumen.length; i++) {

            $scope.deliveryBraceletResumen[i].startRange = 0;
            $scope.deliveryBraceletResumen[i].endsRange = 0;

            console.log($scope.deliveryBraceletResumen[i])
        }

    };
    $scope.getTotal = function () {
        var total = 0;
        for (var i = 0; i < $scope.deliveryBraceletResumen.length; i++) {
            var d = $scope.deliveryBraceletResumen[i].endsRange - $scope.deliveryBraceletResumen[i].startRange;
            total += d == 0 ? 0 : d + 1;
        }
        return total;
    };
    $scope.showResponse = function () {

        for (var i = 0; i < Object.keys($scope.responseList).length; i++) {

            if (typeof $scope.responseList[i] !== 'undefined') {
                if ($scope.responseList[i].status == 0) {
                    $scope.f1 = true;
                    $("#response-failed").append('<h5 style="padding-left: 20px;" class="block"> SERIE ' + $scope.responseList[i].idCost + ' ->' + $scope.responseList[i].mensaje + '</h5>');
                }
                if ($scope.responseList[i].status == 1) {
                    $scope.f2 = true;
                    $("#response-success").append('<h5  style="padding-left: 20px;" class="block"> SERIE ' + $scope.responseList[i].idCost + ' ->' + $scope.responseList[i].mensaje + '</h5>');
                }
            }
        }
        if ($scope.responseList.fecha) {
            $scope.responseList.fecha = $filter('date')($scope.responseList.fecha, 'yyyy-MM-dd hh:mm:ss a');
            $("#response-btn").html('<a class="btn purple btn-sm"  href="/vendedor/acuse/asignacion/'+$scope.responseList.fecha+'?idS='+$scope.salesmanSelected.id+'" target="_blank">VER ACUSE</a>');
        }
        $scope.updataTotalSeries();
    };
    $scope.hideResponse = function () {
        $scope.f1 = false
        $scope.f2 = false;
        $("#response-failed").html(" ");
        $("#response-success").html(" ");
    };

    $scope.updataTotalSeries = function () {
        Bracelet.costs(function (data) {
            $scope.avalibleCostsList = data;
        }, function (err) {

        });
    };
}