/**
 * Created by grupo-becm on 1/25/16.
 */
angular.module('CapitalBusApp').controller('SalesmanSearchController', function ($rootScope, $scope, $http, $timeout, Salesman) {
    $scope.$on('$viewContentLoaded', function () {
        App.initAjax();
    });
    $scope.vendedorList = null;
    $scope.q;

    $scope.getVendedores = function () {
        $scope.vendedores = Salesman.query({
            'q': $scope.q
        }, function (data) {
            $scope.vendedorList = data;
            console.log($scope.vendedorList)
        }, function (err) {
            console.log(err)
        });
    };

});