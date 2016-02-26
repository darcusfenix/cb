
function SalesmanHistoryCashOutController($scope, Bracelet) {

    $scope.historyList = [];

    $scope.$on('$viewContentLoaded', function () {
        App.initAjax();
        $scope.getHistorySoldCashOut();
    });


    $scope.getHistorySoldCashOut = function () {
        App.blockUI(
            {
                target: "#p-b-history-bracelets",
                boxed: !0,
                message: "Cargando..."
            });
        Bracelet.getBraceletsYesSold(function (data) {
            $scope.historyList = data;
            App.unblockUI("#p-b-history-bracelets");
            $scope.getMyAssignments();
        }, function (err) {
            console.log(err)
        });
    };

    $scope.getMyAssignments = function () {
        Bracelet.query(function (data) {
            $scope.avalibleCostsList = data;
        }, function (err) {
        });
    };
}
