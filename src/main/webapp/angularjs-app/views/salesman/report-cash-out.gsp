<div class="row text-right margin-bottom-20 margin-right-10" ng-show="reportList.length > 0">
    <a href="javascript:;" class="btn btn-sm btn-circle red easy-pie-chart-reload uppercase">
        <i class="fa fa-plus"></i> Añadir <b>{{reportList.length}}</b> brazaletes de <b>serie {{braceletNotSoldList[0].costBracelet.id}}</b> a corte de caja de hoy </a>
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
                </ul>
            </div>

            <div class="portlet-body">
                <div class="tab-content" style="{{braceletNotSoldList.length > 0 ? 'height: 500px; overflow-y: auto' : ''}}">
                    <div ng-repeat="avaliblecost in avalibleCostsList"
                         class="tab-pane {{ $index === 0 ? 'active' : '' }}" id="portlet_serie_{{avaliblecost[0].id}}">
                        {{braceletNotSoldList.length != 0 ?  '' : "No tienes brazaletes por reportar de esta serie"}}
                        <div class="col-md-2" ng-repeat="bracelet in braceletNotSoldList" directive-end-repeat-reporte-caja>
                            <div class="color-demo tooltips" soldBracelet="false" bs-bg-color="{{colors[avaliblecost[0].id - 1 ].bg}}" ng-click="addToReport(avaliblecost[0].id,bracelet.id)">
                                <div class="color-view" style="background-color: {{colors[avaliblecost[0].id - 1 ].bg}}; color: {{colors[avaliblecost[0].id - 1].tc}}">{{bracelet.id}}</div>
                                <div class="color-info c-font-14 sbold">{{bracelet.code}}</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>