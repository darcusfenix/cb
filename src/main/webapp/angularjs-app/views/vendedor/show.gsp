
<div class="row center" ng-show="error.flag">
    <div class="col-md-6 form  col-md-offset-3">
        <h1 class="text-danger">{{error.message}}</h1>
    </div>
</div>

<div class="row" ng-hide="error.flag" >
    <div class="col-md-8" >
        <!-- BEGIN PORTLET-->
        <div class="portlet light bordered " id="datos-personales">
            <div class="portlet-title">
                <div class="caption caption-md">
                    <i class="icon-bar-chart font-red"></i>
                    <span class="caption-subject font-red bold uppercase">Datos Personales</span>

                </div>
            </div>

            <div class="portlet-body"  data-ng-include="'angularjs-app/views/vendedor/tpl/datos-personales.gsp'">

            </div>
        </div>
        <!-- END PORTLET-->
    </div>
</div>

<div class="row" ng-hide="error.flag">
    <div class="col-md-12">
        <!-- BEGIN PORTLET-->
        <div class="portlet light bordered ">
            <div class="portlet-title">
                <div class="caption caption-md">
                    <i class="icon-bar-chart font-red"></i>
                    <span class="caption-subject font-red bold uppercase">Generar y Asignar Pulseras</span>
                </div>
            </div>

            <div class="portlet-body form" id="generar-pulseras">
                <div class="row form-horizontal">
                    <div class="col-md-5 form-body">
                        <div class="row text-center">
                            <h4>Seleccione las opciones para modificar cantidad:</h4>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">Persona: <span class="text-danger">*</span>
                            </label>
                            <div class="col-md-9">
                                <select ng-init="personaList[0].id"
                                        ng-model="idPersona"
                                        ng-options=" persona.id as persona.nombre for persona in personaList "
                                        ng-change="updateCosto()"
                                        required class="form-control text-uppercase">
                                </select>
                            </div>
                        </div>

                        <div class="form-group {{alerta.flag ? 'has-error' : ''}}">
                            <label class="col-md-3 control-label">Duración: <span class="text-danger">*</span>
                            </label>
                            <div class="col-md-9">
                                <select ng-init="duracionList[0].id"
                                        ng-model="idDuracion"
                                        ng-options=" duracion.id as duracion.duracion for duracion in duracionList "
                                        ng-change="updateCosto()"
                                        required class="form-control text-uppercase">
                                </select>
                                <span class="help-block" ng-show="alerta.flag"> {{alerta.message}} </span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Costo:
                            </label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" value="{{ costo  | currency}}" readonly>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">Cantidad de Pulseras:
                            </label>
                            <div class="col-md-9">
                                <input type="number" class="form-control" value="0" ng-model="cantidad" min="1" step="1">
                            </div>
                        </div>

                        <div class="form-actions text-right">
                            <a  type="button"
                               class="btn btn-success green"
                               ng-disabled="cantidad <= 0 || alerta.flag"
                               ng-hide="cantidad <= 0 || alerta.flag"
                                ng-click="preparConfirmacion()"
                                data-toggle="modal" href="#static">Añadir</a>
                        </div>
                    </div>

                    <div class="col-md-7">
                        <div class="row text-center">
                            <h4>Resumen de Pulseras a Generar y Asignar a: <b>{{vendedor.nombres}} {{vendedor.apellidos}}</b></h4>
                        </div>
                        <div  data-ng-include="'angularjs-app/views/vendedor/tpl/resumen-generar.gsp'">

                        </div>
                        <div class="row text-right">

                            <a  type="button"
                                class="btn btn-success green"
                                ng-disabled="getTotalOfPulseras() == 0"
                                ng-hide=" getTotalOfPulseras() == 0"
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
                <p> {{confirmacion}} </p>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn red" >Cancelar</button>
                <button type="button" data-dismiss="modal" class="btn green" ng-click="sumarPulseras()">Ok</button>
            </div>
        </div>
    </div>
</div>
<div id="generar" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">Confirmar</h4>
            </div>
            <div class="modal-body" data-ng-include="'angularjs-app/views/vendedor/tpl/resumen-generar.gsp'">
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn red" >Cancelar</button>
                <button type="button" data-dismiss="modal" class="btn green" ng-click="generarPulseras()">Sí, generar</button>
            </div>
        </div>
    </div>
</div>