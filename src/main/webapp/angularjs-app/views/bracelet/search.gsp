<div class="portlet light bordered">
    <div class="portlet-title tabbable-line">
        <div class="caption">
            <i class=" icon-social-twitter font-dark hide"></i>
            <span class="caption-subject font-dark bold uppercase"></span>
        </div>
        <ul class="nav nav-tabs">
            <li class="active">
                <a href="#tab_actions_pending" data-toggle="tab" ng-click="false">Uno solo</a>
            </li>
            <li>
                <a href="#tab_actions_completed" data-toggle="tab" ng-click="false">Por rango</a>
            </li>
        </ul>
    </div>

    <div class="portlet-body">
        <div class="tab-content">
            <div class="tab-pane active" id="tab_actions_pending">

                <div class="row center">
                    <div class="col-md-8 form col-md-offset-2">
                        <form role="form">
                            <div class="form-body">
                                <div class="form-group">
                                    <div class="input-group input-group-lg">
                                        <input type="text" class="form-control"
                                               placeholder="Ingrese el identificador o código"
                                               ng-model="b" required ng-trim="true" ng-change="null" onfocus="true">

                                        <span class="input-group-btn">
                                            <button class="btn green" type="button"
                                                    ng-click="searchBracelet();">Buscar!</button>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="row" ng-hide="currentBracelet === null">
                    <div class="col-md-6 col-md-offset-3">

                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover">
                                <tbody>
                                <tr>
                                    <td>Identificador</td>
                                    <td class="text-info">{{currentBracelet.id}}</td>
                                </tr>
                                <tr>
                                    <td>Código</td>
                                    <td class="text-info">{{currentBracelet.code}}</td>
                                </tr>
                                <tr>
                                    <td>Vendedor</td>
                                    <td class="text-info">{{currentBracelet.salesman ? currentBracelet.salesman : 'No hay venedor registrado' }}</td>
                                </tr>
                                <tr>
                                    <td>Fecha de activación en autobús</td>
                                    <td class="text-info">{{currentBracelet.activationDate ? ( currentBracelet.activationDate | date:'MMMM dd yyyy, h:mm:ss a' ) : 'No ha sido escaneado'}}</td>
                                </tr>
                                <tr>
                                    <td>Fecha de asignación</td>
                                    <td class="text-info">{{currentBracelet.assignmentDate ? (currentBracelet.assignmentDate  | date:'MMMM dd yyyy, h:mm:ss a') : 'No ha sido entregado'}}</td>
                                </tr>
                                <tr>
                                    <td>Fecha de entrega al vendedor</td>
                                    <td class="text-info">{{currentBracelet.deliveryDate ? (currentBracelet.deliveryDate | date:'MMMM dd yyyy, h:mm:ss a' ) : 'No ha sido entregado'}}</td>
                                </tr>
                                <tr>
                                    <td>Estado del brazalete</td>
                                    <td class="text-info">{{currentBracelet.braceletState}}</td>
                                </tr>
                                <tr>
                                    <td>Fecha de creación</td>
                                    <td class="text-info">{{currentBracelet.creationDate | date:'MMMM dd yyyy, h:mm:ss a'}}</td>
                                </tr>
                                <tr>
                                    <td>Serie</td>
                                    <td class="text-info">{{currentBracelet.costBracelet}}</td>
                                </tr>
                                <tr>
                                    <td>Venta</td>
                                    <td class="text-info">{{currentBracelet.sold ? 'Se generó corte de caja de este brazalete' : 'No ha sido asignado a un corte de caja'}}</td>
                                </tr>
                                <tr>
                                    <td>Fecha de corte de caja</td>
                                    <td class="text-info">{{currentBracelet.soldDate? ( currentBracelet.soldDate | date:'MMMM dd yyyy, h:mm:ss a' ) : 'No ha sido asignado a un corte de caja'}}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>

                <div class="row text-center" ng-show="currentBracelet === null">
                    <h4 class="text-danger">No hay brazalete con el id o código proporcionado</h4>
                </div>
            </div>
            <div class="tab-pane" id="tab_actions_completed">
                <div class="row ">
                    <div class="col-md-6 form">
                        <div class="form-body">
                            <div class="form-group">
                                <div class="input-group input-group-lg ">
                                    <input type="text" class="form-control" placeholder="Ingrese el inicio del rango" ng-model="st" required ng-trim="true" ng-change="null" onfocus="true">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 form text-center">
                        <div class="form-body">
                            <div class="form-group">
                                <div class="input-group input-group-lg">
                                    <input type="text" class="form-control" placeholder="Ingrese el fin del rango" ng-model="ed" required ng-trim="true" ng-change="null" onfocus="true">
                                    <span class="input-group-btn">
                                        <button class="btn green" type="button"
                                                ng-click="findByIdOrCodeWithRange();">Buscar!</button>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th> Identificador </th>
                                <th> Código </th>
                                <th> Vendedor </th>
                                <th> Fecha de activación en autobús </th>
                                <th> Fecha de asignación </th>
                                <th> Fecha de entrega al vendedor </th>
                                <th> Estado del brazalete </th>
                                <th> Fecha de creación </th>
                                <th> Serie </th>
                                <th> Venta </th>
                                <th> Fecha de corte de caja </th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr ng-repeat="bracelet in braceletList">
                                <td> {{bracelet.id}} </td>
                                <td> {{bracelet.code}} </td>
                                <td> {{bracelet.salesman}} </td>
                                <td> {{bracelet.activationDate | date:'MMMM dd yyyy, h:mm:ss a'}} </td>
                                <td> {{bracelet.assignmentDate | date:'MMMM dd yyyy, h:mm:ss a'}} </td>
                                <td> {{bracelet.deliveryDate | date:'MMMM dd yyyy, h:mm:ss a'}} </td>
                                <td> {{bracelet.braceletState}} </td>
                                <td> {{bracelet.creationDate | date:'MMMM dd yyyy, h:mm:ss a'}} </td>
                                <td> {{bracelet.costBracelet}} </td>
                                <td> {{bracelet.sold}} </td>
                                <td> {{bracelet.soldDate | date:'MMMM dd yyyy, h:mm:ss a'}} </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
