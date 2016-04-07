<div class="row center">
    <div class="col-md-8 form col-md-offset-2">
        <form role="form">
            <div class="form-body">
                <div class="form-group">
                    <div class="input-group input-group-lg">
                        <input type="text" class="form-control" placeholder="Ingrese el identificador o código" ng-model="q" required ng-trim="true" ng-change="null" onfocus="true">

                        <span class="input-group-btn">
                            <button class="btn green" type="button">Buscar!</button>
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