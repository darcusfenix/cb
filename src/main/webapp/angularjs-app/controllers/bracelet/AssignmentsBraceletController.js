/**
 * Created by becm on 3/28/16.
 */

function AssignmentsBraceletController($scope, $filter, Bracelet) {

    $scope.startDate;
    $scope.endDate;

    $scope.assignmentsList;

    $scope.$on('$viewContentLoaded', function () {
        App.initAjax();
        $scope.updateRangeDate();
    });

    $scope.getListOfAssignments = function () {
        Bracelet.getListOfAssignments(function(data){
            $scope.assignmentsList = data;
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
            //$scope.getBraceletsNotSold($scope.idCost);
        });

        $('#dashboard-report-range span').html(moment().format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));
        $('#dashboard-report-range').show();

        $scope.startDate = moment().format('YYYY-MM-DD hh:mm:ss a');
        $scope.endDate = moment().format('YYYY-MM-DD hh:mm:ss a');
        $scope.getListOfAssignments();
    };
}