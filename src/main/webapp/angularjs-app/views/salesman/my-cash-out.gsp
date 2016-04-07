<div class="row">
    <!-- BEGIN SAMPLE TABLE PORTLET-->
    <div class="col-md-12 ">
        <div class="portlet light portlet-fit bordered">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-cogs"></i>Historial
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
                            <th class="text-center">Total de brazaletes</th>
                            <th class="text-center"></th>
                            <th class="text-center"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="h in historyList">
                            <td class="text-center">{{$index + 1}}</td>
                            <td class="text-center">{{h[0]}}</td>
                            <td class="text-center">{{h[1]| number}}</td>
                            <td class="text-center">
                                <!--<a href="#static" ng-click="getResumen(h[0])" class="btn btn-circle btn-success  btn-sm" data-toggle="modal">
                                    Ver detalles
                                </a>-->
                            </td>
                            <td class="text-center">
                                <!--<a href="${request.contextPath}/vendedor/acuse/corte-caja/{{h[0]}}" target="_blank"  class="btn btn-circle btn-small purple btn-sm ">
                                    Obtener acuse
                                </a>-->
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

<div id="static" class="modal fade bs-modal-lg" tabindex="-1" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">Detalles de Reporte de caja  <b>{{d}}</b> :</h4>
            </div>
            <div class="modal-body" id="abcdef">
                <div class="row">
                    <div class="col-md-12">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th class="text-center"></th>
                                    <th class="text-center text-success" ng-repeat="current in currentResumen">
                                        Serie {{current[2].id}}
                                    </th>
                                    <th class="text-center">Total</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>Total de brazaletes:</td>
                                    <td class="text-center" ng-repeat="current in currentResumen">
                                        {{current[1]}}
                                    </td>
                                    <td class="text-center">{{ getTotalByCurrentDate() }}</td>
                                </tr>
                                <tr>
                                    <td>Total de ventas:</td>
                                    <td class="text-center" ng-repeat="current in currentResumen">
                                        {{getTotalByCostSelected(current[2].id) * current[2].cost | currency}}
                                    </td>
                                    <td class="text-center">{{ getTotalSold() | currency}}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn purple">Cerrar</button>
            </div>
        </div>
    </div>
</div>