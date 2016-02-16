<!-- BEGIN DASHBOARD STATS -->
<div class="row">
    <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
        <div class="dashboard-stat blue">
            <div class="visual">
                <i class="fa fa-comments"></i>
            </div>

            <div class="details">
                <div class="number">1349</div>

                <div class="desc">New Feedbacks</div>
            </div>
            <a class="more" href="#">View more
                <i class="m-icon-swapright m-icon-white"></i>
            </a>
        </div>
    </div>

    <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
        <div class="dashboard-stat red">
            <div class="visual">
                <i class="fa fa-bar-chart-o"></i>
            </div>

            <div class="details">
                <div class="number">12,5M$</div>

                <div class="desc">Total Profit</div>
            </div>
            <a class="more" href="#">View more
                <i class="m-icon-swapright m-icon-white"></i>
            </a>
        </div>
    </div>

    <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
        <div class="dashboard-stat green">
            <div class="visual">
                <i class="fa fa-shopping-cart"></i>
            </div>

            <div class="details">
                <div class="number">549</div>

                <div class="desc">New Orders</div>
            </div>
            <a class="more" href="#">View more
                <i class="m-icon-swapright m-icon-white"></i>
            </a>
        </div>
    </div>

    <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
        <div class="dashboard-stat purple">
            <div class="visual">
                <i class="fa fa-globe"></i>
            </div>

            <div class="details">
                <div class="number">+89%</div>

                <div class="desc">Brand Popularity</div>
            </div>
            <a class="more" href="#">View more
                <i class="m-icon-swapright m-icon-white"></i>
            </a>
        </div>
    </div>
</div>
<!-- END DASHBOARD STATS -->

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
    <div class="col-md-10 col-md-offset-1">
        <!-- BEGIN PORTLET-->
        <div class="portlet light bordered ">
            <div class="portlet-title">
                <div class="caption caption-md">
                    <i class="icon-bar-chart font-red"></i>
                    <span class="caption-subject font-red bold uppercase">Resultados de Vendedores</span>
                    <span class="caption-helper hide">weekly stats...</span>
                </div>

                <div class="actions">
                </div>
            </div>

            <div class="portlet-body">
                <div class="row number-stats margin-bottom-30">
                    <div class="col-md-6 col-sm-6 col-xs-6">
                        <div class="stat-left">
                            <div class="stat-chart">
                                <!-- do not line break "sparkline_bar" div. sparkline chart has an issue when the container div has line break -->
                                <div id="sparkline_bar"></div>
                            </div>

                            <div class="stat-number">
                                <div class="title">Total</div>

                                <div class="number">2460</div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6 col-sm-6 col-xs-6">
                        <div class="stat-right">
                            <div class="stat-chart">
                                <!-- do not line break "sparkline_bar" div. sparkline chart has an issue when the container div has line break -->
                                <div id="sparkline_bar2"></div>
                            </div>

                            <div class="stat-number">
                                <div class="title">New</div>

                                <div class="number">719</div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="table-scrollable table-scrollable-borderless">
                    <table class="table table-hover table-light">
                        <thead>
                        <tr class="uppercase">
                            <th colspan="2" class="bold font-green">Nombres</th>
                            <th class="bold font-green">Apellidos</th>
                            <th class="bold font-green">Correo</th>
                            <th class="bold font-green">Teléfono</th>
                            <th class="text-center bold font-green">Historial</th>
                        </tr>
                        </thead>
                        <tr ng-repeat="vendedor in vendedorList|filter:filtro">
                            <td class="fit">
                                <img class="user-pic rounded" src="https://cdn3.iconfinder.com/data/icons/line/36/person-24.png"></td>
                            <td>
                                <a href="javascript:;" class="primary-link">{{vendedor.nombres}}</a>
                            </td>
                            <td>{{vendedor.apellidos}}</td>
                            <td>{{vendedor.username}}</td>
                            <td>{{vendedor.telefono}}</td>
                            <td class="text-center">
                                <a href="#/vendedor/{{vendedor.id}}/historial" class="btn btn-success">Ver historial</a>
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