
<div class="row center" ng-show="error.flag">
    <div class="col-md-6 form  col-md-offset-3">
        <h1 class="text-danger">{{error.message}}</h1>
    </div>
</div>


<div class="row" ng-hide="error.flag">
    <div class="col-md-12">
        <!-- BEGIN PORTLET-->
        <div class="portlet light bordered ">
            <div class="portlet-title">
                <div class="caption caption-md">
                    <i class="icon-bar-chart font-red"></i>
                    <span class="caption-subject font-red bold uppercase">Generar</span>
                </div>
            </div>

            <div class="portlet-body form" id="generate-bracelet">
                <div class="row form-horizontal">
                    <div class="col-md-5 form-body">
                        <div class="row text-center">
                            <h4>Seleccione las opciones para modificar cantidad:</h4>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Circuito: <span class="text-danger">*</span>
                            </label>
                            <div class="col-md-9">
                                <select ng-init="circuitList[0].id"
                                        ng-model="idCircuit"
                                        ng-options=" circuit.id as circuit.name for circuit in circuitList"
                                        required class="form-control text-uppercase">
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Persona: <span class="text-danger">*</span>
                            </label>
                            <div class="col-md-9">
                                <select ng-init="kindPersontList[0].id"
                                        ng-model="idKindPerson"
                                        ng-options=" kindPersont.id as kindPersont.name for kindPersont in kindPersontList "
                                        ng-change="updateCostOnView()"
                                        required class="form-control text-uppercase">
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Duración: <span class="text-danger">*</span>
                            </label>
                            <div class="col-md-9">
                                <select ng-init="daysDurationList[0].id"
                                        ng-model="idDaysDuration"
                                        ng-options=" daysDuration.id as daysDuration.days for daysDuration in daysDurationList "
                                        ng-change="updateCostOnView()"
                                        required class="form-control text-uppercase">
                                </select>
                            </div>
                        </div>

                        <div class="form-group {{costCurrent <= 0 ? 'has-error' : ''}}">
                            <label class="col-md-3 control-label">Costo:
                            </label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" value="{{ costCurrent  | currency}}" readonly>
                                <span class="help-block" ng-show="costCurrent <= 0"> {{alertNotFound}} </span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Cantidad de Pulseras:
                            </label>
                            <div class="col-md-9">
                                <input type="number" class="form-control" value="0" ng-model="amount" min="1" step="1">
                            </div>
                        </div>

                        <div class="form-actions text-right">
                            <a  type="button"
                                class="btn btn-success green"
                                ng-disabled="amount <= 0 || costCurrent <= 0 "
                                ng-hide="amount <= 0 || costCurrent <= 0"
                                ng-click="preparingConfirmation()"
                                data-toggle="modal" href="#static">Añadir</a>
                        </div>
                    </div>

                    <div class="col-md-7">
                        <div class="row text-center">
                            <h4>Resumen de Pulseras a Generar</h4>
                        </div>
                        <div  data-ng-include="'angularjs-app/views/vendedor/tpl/resumen-generar.gsp'">

                        </div>
                        <div class="row text-center">

                            <a  type="button"
                                class="btn btn-success green"
                                ng-disabled="getTotalOfBracelets() == 0"
                                ng-hide=" getTotalOfBracelets() == 0"
                                ng-click="null"
                                data-toggle="modal" href="#generar">Generar</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- END PORTLET-->
    </div>
</div>


<div id="static" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">Aviso</h4>
            </div>
            <div class="modal-body">
                <p> {{confirmation}} </p>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn red" >Cancelar</button>
                <button type="button" data-dismiss="modal" class="btn green" ng-click="addBracelets()">Ok</button>
            </div>
        </div>
    </div>
</div>
<div id="generar" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">Confirme la siguiente generación de brazaletes</h4>
            </div>
            <div class="modal-body" data-ng-include="'angularjs-app/views/vendedor/tpl/resumen-generar.gsp'">
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn red" >Cancelar</button>
                <button type="button" data-dismiss="modal" class="btn green" ng-click="generetingBracelets()">Sí, generar</button>
            </div>
        </div>
    </div>
</div>