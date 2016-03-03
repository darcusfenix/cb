function SalesmanHistoryCashOutController($scope, $filter, Bracelet) {

    $scope.historyList = [];
    $scope.d;
    $scope.currentResumen = [];

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

            for (var i = 0; i < $scope.historyList.length; i++) {
                $scope.historyList[i][0] = $filter('date')($scope.historyList[i][0], 'yyyy-MM-dd hh:mm:ss a', 'GMT+/-6:00');
            }

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

    $scope.getTotalByCurrentDate = function () {
        var t = 0;
        for (var i = 0; i < $scope.currentResumen.length; i++) {
            t += $scope.currentResumen[i][1];
        }
        return t;
    };

    $scope.getTotalSold = function () {
        var f = 0;
        if (typeof $scope.currentResumen !== 'undefined') {
            for (var j = 0; j < $scope.currentResumen.length; j++) {
                f += $scope.getTotalByCostSelected($scope.currentResumen[j][2].id) * $scope.currentResumen[j][2].cost
            }
        }
        return f;
    };

    $scope.getTotalByCostSelected = function (id) {
        var t = 0;
        for (var i = 0; i < $scope.currentResumen.length; i++) {
            if ($scope.currentResumen[i][2].id == id)
                t += $scope.currentResumen[i][1];
        }
        return t;
    };

    $scope.getResumen = function (d) {
        $scope.d = d;
        App.blockUI(
            {
                target: "#p-b-history-bracelets",
                boxed: !0,
                message: "Cargando..."
            });

        Bracelet.getBraceletsYesSold({
            "ss": d
        }, function (data) {
            $scope.currentResumen = data;
            App.unblockUI("#p-b-history-bracelets");
        }, function (err) {

            console.log(err)

        });
    };
}
