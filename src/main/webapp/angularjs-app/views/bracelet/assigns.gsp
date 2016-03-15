<div class="row">


    <div class="row text-center" id="response-btn">
    </div>

    <div class="note note-danger" ng-show="f1">
        <h4 class="block">Error para <b>{{salesmanSelected.firstName}} {{salesmanSelected.lastName}}</b></h4>

        <div class="row" id="response-failed">
        </div>
    </div>

    <div class="note note-success" ng-show="f2">
        <h4 class="block">BIen para <b>{{salesmanSelected.firstName}} {{salesmanSelected.lastName}}</b></h4>

        <div class="row" id="response-success">
        </div>
    </div>

</div>

<div class="row widget-row" data-ng-include="'angularjs-app/views/bracelet/tpl/resume-bracelets.gsp'">
</div>

<div class="row center">
    <div class="col-md-8 form col-md-offset-2">
        <form role="form">
            <div class="form-body">
                <div class="form-group">
                    <div class="input-group input-group-lg">
                        <input type="text" class="form-control" placeholder="Escribe un correo o nombres del vendedor"
                               ng-model="q" required ng-trim="true" ng-change="getVendedores()" onfocus="true">

                        <span class="input-group-btn">
                            <button class="btn green" type="button">Buscar!</button>
                        </span>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <!-- BEGIN PORTLET-->
        <div class="portlet light bordered ">
            <div class="portlet-title">
                <div class="caption caption-md">
                    <span class="caption-subject bold uppercase">Resultados de la búsqueda</span>
                </div>

                <div class="tools">
                    <a href="javascript:;" class="collapse"></a>
                </div>
            </div>

            <div class="portlet-body" id="rs-busqueda">
                <div class="table-scrollable table-scrollable-borderless">
                    <table class="table table-hover table-light">
                        <thead>
                        <tr class="uppercase">
                            <th class="bold ">Nombres</th>
                            <th class="bold ">Apellidos</th>
                            <th class="bold ">Correo</th>
                            <th class="bold ">NOmbre de Usuario</th>
                            <th class="text-center bold ">Asignar Brazaletes</th>
                        </tr>
                        </thead>
                        <tr ng-repeat="salesman in vendedorList|filter:filtro">
                            <td>{{salesman.firstName}}</td>
                            <td>{{salesman.lastName}}</td>
                            <td>{{salesman.email}}</td>
                            <td>{{salesman.username}}</td>
                            <td class="text-center">
                                <a ng-click="selectSalesman(salesman)" class="btn " data-toggle="modal"
                                   href="#asignar">Asignar</a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <!-- END PORTLET-->
    </div>
</div>

<div id="asignar" class="modal fade bs-modal-lg" tabindex="-1" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog  modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">Seleccione el rango de IDENTIFICADOR de entrega para <b>{{salesmanSelected.firstName}} {{salesmanSelected.lastName}}</b>
                </h4>

            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="table-scrollable table-scrollable-borderless">
                        <table class="table table-hover table-light">
                            <thead>
                            <th class="font-blue text-uppercase  text-center">

                            </th>
                            <th class="font-blue text-uppercase  text-center">
                                inicio
                            </th>
                            <th class="font-blue text-uppercase  text-center">
                                fin
                            </th>
                            <th class="font-blue text-uppercase  text-center">
                                total de brazaletes
                            </th>
                            </thead>
                            <tr ng-repeat="avaliblecost in avalibleCostsList">
                                <td class="text-left">
                                    Serie {{deliveryBraceletResumen[$index].idCost}}
                                </td>
                                <td>
                                    <input type="number" min="1" placeholder="Inicio del rango" class="form-control"
                                           ng-model="deliveryBraceletResumen[$index].startRange">
                                </td>
                                <td>
                                    <input type="number" min="1" placeholder="Fin del rango" class="form-control"
                                           ng-model="deliveryBraceletResumen[$index].endsRange">
                                </td>
                                <td class="text-center">
                                    <span ng-class=" ( deliveryBraceletResumen[$index].endsRange < deliveryBraceletResumen[$index].startRange )
                                                     || ( deliveryBraceletResumen[$index].endsRange - deliveryBraceletResumen[$index].startRange < 0 )
                                                     ? 'text-danger' : 'text-success'">
                                        {{
                                        (deliveryBraceletResumen[$index].endsRange - deliveryBraceletResumen[$index].startRange) == 0 ?  0 :
                                        deliveryBraceletResumen[$index].endsRange - deliveryBraceletResumen[$index].startRange + 1
                                        }}
                                    </span>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    TOTAL
                                </td>
                                <td>

                                </td>
                                <td>
                                </td>
                                <td class="text-center">
                                    <b>{{getTotal()}}</b>
                                </td>
                            </tr>
                        </table>

                    </div>
                    <span class="text-danger"
                          ng-hide="validate()">Hay errores de validación, verifique que los números correspondan al rango de los brazaletes</span>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn red">Cancelar</button>
                <a href="#stack1" class="btn green" data-toggle="modal" ng-click="null"
                   ng-show="getTotal() > 0 ? true : false">
                    Asignar
                </a>
            </div>
        </div>
    </div>
</div>


<div id="stack1" class="modal fade" tabindex="-1" data-width="400">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">Por favor confirme la siguiente asignación para <b>{{salesmanSelected.firstName}} {{salesmanSelected.lastName}}</b>
                </h4>
            </div>

            <div class="modal-body">
                <div class="table-scrollable table-scrollable-borderless">
                    <table class="table table-hover table-light">
                        <thead>
                        <th class="font-blue text-uppercase  text-center">

                        </th>
                        <th class="font-blue text-uppercase  text-center">
                            inicio
                        </th>
                        <th class="font-blue text-uppercase  text-center">
                            fin
                        </th>
                        <th class="font-blue text-uppercase  text-center">
                            total de brazaletes
                        </th>
                        </thead>

                        <tr ng-repeat="avaliblecost in avalibleCostsList">
                            <td class="text-center">
                                Serie {{deliveryBraceletResumen[$index].idCost}}
                            </td>
                            <td class="font-blue text-uppercase  text-center">
                                {{deliveryBraceletResumen[$index].startRange}}
                            </td>
                            <td class="font-blue text-uppercase  text-center">
                                {{deliveryBraceletResumen[$index].endsRange}}
                            </td>
                            <td class="text-center">
                                <span ng-class=" ( deliveryBraceletResumen[$index].endsRange < deliveryBraceletResumen[$index].startRange )
                                                     || ( deliveryBraceletResumen[$index].endsRange - deliveryBraceletResumen[$index].startRange < 0 )
                                                     ? 'text-danger' : 'text-success'">
                                    {{
                                    (deliveryBraceletResumen[$index].endsRange - deliveryBraceletResumen[$index].startRange) == 0 ?  0 :
                                    deliveryBraceletResumen[$index].endsRange - deliveryBraceletResumen[$index].startRange + 1
                                    }}
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <td class="text-center">
                                TOTAL
                            </td>
                            <td>

                            </td>
                            <td>
                            </td>
                            <td class="text-center">
                                <b>{{getTotal()}}</b>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn dark btn-outline">Cancelar</button>
                <button class="btn green" data-dismiss="modal" ng-click="toAssignForSalesman()" ng-show="validate()">
                    Sí, Asignar
                </button>
            </div>
        </div>
    </div>
</div>