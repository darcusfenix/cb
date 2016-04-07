<div class="row widget-row" data-ng-include="'angularjs-app/views/bracelet/tpl/resume-bracelets.gsp'">
</div>

<div class="row">
    <!-- BEGIN SAMPLE TABLE PORTLET-->
    <div class="col-md-12 ">
        <div class="portlet light portlet-fit bordered">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-cogs"></i>Historial de {{isSalesman ? 'Asignaciones' : 'Generaciones' }} de Brazaletes
                </div>

                <div class="tools">
                    <a href="javascript:;" class="collapse"></a>
                </div>
            </div>

            <div class="portlet-body" id="p-b-history-bracelets">
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="text-center">#</th>
                            <th class="text-center">Fecha</th>
                            <th class="text-center"
                                ng-repeat="avaliblecost in avalibleCostsList">SERIE {{avaliblecost[0].id}}</th>
                            <th class="text-center">Total</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="h in historyList">
                            <td class="text-center">{{$index + 1}}</td>
                            <td class="text-center">{{h[1] | date:'yyyy-MM-dd hh:mm:ss a' }}</td>
                            <th class="text-center"
                                ng-repeat="avaliblecost in avalibleCostsList">{{getTotalBySerie(h[1], avaliblecost[0].id) | number}}</th>
                            <td class="text-center">{{h[0]| number}}</td>
                            <td class="text-center">
                                <!--<a class="btn btn-circle btn-small purple btn-sm"  href="${request.contextPath}/vendedor/acuse/asignacion/{{h[1] | date:'yyyy-MM-dd hh:mm:ss a'}}" target="_blank">Ver acuse</a>-->
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <!-- END SAMPLE TABLE PORTLET-->
</div>