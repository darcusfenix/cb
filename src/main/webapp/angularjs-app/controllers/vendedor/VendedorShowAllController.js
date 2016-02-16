/**
 * Created by darcusfenix on 1/26/16.
 */
function VendedorShowAllController($rootScope,
                                   $scope,
                                   $http,
                                   $timeout,
                                   $stateParams,
                                   Vendedor,
                                   CostoPulsera,
                                   Persona,
                                   Duracion) {

    $scope.$on('$viewContentLoaded', function () {
        App.initAjax();
        $scope.getVendedores();
    });

    $scope.error = [];
    $scope.alerta = [];
    $scope.vendedorList;

    $scope.confirmacion;
    $scope.costosList;
    $scope.personaList;
    $scope.duracionList;

    $scope.pulseraList = [];

    $scope.getVendedores = function () {
        App.blockUI(
            {
                target: "#p-b-vendedor-list",
                boxed: !0,
                message: "Cargando..."
            });
        $scope.vendedor = Vendedor.query(function (data) {
            $scope.vendedorList = data;
            App.unblockUI("#p-b-vendedor-list");
        }, function (err) {
            $scope.error.flag = true;
            $scope.error.message = err.data.message;
        });

    };

    $scope.getTotalPulseras = function (id) {

        $scope.vendedor = Vendedor.getByEmail({
          id:id
        },function (data) {
            return data;
        }, function (err) {
            return 0;
        });

    };



};