var DateRange = function() {

    var startDate = null, endDate = null;

    return {
        initDashboardDaterange: function() {
            if (!jQuery().daterangepicker) {
                return;
            }

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
                    "daysOfWeek": [
                        "Do",
                        "Lu",
                        "Ma",
                        "Mie",
                        "Ju",
                        "Vi",
                        "Sa"
                    ],
                    "monthNames": [
                        "Enero",
                        "Febrero",
                        "Marzo",
                        "Abril",
                        "Mayo",
                        "Junio",
                        "Julio",
                        "Agosto",
                        "Septiembre",
                        "Octubre",
                        "Noviembre",
                        "Deciembre"
                    ],
                    "firstDay": 1
                },

                opens: (App.isRTL() ? 'right' : 'left'),

            }, function(start, end, label) {
                $('#dashboard-report-range span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));

                startDate = start.format('YYYY-MM-DD hh:mm:ss a');
                endDate = end.format('YYYY-MM-DD hh:mm:ss a');

            });

            $('#dashboard-report-range span').html(moment().format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));
            $('#dashboard-report-range').show();
            startDate = moment().format('YYYY-MM-DD hh:mm:ss a');
            endDate = moment().format('YYYY-MM-DD hh:mm:ss a');

        },
        getStartDate: function () {
            return startDate;
        },
        getEndDate: function () {
            return endDate;
        },

        init: function() {
            this.initDashboardDaterange();
        }
    };

}();

if (App.isAngularJsApp() === false) {
    jQuery(document).ready(function() {
        DateRange.init();
    });
}