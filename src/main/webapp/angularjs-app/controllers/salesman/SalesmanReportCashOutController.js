/**
 * Created by becm on 2/16/16.
 */

function SalesmanReportCashOutController($scope, Bracelet) {
    $scope.$on('$viewContentLoaded', function () {
        App.initAjax();
    });
}