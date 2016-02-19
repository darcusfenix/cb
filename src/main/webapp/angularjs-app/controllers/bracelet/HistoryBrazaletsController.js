/**
 * Created by darcusfenix on 2/15/16.
 */

/**
 * Created by becm on 2/10/16.
 */

function HistoryBrazaletsController($rootScope,
                                     $scope,
                                        $filter,
                                     $timeout,
                                     $stateParams,
                                     Bracelet) {

    $scope.historyList = [];

    $scope.$on('$viewContentLoaded', function () {
        App.initAjax();
        $scope.getHistory();
    });


    $scope.getHistory = function () {
        App.blockUI(
            {
                target: "#p-b-history-bracelets",
                boxed: !0,
                message: "Cargando..."
            });
        Bracelet.history(function (data) {
            $scope.historyList = data;
            App.unblockUI("#p-b-history-bracelets");
            console.log($scope.historyList)
        }, function (err) {
            console.log(err)
        });
    };

    $scope.getDaysDuration = function () {
        DaysDuration.query(function (data) {
            $scope.daysDurationList = data;

            $scope.updateCostOnView();
            $scope.preparingListOfBracelets();
        }, function (err) {
            console.log(err)
        });
    };
}
