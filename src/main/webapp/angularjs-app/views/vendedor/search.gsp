<%--
  Created by IntelliJ IDEA.
  User: becm
  Date: 2/4/16
  Time: 10:38 AM
--%>


<div class="row center">
    <div class="col-md-8 form col-md-offset-2">
        <form role="form">
            <div class="form-body">
                <div class="form-group">
                    <div class="input-group input-group-lg">
                        <input type="text" class="form-control" placeholder="Escriba un correo o nombres del Vendedor" ng-model="q" required ng-trim="true" ng-change="getVendedores()" onfocus="true">

                        <span class="input-group-btn">
                            <button class="btn green" type="button">Ir!</button>
                        </span>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<div class="row center" ng-show="vendedorList == null || vendedorList <= 0">
    <div class="col-md-6 form  col-md-offset-3">
        <h1 class="text-danger">No hay vendedores con: {{q}}</h1>
    </div>
</div>

<div class="row" ng-hide="vendedorList == null || vendedorList <= 0">
    <div class="col-md-12">
        <!-- BEGIN PORTLET-->
        <div class="portlet light bordered ">
            <div class="portlet-title">
                <div class="caption caption-md">
                    <i class="icon-bar-chart font-red"></i>
                    <span class="caption-subject font-red bold uppercase">Resultados de la búsqueda</span>
                    <span class="caption-helper hide">weekly stats...</span>
                </div>

                <div class="actions">
                </div>
            </div>

            <div class="portlet-body" id="rs-busqueda">

                <div class="table-scrollable table-scrollable-borderless">
                    <table class="table table-hover table-light">
                        <thead>
                        <tr class="uppercase">
                            <th colspan="2" class="bold font-green">Nombres</th>
                            <th class="bold font-green">Apellidos</th>
                            <th class="bold font-green">Correo</th>
                            <th class="bold font-green">Teléfono</th>
                            <th class="text-center bold font-green">Historial</th>
                            <th class="text-center bold font-green">Reporte</th>
                        </tr>
                        </thead>
                        <tr ng-repeat="vendedor in vendedorList|filter:filtro">
                            <td class="fit">
                                <img class="user-pic rounded" src="https://cdn3.iconfinder.com/data/icons/line/36/person-24.png"></td>
                            <td>
                                <a href="javascript:;" class="primary-link">{{vendedor.firstName}}</a>
                            </td>
                            <td>{{vendedor.lastName}}</td>
                            <td>{{vendedor.email}}</td>
                            <td>{{vendedor.telephone}}</td>
                            <td class="text-center">
                                <a href="#/vendedor/{{vendedor.id}}/historial" class="btn btn-success">Ver historial</a>
                            </td>
                            <td class="text-center">
                                <a href="#/vendedor/{{vendedor.id}}/reporte-pulseras" class="btn btn-success">Ver Reporte</a>
                            </td>

                        </tr>

                    </table>
                </div>
            </div>
        </div>
        <!-- END PORTLET-->
    </div>
</div>
<script