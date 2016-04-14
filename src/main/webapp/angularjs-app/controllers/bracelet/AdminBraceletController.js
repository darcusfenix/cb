/**
 * Created by becm on 4/14/16.
 */

function AdminBraceletController($rootScope, $scope, $http, $filter, Bracelet){

    $scope.currentBracelet;
    $scope.b;

    $scope.$on('$viewContentLoaded', function () {
        App.initAjax();

    });

    $scope.searchBracelet = function(){
        $scope.currentBracelet = Bracelet.findByIdOrCode({
            v : $scope.b
        },function(data){
            $scope.currentBracelet = data;
            console.log($scope.currentBracelet);
        }, function(err){

        });
    };

}