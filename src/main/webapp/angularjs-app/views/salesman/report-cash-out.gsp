<style type="text/css">
.active-sold{
    background-color: #03D511;
    color: white;
}
</style>


<div class="row widget-row"  data-ng-include="'angularjs-app/views/bracelet/tpl/resume-bracelets.gsp'">
</div>


<div class="row margin-bottom-20 margin-right-10 text-center">

    <div class="col-md-4 col-md-offset-2">
        <a href="javascript:;" class="btn btn-sm btn-circle red easy-pie-chart-reload uppercase"
           ng-hide="reportList.length > 0">
            Selecciona Brazaletes que vendiste en esta fecha
        </a>
    </div>

    <div class="col-md-4 col-md-offset-2">
        <div id="dashboard-report-range" class="pull-right tooltips btn btn-fit-height green" data-placement="top"
             data-original-title="Change dashboard date range">
            <i class="icon-calendar"></i>&nbsp;
            <span class="thin uppercase hidden-xs" id="span"></span>&nbsp;
            <i class="fa fa-angle-down"></i>
        </div>
    </div>
</div>


<div class="row ">
    <div class="col-md-12" id="corte">
        <div class="portlet light bordered">
            <div class="portlet-title tabbable-line">
                <div class="caption">
                    <i class="icon-bubbles font-red"></i>
                    <span class="caption-subject font-red bold uppercase">Mis brazaletes no vendidos de hoy</span>
                </div>
                <ul class="nav nav-tabs">
                    <li class="{{$index == 0 ? 'active' : ''}}" ng-repeat="avaliblecost in avalibleCostsList">
                        <a href="#portlet_serie_{{avaliblecost[0].id}}" data-toggle="tab"
                           ng-click="getBraceletsNotSold(avaliblecost[0].id)">Serie {{avaliblecost[0].id}}</a>
                    </li>
                    <li>
                        <a href="#portlet_serie_resumen" data-toggle="tab"
                           ng-click="null">Resumen</a>
                    </li>
                </ul>
            </div>

            <div class="portlet-body">
                <div class="tab-content"
                     style="{{braceletNotSoldList.length > 0 ? 'height: 500px; overflow-y: auto' : ''}}">

                    <div ng-repeat="avaliblecost in avalibleCostsList"
                         class="tab-pane {{ $index === 0 ? 'active' : '' }}" id="portlet_serie_{{avaliblecost[0].id}}">
                        {{braceletNotSoldList.length != 0 ?  '' : "No tienes brazaletes por reportar de esta serie"}}
                        <div class="col-md-2" ng-repeat="bracelet in braceletNotSoldList"
                             directive-end-repeat-reporte-caja>

                            <div class="color-demo tooltips" soldBracelet="false"
                                 bs-bg-color="{{colors[avaliblecost[0].id - 1 ].bg}}"
                                 ng-click="addToReport(avaliblecost[0].id,bracelet.id)">
                                <div class="color-view {{verify(bracelet.id) ? 'active-sold' : ''}}">{{bracelet.id}}</div>

                                <div class="color-info c-font-14 sbold">{{bracelet.code}}</div>
                            </div>

                        </div>
                    </div>

                    <div class="tab-pane" id="portlet_serie_resumen"
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
                                    <td>Total de brazaletes: </td>
                                    <td class="text-center" ng-repeat="avaliblecost in avalibleCostsList">
                                        {{getTotalByCostSelected(avaliblecost[0].id)}}
                                    </td>
                                    <td class="text-center">{{reportList.length}}</td>
                                </tr>
                                <tr>
                                    <td>Total de ventas: </td>
                                    <td class="text-center" ng-repeat="avaliblecost in avalibleCostsList">
                                        {{getTotalByCostSelected(avaliblecost[0].id) * avaliblecost[0].cost | currency}}
                                    </td>
                                    <td class="text-center">{{ getTotalSold() | currency}}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


