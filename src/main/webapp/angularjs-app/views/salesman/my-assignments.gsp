<div class="row">
    <div class="col-md-12">
        <div class="portlet light bordered ">
            <div class="portlet-title">
                <div class="caption caption-md">
                    <span class="caption-subject bold uppercase">Mis asignaciones de brazaletes</span>
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
                                <a ng-click="selectSalesman(salesman)" class="btn " data-toggle="modal" href="#asignar">Asignar</a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>