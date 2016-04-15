/**
 * Created by becm on 4/14/16.
 */

function AdminBraceletController($rootScope, $scope, $http, $filter, Bracelet){

    $scope.currentBracelet = null;
    $scope.braceletList = null;
    $scope.b;
    $scope.st;
    $scope.ed;

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
            $scope.currentBracelet = null;
        });
    };
    $scope.findByIdOrCodeWithRange = function(){
        $scope.braceletList = Bracelet.findByIdOrCodeWithRange({
            st : $scope.st,
            ed : $scope.ed
        },function(data){
            $scope.braceletList = data;
            console.log($scope.braceletList);
        }, function(err){
            $scope.braceletList = null;
        });
    };

}