
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">Por favor confirme, Se generará el siguiente reporte de ventas:</h4>
            </div>
            <div class="modal-body">
                <div class="row ">
                    <div class="col-md-12">
                        <div class="portlet light bordered">
                            <div class="portlet-title tabbable-line">
                                <ul class="nav nav-tabs">
                                    <li class="active">
                                        <a href="#a" data-toggle="tab"
                                           ng-click="null">Resumen</a>
                                    </li>
                                    <li>
                                        <a href="#b" data-toggle="tab"
                                           ng-click="null">brazaletes seleccionados</a>
                                    </li>
                                </ul>
                            </div>
                            <div class="portlet-body">
                                <div class="tab-content">
                                    <div class="tab-pane active" id="a">
                                        <div class="table-responsive">
                                            <table class="table table-striped table-bordered table-hover">
                                                <thead>
                                                <tr>
                                                    <th class="text-center"></th>
                                                    <th class="text-center text-success" ng-repeat="avaliblecost in avalibleCostsList">
                                                        Serie {{avaliblecost[0].id}}
                                                    </th>
                                                    <th class="text-center">Total</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td>Total de brazaletes:</td>
                                                    <td class="text-center" ng-repeat="avaliblecost in avalibleCostsList">
                                                        {{getTotalByCostSelected(avaliblecost[0].id)}}
                                                    </td>
                                                    <td class="text-center">{{reportList.length}}</td>
                                                </tr>
                                                <tr>
                                                    <td>Total de ventas:</td>
                                                    <td class="text-center" ng-repeat="avaliblecost in avalibleCostsList">
                                                        {{getTotalByCostSelected(avaliblecost[0].id) * avaliblecost[0].cost | currency}}
                                                    </td>
                                                    <td class="text-center">{{ getTotalSold() | currency}}</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="b">
                                        <div class="mt-comments">
                                            <div class="mt-comment" ng-repeat="avaliblecost in avalibleCostsList">
                                                <div class="mt-comment-body">
                                                    <div class="mt-comment-info">
                                                        <span class="mt-comment-author">Serie {{avaliblecost[0].id}}</span>
                                                        <span class="mt-comment-date"></span>
                                                    </div>
                                                    <div class="mt-comment-text">{{getBraceletsSelected(avaliblecost[0].id)}}</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn red">Cancelar</button>
                <button type="button" data-dismiss="modal" class="btn green"
                        ng-click="saveCorteCaja()">Sí, guardar</button>
            </div>
        </div>
    </div>