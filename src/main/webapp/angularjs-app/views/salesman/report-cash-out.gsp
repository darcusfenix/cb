<div class="row">
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
                <div class="tab-content" style="height: 500px; overflow-y: auto">
                    <div ng-repeat="avaliblecost in avalibleCostsList"
                         class="tab-pane {{ $index === 0 ? 'active' : '' }}" id="portlet_serie_{{avaliblecost[0].id}}">
                        {{braceletNotSoldList.length != 0 ?  '' : "No tienes brazaletes por reportar de esta serie"}}
                        <div class="col-md-1" ng-repeat="bracelet in braceletNotSoldList">
                            <div class="color-demo tooltips">
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