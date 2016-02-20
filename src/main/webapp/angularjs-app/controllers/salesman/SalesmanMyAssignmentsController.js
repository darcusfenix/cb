/**
 * Created by becm on 2/19/16.
 */

function SalesmanMyAssignmentsController($scope, Salesman) {
    $scope.$on('$viewContentLoaded', function () {
        App.initAjax();
    });

    $scope.getMyAssignmentsList = [];

    $scope.getMyAssignments = function () {
        Salesman.$getMyAssignments(function(data){
            $scope.getMyAssignmentsList = data;
        }, function (err) {
            console.log(err)
        });
    };
}