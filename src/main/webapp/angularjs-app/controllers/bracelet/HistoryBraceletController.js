/**
 * Created by becm on 3/28/16.
 */

function HistoryBraceletController($rootScope, $scope, $filter, Bracelet) {

    $scope.startDate = null;
    $scope.endDate = null;
    $scope.option = 1;

    $scope.braceletList;

    $scope.textCurrent;


    $scope.changeReport = function (i) {
        $scope.action = i;
        var t;
        switch ($scope.action){
            case 1:
                t = "Reporte entrega de brazaletes";
                break;
            case 2:
                t = "Reporte asignación de brazaletes";
                break;
            case 3:
                t = "Reporte asignación y entrega de brazaletes";
                break;
        }
        $rootScope.$state.current.data.pageTitle = t;
        switch ($scope.action){
            case 1 :
                t = "entregados";
                break;
            case 2 :
                t = "asignados";
                break;
            case 3 :
                t = "asignados y entregados";
                break;
        }
        $scope.textCurrent = t;
    };


    $scope.$on('$viewContentLoaded', function () {
        App.initAjax();
        $scope.updateRangeDate();
        $scope.changeReport($scope.option);
    });

    $scope.getHistory = function () {
        Bracelet.getHistory({
            'op' : $scope.option,
            'sd' : $scope.startDate,
            'ed' : $scope.endDate
        },function(data){
            $scope.braceletList = data;
            console.log($scope.braceletList)
        }, function (err) {
        });
    };

    $scope.updateRangeDate = function () {
        $('#dashboard-report-range').daterangepicker({
            "ranges": {
                'Hoy': [moment(), moment()],
                'Ayer': [moment().subtract('days', 1), moment().subtract('days', 1)],
                'Últimos 7 días': [moment().subtract('days', 6), moment()],
                'Últimos 30 DÍAS': [moment().subtract('days', 29), moment()],
                'Este Mes': [moment().startOf('month'), moment().endOf('month')],
                'Último Mes': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
            },
            "locale": {
                "format": "yyyy-MM-dd hh:mm:ss a",
                "separator": " - ",
                "applyLabel": "Aplicar",
                "cancelLabel": "Cancelar",
                "fromLabel": "Desde",
                "toLabel": "Hasta",
                "customRangeLabel": "Personalizar",
                "daysOfWeek": ["Do","Lu","Ma","Mie", "Ju", "Vi", "Sa"],
                "monthNames": ["ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE","NOVIEMBRE", "DICIEMBRE"],
                "firstDay": 1
            },
            opens: (App.isRTL() ? 'right' : 'left'),
        }, function (start, end, label) {
            $('#dashboard-report-range span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
            $scope.startDate = start.format('YYYY-MM-DD hh:mm:ss a');
            $scope.endDate = end.format('YYYY-MM-DD hh:mm:ss a');
            console.log($scope.startDate);
            console.log($scope.endDate);
            $scope.getHistory();
        });

        $('#dashboard-report-range span').html(moment().format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));
        $('#dashboard-report-range').show();

        $scope.startDate = moment().format('YYYY-MM-DD hh:mm:ss a');
        $scope.endDate = moment().format('YYYY-MM-DD hh:mm:ss a');
        $scope.getHistory();
    };
}