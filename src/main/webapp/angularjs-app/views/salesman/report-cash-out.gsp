<style type="text/css">
.active-sold {
    background-color: #03D511;
    color: white;
}

.scanning-bus {
    background-color: #ff0000;
    color: white;
}

.rec-exp {
    width: 15px;
    height: 15px;
    padding: 0;
    margin: 0;
}

.content-exp label {
    padding-top: 3px;
}

.content-exp div, .content-exp label {
    display: inline-block;
    vertical-align: middle;
}
</style>

<div class="row">
    <div class="note note-success" ng-show="successSave">
        <h4 class="block">Se ha guardado el corte de caja correctamente</h4>
    </div>
</div>

<div class="row widget-row" data-ng-include="'angularjs-app/views/bracelet/tpl/resume-bracelets.gsp'">
</div>

<div class="row margin-bottom-20 margin-right-10 text-center">
    <div class="col-md-4 col-md-offset-2 text-left">
        <a href="javascript:;" class="btn btn-sm red easy-pie-chart-reload uppercase text-left">
            Selecciona los Brazaletes que vendiste hoy
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
                        <a href="#portlet_serie_resumen" data-toggle="tab" ng-show="reportList.length > 0"
                           ng-click="null">Resumen</a>
                    </li>
                    <li>
                        <a href="#portlet_serie_resumen_selected" data-toggle="tab" ng-show="reportList.length > 0"
                           ng-click="null">brazaletes seleccionados</a>
                    </li>
                </ul>
            </div>

            <div class="portlet-body">
                <div class="row text-right margin-bottom-20 margin-right-10">
                    <div class="row text-center">
                        <a href="#static" class="btn btn-sm purple" ng-click="null" ng-show="reportList.length > 0"
                           data-toggle="modal">Generar corte de caja</a>
                    </div>

                    <div class="col-md-6 text-left content-exp">
                        <div class="rec-exp"
                             style="background-color: #03D511"></div> <label>Brazalete seleccionado
                        <b class="text-success"
                           ng-show="checkboxModelFilter.selected">Se están mostrando solo los seleccionados</b>
                    </label><br>

                        <div class="rec-exp"
                             style="background-color: #ff0000"></div> <label>Brazalete escaneado en el Autobús
                        <b class="text-danger"
                           ng-show="checkboxModelFilter.scanningBus">Se están mostrando solo los escaneados</b></label>
                    </div>

                    <div class="col-md-6">
                        <div class="actions">
                            <div class="btn-group">
                                <a class="btn btn-sm blue btn-outline btn-circle" href="javascript:;"
                                   data-toggle="dropdown"
                                   data-hover="dropdown" data-close-others="true">Filtrar por:
                                    <i class="fa fa-angle-down"></i>
                                </a>

                                <div class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">
                                    <label>
                                        <input type="checkbox" ng-model="checkboxModelFilter.selected"/> Seleccionadas
                                    </label>
                                    <label>
                                        <input type="checkbox"
                                               ng-model="checkboxModelFilter.scanningBus"/> Ya escaneadas</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="tab-content"
                     style="{{braceletNotSoldList.length > 0 ? 'height: 500px; overflow-y: auto' : ''}}">
                    <div ng-repeat="avaliblecost in avalibleCostsList"
                         class="tab-pane {{ $index === 0 ? 'active' : '' }}" id="portlet_serie_{{avaliblecost[0].id}}">
                        {{braceletNotSoldList.length != 0 ?  '' : "No tienes brazaletes por reportar de esta serie"}}
                        <div class="col-md-2" ng-repeat="bracelet in braceletNotSoldList"
                             directive-end-repeat-reporte-caja>
                            <div class="color-demo tooltips" soldBracelet="false"
                                 bs-bg-color="{{colors[avaliblecost[0].id - 1 ].bg}}"
                                 ng-click="addToReport(avaliblecost[0].id,bracelet.id)"
                                 ng-hide="(checkboxModelFilter.selected && ( !verify(bracelet.id)  || bracelet.activationDate != null)) || (checkboxModelFilter.scanningBus && ( !verify(bracelet.id)  || bracelet.activationDate == null) )">
                                <div class="color-view {{verify(bracelet.id) ? 'active-sold' : ''}}  {{bracelet.activationDate != null ? 'scanning-bus' : ''}}">{{bracelet.id}}</div>

                                <div class="color-info c-font-14 sbold">{{bracelet.code}}</div>
                            </div>

                        </div>
                    </div>

                    <div class="tab-pane" id="portlet_serie_resumen">
                        <div class="row">
                            <div class="col-md-10 col-md-offset-1">
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th class="text-center"></th>
                                            <th class="text-center text-success"
                                                ng-repeat="avaliblecost in avalibleCostsList">
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

                            <div class="col-md-10 col-md-offset-1 text-right">
                                <a href="#static" class="btn btn-success" ng-click="null"
                                   data-toggle="modal">Generar corte de caja</a>
                            </div>
                        </div>
                    </div>

                    <div class="tab-pane" id="portlet_serie_resumen_selected">
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


<div id="static" class="modal fade bs-modal-lg" tabindex="-1" data-backdrop="static" data-keyboard="false"
     data-ng-include="'angularjs-app/views/salesman/tpl/tpl-corte-caja.gsp'"></div>
