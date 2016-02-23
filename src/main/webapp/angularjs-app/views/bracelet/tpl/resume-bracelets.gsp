<div class="col-md-3" ng-repeat="avaliblecost in avalibleCostsList">
    <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 bordered">
        <h6 class="widget-thumb-heading text-capitalize">
            <p class="text-center text-danger">SERIE {{avaliblecost[0].id}}</p>
            {{getNameOfCircuit(avaliblecost[0].circuit.id)}}
            <br>
            Persona: <b class="text-success">{{getNameOfKindPerson(avaliblecost[0].kindPerson.id)}}</b>
            <br>
            Duración: <b class="text-success">{{getNameOfDaysDuration(avaliblecost[0].daysDuration.id)}}</b>
            <br>
            Costo: <b class="text-success">{{avaliblecost[0].cost | currency}}</b>
        </h6>

        <div class="widget-thumb-wrap">
            <div class="widget-thumb-body">
                <div class="col-md-6">
                    <span class="widget-thumb-subtitle">TOTAL</span>
                    <span class="widget-thumb-body-stat" >{{avaliblecost[1]| number}}</span>
                </div>
                <div class="col-md-6">
                    <span class="widget-thumb-subtitle">{{isSalesman ? 'Vendidas': 'Asignadas'}}</span>
                    <span class="widget-thumb-body-stat" ng-hide="isSalesman" >{{avaliblecost[2]| number}}</span>
                    <span class="widget-thumb-body-stat" ng-show="isSalesman" >{{ getTotalBraceletsSold(avaliblecost[0].id) ? getTotalBraceletsSold(avaliblecost[0].id) : 0 | number}}</span>
                </div>
            </div>
        </div>
    </div>
</div>