<div class="row center">
    <div class="col-md-8 form col-md-offset-2">
        <form role="form">
            <div class="form-body">
                <div class="form-group">
                    <div class="input-group input-group-lg">
                        <input type="text" class="form-control" placeholder="Ingrese el identificador o código"
                               ng-model="b" required ng-trim="true" ng-change="null" onfocus="true">

                        <span class="input-group-btn">
                            <button class="btn green" type="button" ng-click="searchBracelet();">Buscar!</button>
                        </span>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>


<div class="row">
    <div class="col-md-6 col-md-offset-3">
        <div class="portlet light bordered">
            <div class="portlet-title">
                <div class="caption">
                    <i class="icon-settings font-dark"></i>
                    <span class="caption-subject font-dark sbold uppercase">Resultado de la búsqueda</span>
                </div>
            </div>

            <div class="portlet-body">
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
                            <td>Vendedor</td>
                            <td class="text-info">{{currentBracelet.salesman }}</td>
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
    </div>
</div>