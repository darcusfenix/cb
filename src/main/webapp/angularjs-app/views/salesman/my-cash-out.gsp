<div class="row">
    <!-- BEGIN SAMPLE TABLE PORTLET-->
    <div class="col-md-12 ">
        <div class="portlet light portlet-fit bordered">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-cogs"></i>Historial
                </div>
                <div class="tools">
                    <a href="javascript:;" class="collapse"></a>
                </div>
            </div>

            <div class="portlet-body" id="p-b-history-bracelets">
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="text-center">#</th>
                            <th class="text-center">Fecha</th>
                            <th class="text-center">Total de brazaletes</th>
                            <th class="text-center"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="h in historyList">
                            <td class="text-center">{{$index + 1}}</td>
                            <td class="text-center">{{h[0] | date:'yyyy-MM-dd hh:mm:ss a'}}</td>
                            <td class="text-center">{{h[1]| number}}</td>
                            <td class="text-center"><button class="btn btn-circle btn-success">Ver detalles</button></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <!-- END SAMPLE TABLE PORTLET-->
</div>